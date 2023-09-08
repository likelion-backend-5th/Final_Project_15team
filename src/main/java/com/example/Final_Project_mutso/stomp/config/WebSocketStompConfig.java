package com.example.Final_Project_mutso.stomp.config;

import com.example.Final_Project_mutso.entity.CustomUserDetails;
import com.example.Final_Project_mutso.jwt.JwtTokenUtils;
import com.example.Final_Project_mutso.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
// WebSocket과 STOMP 프로토콜을 설정하는 부분
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {
    private final JwtTokenUtils jwtTokenUtils;
    private final UserService userService;
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.enableSimpleBroker("/topic");

        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor
                        = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    List<String> authToken = accessor.getNativeHeader("token");
                    if (authToken != null && authToken.size() == 1) {
                        String tokenValue = authToken.get(0); // 토큰 값 가져오기
                        log.info(tokenValue);
                        String username = jwtTokenUtils.parseClaims(tokenValue)
                                .getSubject();
                        UserDetails userDetails = userService.loadUserByUsername(username);
                        Authentication authentication = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                authToken,
                                userDetails.getAuthorities()
                        );
                        accessor.setUser(authentication);
                    } else throw new AccessDeniedException("invalid token");
                } else if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
                    try {
                        CustomUserDetails jwtUserDetails = (CustomUserDetails) ((Authentication) accessor.getUser()).getPrincipal();
                        if (!accessor.getDestination().endsWith(String.format("/%d", jwtUserDetails.getId())))
                            throw new AccessDeniedException("forbidden");
                    } catch (ClassCastException | NullPointerException e) {
                        log.error(e.getMessage());
                        throw new AccessDeniedException("invalid credentials");
                    }
                }
                return message;
            }
        });
    }
}


