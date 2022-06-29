package net.com.treehole.treeholecore.component;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import net.com.treehole.treeholecore.entity.TreeholeChat;
import net.com.treehole.treeholecore.entity.TreeholeChatCommunication;
import net.com.treehole.treeholecore.entity.enums.UserState;
import net.com.treehole.treeholecore.service.ITreeholeChatCommunicationService;
import net.com.treehole.treeholecore.service.ITreeholeChatService;
import net.com.treehole.treeholecore.service.ITreeholeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;
/**
 *description websocket 服务
 *@className WebSocketServer
 *@author guixiao
 *@date 2022/6/26 9:11
 */
@ServerEndpoint(host="192.168.18.145",path  = "/ws/imserver/{userId}", port = "40003")
@Component
@Slf4j
public class WebSocketServer {
    @Autowired
    private ITreeholeUserService iTreeholeUserService;
    @Autowired
    private ITreeholeChatService iTreeholeChatService;

    @Autowired
    private ITreeholeChatCommunicationService iTreeholeChatCommunicationService ;



    /**
     * 记录当前在线连接数
     */
    public  static final Map<String, Session> sessions = new ConcurrentHashMap<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session,@PathVariable String userId){
        sessions.put(userId, session);
        // 用户状态切换为在线(1)
        iTreeholeUserService.userStatusSwitchover(Long.valueOf(userId), UserState.ON_LINE.getState());
        log.info("有新用户加入,username={}，当前在线人数为: {}", userId, sessions.size());
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
    public void onClose(Session session, @PathVariable String userId){
        // 用户离线切换为在线(0)
        iTreeholeUserService.userStatusSwitchover(Long.valueOf(userId), UserState.OFF_LINE.getState());
        sessions.remove(userId);
        log.info("有一连接关闭，移除username={}用户，当前在线人数为：{}", userId, sessions.size());
    }

    @OnMessage
    public void onMessage(String message, Session session){
        log.info("服务端收到{}的消息{}", message);
        JSONObject jsonObject = JSONUtil.parseObj(message);
        /**
         * to 表示 发送给哪个用户
         */
        String to = jsonObject.getStr("to");
        /**
         * from 表示 当前用户
         */
        String from = jsonObject.getStr("from");
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
            jsonObject1.append("from", from);
            jsonObject1.append("text", text);
            this.sendMessage(jsonObject1.toString(), toSession);
            /**
             * TODO 这里要根据发送人id和收到的人id查找是否已经存在不存在就新建存在就返回
             */
            TreeholeChat treeholeChat = new TreeholeChat();
            /**
             *   通过用户id 和  anotherIdc查找到聊天主表
             */
            List<TreeholeChat> treeholeChats = iTreeholeChatService.selectByUserIdOrAnotherId(Long.valueOf(from), Long.valueOf(to));
            if(treeholeChats.size() > 0){
                treeholeChat = treeholeChats.get(0);
            }else {
                /**
                 *  为空的话就是第一次聊天直接新增
                 */
                treeholeChat.setUserId(Long.valueOf(from));
                treeholeChat.setAnotherId(Long.valueOf(to));
                iTreeholeChatService.save(treeholeChat);
            }

            /**
             * 根据 聊天主表id保存 聊天信息
             */
            TreeholeChatCommunication treeholeChatCommunication = new TreeholeChatCommunication();
            treeholeChatCommunication.setChatId(treeholeChat.getChatId());
            treeholeChatCommunication.setContent(text);
            treeholeChatCommunication.setFromId(Long.valueOf(from));
            treeholeChatCommunication.setToId(Long.valueOf(to));
            iTreeholeChatCommunicationService.save(treeholeChatCommunication);
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
        log.info("服务端给客户端[{}]发送消息", toSession, message);
        toSession.sendText(message);
    }
    /**
     * 服务端发送消息给所有客户端
     * @param message
     */
    private void sendAllMessage(String message) {
        for (Session session : sessions.values()) {
            log.info("服务端给客户端[{}]发送消息", session , message);
            session.sendText(message);
        }
    }
}
