package com.example.Final_Project_mutso.stomp.service;

import com.example.Final_Project_mutso.stomp.dto.ChatMessageDto;
import com.example.Final_Project_mutso.stomp.dto.ChatRoomDto;
import com.example.Final_Project_mutso.stomp.jpa.ChatMessageEntity;
import com.example.Final_Project_mutso.stomp.jpa.ChatMessageRepository;
import com.example.Final_Project_mutso.stomp.jpa.ChatRoomEntity;
import com.example.Final_Project_mutso.stomp.jpa.ChatRoomRepository;
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
    public List<ChatRoomDto> getChatRooms() {
        List<ChatRoomDto> chatRoomDtoList = new ArrayList<>();
        for (ChatRoomEntity chatRoomEntity: chatRoomRepository.findAll())
            chatRoomDtoList.add(ChatRoomDto.fromEntity(chatRoomEntity));
        return chatRoomDtoList;
    }

    // 채팅방 생성하기
    public ChatRoomDto createChatRoom(ChatRoomDto chatRoomDto) {
        ChatRoomEntity chatRoomEntity = new ChatRoomEntity();
        chatRoomEntity.setRoomName(chatRoomDto.getRoomName());

        return ChatRoomDto.fromEntity(chatRoomRepository.save(chatRoomEntity));
    }

    // 채팅방 이름 가져오기
    public ChatRoomDto findRoomById(Long id) {
        Optional<ChatRoomEntity> optionalChatRoom
                = chatRoomRepository.findById(id);
        if (optionalChatRoom.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ChatRoomDto.fromEntity(optionalChatRoom.get());
    }


    public void saveChatMessage(ChatMessageDto chatMessageDto) {
        chatMessageRepository.save(chatMessageDto.newEntity());
    }

    public List<ChatMessageDto> getLast5Messages(Long roomId) {
        List<ChatMessageDto> chatMessageDtos = new ArrayList<>();
        List<ChatMessageEntity> chatMessageEntities = chatMessageRepository.findTop5ByRoomIdOrderByIdDesc(roomId);
        Collections.reverse(chatMessageEntities);
        for (ChatMessageEntity messageEntity: chatMessageEntities) {
            chatMessageDtos.add(ChatMessageDto.fromEntity(messageEntity));
        }
        return chatMessageDtos;
    }
}