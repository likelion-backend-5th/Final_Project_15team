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
                // STOMP 메시지의 헤더 정보를 읽거나 수정
                StompHeaderAccessor accessor
                        = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                // StompCommand.CONNECT 명령은 클라이언트가 웹 소켓에 연결을 시도할 때 사용
                if (accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())) {
                    // "token"이라는 이름의 헤더에서 토큰 정보를 가져옵니다.
                    List<String> authToken = accessor.getNativeHeader("token");
                    // 토큰 정보가 존재하고 하나의 토큰만 전달된 경우(authToken.size() == 1) 해당 토큰 값을 가져온다.
                    if (authToken != null && authToken.size() == 1) {
                        String tokenValue = authToken.get(0); // 토큰 값 가져오기
                        log.info(tokenValue);
                        // JWT 토큰을 파싱하여 토큰의 subject(주체)를 추출, 이 값은 사용자의 정보를 검색하기 위해 사용
                        // 주어진 JWT 토큰(tokenValue)을 파싱하여 그 안에 포함된 클레임(claim)들을 추출
                        String username = jwtTokenUtils.parseClaims(tokenValue)
                                .getSubject();
                        // UserDetails 객체를 사용하여 사용자 정보를 가져옵
                        UserDetails userDetails = userService.loadUserByUsername(username);
                        // UsernamePasswordAuthenticationToken을 사용하여 사용자 정보와 권한을 포함한 인증 객체를 생성
                        Authentication authentication = new UsernamePasswordAuthenticationToken(
                                // 사용자 정보 객체
                                userDetails,
                                // 인증에 사용된 자격 증명으로 인증 토큰과 같은 정보를 나타냄
                                authToken,
                                // 사용자의 권한 목록을 나타내는 컬렉션
                                userDetails.getAuthorities()
                        );
                        // accessor.setUser(authentication)을 사용하여 STOMP 메시지의 사용자를 설정합니다.
                        // 이를 통해 클라이언트가 웹 소켓 연결을 통해 인증된 사용자로 간주
                        accessor.setUser(authentication);
                    } else throw new AccessDeniedException("잘못된 토큰!");
                    // STOMP(스트리밍 전송 프로토콜) 메시지의 명령(command)가 SUBSCRIBE인 경우
                } else if (accessor != null && StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
                    try {
                        Authentication authentication = (Authentication) accessor.getUser();
                        if (authentication != null) {
                            CustomUserDetails jwtUserDetails = (CustomUserDetails) ((Authentication) accessor.getUser()).getPrincipal();
                            if (!accessor.getDestination().endsWith(String.format("/%d", jwtUserDetails.getId())))
                                throw new AccessDeniedException("forbidden");
                        }
                        else {
                            throw new AccessDeniedException("invalid credentials");
                        }
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


