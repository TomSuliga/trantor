package org.suliga.trantor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.suliga.trantor.controller.TrantorWebSocketController;

@Configuration
@EnableWebSocket
public class TrantorWebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(myHandler(), "/websocket/ws");
	}

	@Bean
	public TrantorWebSocketController myHandler() {
		return new TrantorWebSocketController();
	}
}
