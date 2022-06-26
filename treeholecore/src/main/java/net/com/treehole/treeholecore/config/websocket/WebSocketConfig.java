package net.com.treehole.treeholecore.config.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yeauty.standard.ServerEndpointExporter;

/**
 *description websocket 配置类
 *@className WebSocketConfig
 *@author guixiao
 *@date 2022/6/26 9:09
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
