package com.example.Final_Project_mutso.stomp.service;

import com.example.Final_Project_mutso.stomp.dto.ChatMessageDto;
import com.example.Final_Project_mutso.stomp.dto.ChatRoomDto;
import com.example.Final_Project_mutso.stomp.entity.ChatMessage;
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
    public List<ChatRoomDto> getChatRooms() {
        List<ChatRoomDto> chatRoomDtoList = new ArrayList<>();
        for (ChattingRoom chatRoomEntity: chatRoomRepository.findAll())
            chatRoomDtoList.add(ChatRoomDto.fromEntity(chatRoomEntity));
        return chatRoomDtoList;
    }

    // 채팅방 생성하기
    public ChatRoomDto createChatRoom(ChatRoomDto chatRoomDto) {
        ChattingRoom chattingRoom = new ChattingRoom();
        chattingRoom.setRoomName(chatRoomDto.getRoomName());

        return ChatRoomDto.fromEntity(chatRoomRepository.save(chattingRoom));
    }

    // 채팅방 이름 가져오기
    public ChatRoomDto findRoomById(Long id) {
        Optional<ChattingRoom> optionalChatRoom
                = chatRoomRepository.findById(id);
        if (optionalChatRoom.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ChatRoomDto.fromEntity(optionalChatRoom.get());
    }


    // 메세지 저장하기
    public void saveChatMessage(ChatMessageDto chatMessageDto) {
        chatMessageRepository.save(chatMessageDto.newEntity());
    }

    // 메시지 조회
    public List<ChatMessageDto> readMessage(Long roomId){
        List<ChatMessageDto> chatMessageDtoList = new ArrayList<>();
        Optional<ChattingRoom> optionalChatRoom = chatRoomRepository.findById(roomId);
        if (optionalChatRoom.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        for (ChatMessage chatMessage : chatMessageRepository.findAll()){
            chatMessageDtoList.add(ChatMessageDto.fromEntity(chatMessage));
        }
        return chatMessageDtoList;
    }

    public List<ChatMessageDto> getLast5Messages(Long roomId) {
        List<ChatMessageDto> chatMessageDtos = new ArrayList<>();
        List<ChatMessage> chatMessageEntities = chatMessageRepository.findTop5ByRoomIdOrderByIdDesc(roomId);
        Collections.reverse(chatMessageEntities);
        for (ChatMessage messageEntity: chatMessageEntities) {
            chatMessageDtos.add(ChatMessageDto.fromEntity(messageEntity));
        }
        return chatMessageDtos;
    }
}