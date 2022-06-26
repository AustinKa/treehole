package net.com.treehole.treeholecore.config.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 *description websocket 配置类
 *@className WebSocketConfig
 *@author guixiao
 *@date 2022/6/26 9:09
 */
@Configuration
public class WebSocketConfig {


    /**
     * 注入一个ServerEndpointExporter,该Bean会自动注册使用@ServerEndpoint注解申明的websocket endpoint
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
