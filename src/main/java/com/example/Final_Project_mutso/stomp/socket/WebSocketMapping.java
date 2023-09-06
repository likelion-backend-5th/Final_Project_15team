package com.example.Final_Project_mutso.stomp.socket;

import com.example.Final_Project_mutso.stomp.service.ChatService;
import com.example.Final_Project_mutso.stomp.dto.ChatMessageDto;
import com.example.Final_Project_mutso.stomp.dto.ChatRoomDto;
//import com.example.Final_Project_mutso.stomp.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebSocketMapping {
    // STOMP over WebSocket
    private final SimpMessagingTemplate template;
    private final ChatService chatService;
//    private final MessageService messageService;
    // MessageMapping 을 통해 websocket 으로 들어오는 메시지를 발신 처리합니다.
    // 이 때 클라이언트에서는 /pub/chat/message 로 요청을 하게 되고 이것을 controller 가 받아서 처리합니다.
    // 처리가 완료되면 /sub/chat/room/roomId 로 메시지가 전송됩니다.
    @MessageMapping("/chat/enter")
    public void enterUser(
            @Payload ChatMessageDto message,
            SimpMessageHeaderAccessor headerAccessor
    ){
        // 입장하는 순간 채팅방 유저 +1
        chatService.increaseUser(message.getRoomId());

        // 반환 결과를 socket session 에 userUUID 로 저장
        headerAccessor.getSessionAttributes().put("roomId",message.getRoomId());

        message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        template.convertAndSend("/topic/chat/room/enter/"+message.getRoomId(), message);
        log.info("roomId값 : "+message.getRoomId());
    }

    // 메세지 보내기
    @MessageMapping("/chat/message")
    public void sendMessage(@Payload ChatMessageDto message){
        log.info("chat : {}", message);
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        message.setTime(time);
        message.setMessage(message.getSender()+" : "+message.getMessage()+" ("+time+")");
        template.convertAndSend("/topic/chat/room/enter/"+message.getRoomId(), message);
    }

    //유저 퇴장 시에는 EventListener 를 통해서 유저 퇴장을 확인
    // SessionDisconnectEvent : WebSocket 세션이 종료될 때 발생하는 이벤트
    @EventListener
    public void webSocketDisconnectListener(SessionDisconnectEvent event){
        log.info("DisconnectEvent : {}",event);
        // StompHeaderAccessor를 사용하여 필요한 정보를 추출하고, 연결이 끊긴 사용자의 정보를 확인하고 관련된 로직을 수행
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        // stomp 세션에 있던 uuid 와 roomId 를 확인하여 채팅방 유저 리스트와 room에서 해당 유저를 삭제
        // 문자열로 저장했을 시
        String roomIdString = (String) headerAccessor.getSessionAttributes().get("roomId");
        if(roomIdString == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Long roomId = Long.parseLong(roomIdString);

//        Long roomId = (Long) headerAccessor.getSessionAttributes().get("roomId");

        log.info("headAccessor : {}",headerAccessor);

        // 채팅방 유저 -1
        chatService.decreaseUser(roomId);
    }


    // 이미지 파일 누를 때 발생시키도록 설정?
//    @MessageMapping("/sendBinary")
//    @SendTo("/topic/binaryMessages")
//    public BinaryMessage handlerBinaryMessage(@Payload byte[] binaryData) {
//        // 파일 저장
//        // 이진 데이터처리 (이미지 처리)
//        return handlerBinaryMessage(binaryData);
//
//    }



}