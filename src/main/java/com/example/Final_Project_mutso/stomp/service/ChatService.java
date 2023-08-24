package com.example.Final_Project_mutso.stomp.service;

import com.example.Final_Project_mutso.stomp.dto.ChatMessage;
import com.example.Final_Project_mutso.stomp.dto.ChatRoom;
import com.example.Final_Project_mutso.stomp.entity.ChatMessageEntity;
import com.example.Final_Project_mutso.stomp.entity.ChattingRoom;
import com.example.Final_Project_mutso.stomp.repository.ChatMessageRepository;
import com.example.Final_Project_mutso.stomp.repository.ChatRoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    public ChatService(
            ChatRoomRepository chatRoomRepository,
            ChatMessageRepository chatMessageRepository
    ) {
        this.chatRoomRepository = chatRoomRepository;
        this.chatMessageRepository = chatMessageRepository;
//        ChatRoomEntity room = new ChatRoomEntity();
//        room.setRoomName("general");
//        this.chatRoomRepository.save(room);
    }

    // 채팅방 조회하기
    public List<ChatRoom> getChatRooms() {
        List<ChatRoom> chatRoomList = new ArrayList<>();
        for (ChattingRoom chatRoomEntity: chatRoomRepository.findAll())
            chatRoomList.add(ChatRoom.fromEntity(chatRoomEntity));
        return chatRoomList;
    }

    // 채팅방 생성하기
    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        ChattingRoom chattingRoom = new ChattingRoom();
        chattingRoom.setRoomName(chatRoom.getRoomName());

        return ChatRoom.fromEntity(chatRoomRepository.save(chattingRoom));
    }

    // 채팅방 이름 가져오기
    public ChatRoom findRoomById(Long id) {
        Optional<ChattingRoom> optionalChatRoom
                = chatRoomRepository.findById(id);
        if (optionalChatRoom.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ChatRoom.fromEntity(optionalChatRoom.get());
    }

    // 메세지 보내기


    // 메세지 저장하기
    public void saveChatMessage(ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage.newEntity());
    }


    public List<ChatMessage> getLast5Messages(Long roomId) {
        List<ChatMessage> chatMessages = new ArrayList<>();
        List<ChatMessageEntity> chatMessageEntities = chatMessageRepository.findTop5ByRoomIdOrderByIdDesc(roomId);
        Collections.reverse(chatMessageEntities);
        for (ChatMessageEntity messageEntity: chatMessageEntities) {
            chatMessages.add(ChatMessage.fromEntity(messageEntity));
        }
        return chatMessages;
    }
}