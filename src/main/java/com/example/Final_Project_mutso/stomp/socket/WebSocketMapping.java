package com.example.Final_Project_mutso.stomp.socket;

import com.example.Final_Project_mutso.stomp.dto.ChatMessageDto;
import com.example.Final_Project_mutso.stomp.service.ChatService;
import com.example.Final_Project_mutso.stomp.dto.ChatRoomDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebSocketMapping {
    // STOMP over WebSocket
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/chat")
    public void sendChat(
            // 메세지의 실제 내용을 보여주는 @payload
            @Payload ChatMessageDto chatMessageDto,
            // STOMP over WebSocket은 Header를 포함할 수 있다
            @Headers Map<String, Object> headers,
            @Header("nativeHeaders") Map<String, String> nativeHeaders
    ){
        log.info(chatMessageDto.toString());
        log.info(headers.toString());
        log.info(nativeHeaders.toString());
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        chatMessageDto.setTime(time);
        chatService.saveChatMessage(chatMessageDto);
        simpMessagingTemplate.convertAndSend(
                String.format("/topic/%s", chatMessageDto.getRoomId()),
                chatMessageDto
        );
    }

    // 누군가가 구독할때 실행하는 메소드
    @SubscribeMapping("/{roomId}")
    public List<ChatMessageDto> sendGreet(
            @DestinationVariable("roomId") Long roomId
    ) {
        log.info("new subscription to {}", roomId);
        ChatRoomDto chatRoomDto = chatService.findRoomById(roomId);
        List<ChatMessageDto> last5Messages
                = chatService.getLast5Messages(roomId);
        ChatMessageDto chatMessageDto = new ChatMessageDto();
        chatMessageDto.setRoomId(roomId);
        chatMessageDto.setSender("admin");
        if (last5Messages.size() > 0) {
            int count = Math.min(last5Messages.size(), 5);
            chatMessageDto.setMessage(String.format("hello! these are the last %d messages", count));
            chatMessageDto.setTime(last5Messages.get(0).getTime());
        } else {
            chatMessageDto.setMessage("hello! there aren't any messages here");
            chatMessageDto.setTime(new SimpleDateFormat("HH:mm").format(new Date()));
        }
        last5Messages.add(0, chatMessageDto);
        return last5Messages;
    }
}