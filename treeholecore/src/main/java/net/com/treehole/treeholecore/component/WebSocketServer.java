package net.com.treehole.treeholecore.component;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *description websocket 服务
 *@className WebSocketServer
 *@author guixiao
 *@date 2022/6/26 9:11
 */
@ServerEndpoint(value = "/imserver/{username}")
@Component
@Slf4j
public class WebSocketServer {
    /**
     * 记录当前在线连接数
     */
    public  static final Map<String, Session> sessions = new ConcurrentHashMap<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("username")String username){
        sessions.put(username, session);
        log.info("有新用户加入,username={}，当前在线人数为: {}", username, sessions.size());
        JSONObject jsonObject = new JSONObject();
        JSONArray arry = new JSONArray();
        jsonObject.append("users", arry);
        for (String s : sessions.keySet()) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("username", s);
            arry.add(jsonObject1);
        }
        sendAllMessage(JSONUtil.toJsonStr(jsonObject));
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("username")String username){
        sessions.remove(username);
        log.info("有一连接关闭，移除username={}用户，当前在线人数为：{}", username, sessions.size());
    }
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("username") String username){
        log.info("服务端收到用户username={}的消息{}", username, message);
        JSONObject jsonObject = JSONUtil.parseObj(message);
        /**
         * to 表示 发送给哪个用户
         */
        String to = jsonObject.getStr("to");
        /**
         * 发送的消息
         */
        String text = jsonObject.getStr("text");
        /**
         *  根据 to 用户名来取session 再通过 session发送消息文本
         */
        Session toSession = sessions.get(to);
        if(ObjectUtil.isNotNull(toSession)){
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.append("from", username);
            jsonObject1.append("text", text);
            this.sendMessage(jsonObject1.toString(), toSession);
            log.info("发送给用户username={}的消息{}", to, jsonObject1.toString());
        }else  {
            log.error("发送失败，未找到用户{}", to);
        }
    }

    @OnError
    public void onErro(Session session, Throwable throwable){
        log.error("发送错误");
        throwable.printStackTrace();
    }
    
    /**
     * description 服务端给客户端发送消息
     * @author ruhu
     * @param message 
     * @param: toSession
     * @return void
     * @date 2022/6/26 9:37
     */
    private void sendMessage(String message, Session toSession){
        log.info("服务端给客户端[{}]发送消息", toSession.getId(), message);
        try {
            toSession.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("服务器端发送消息给客户端失败", e);
        }

    }
    /**
     * 服务端发送消息给所有客户端
     * @param message
     */
    private void sendAllMessage(String message) {
        try {
            for (Session session : sessions.values()) {
                log.info("服务端给客户端[{}]发送消息", session.getId(), message);
                session.getBasicRemote().sendText(message);
            }
        } catch (IOException e) {
            log.error("服务器端发送消息给客户端失败", e);
        }
    }
}
