package com.example.Final_Project_mutso.stomp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
// WebSocket과 STOMP 프로토콜을 설정하는 부분
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    // STOMP 엔드포인트 설정용 메소드
    // 엔드포인트 등록 시 클라이언트 애플리케이션과 WebSocket 연결을 설정
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 클라이언트는 이 경로를 사용하여 WebSocket 연결을 생성하고 STOMP 프로토콜을 통해 메시지를 주고받을 수 있게 됨
        registry.addEndpoint("/chatting");
    }

    @Override
    // MessageBroker를 활용하는 방법 설정
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // /topic 경로를 구독한 클라이언트들에게 메시지를 전달
        registry.enableSimpleBroker("/topic");
        // 클라이언트는 /app 및 /topic 경로를 사용하여 메시지를 보낼 수 있다.
        registry.setApplicationDestinationPrefixes("/app", "/topic");
    }
}
