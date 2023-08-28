package com.example.Final_Project_mutso.stomp.service;

import com.example.Final_Project_mutso.stomp.dto.ChatMessageDto;
import com.example.Final_Project_mutso.stomp.dto.ChatRoomDto;
import com.example.Final_Project_mutso.stomp.entity.ChatMessage;
import com.example.Final_Project_mutso.stomp.entity.ChattingRoom;
import com.example.Final_Project_mutso.stomp.jpa.ChatMessageRepository;
import com.example.Final_Project_mutso.stomp.jpa.ChatRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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


    // 채팅방 정보 불러오기
    public ChatRoomDto findRoomById(Long id) {
//        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
//        UserEntity user = userRepository.findAllByUsername(auth);

        Optional<ChattingRoom> optionalChatRoom
                = chatRoomRepository.findById(id);
        if (optionalChatRoom.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // 채팅방 멤버 수 불러오기
        /*
        1. chattingRoom DB에 userId 추가 및 연결하여 입장 시 userId 보이게 설정
        2. db에 현재 참가중인 userId 개수 구하기
        */
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

    // 채팅방 이미지 추가
    public void updateImage(Long id, MultipartFile image){
        String profileDir = "back/chatRoom/";
        try {
            Files.createDirectories(Path.of(profileDir));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // 2-2. 확장자를 포함한 이미지 이름 만들기 (profile.{확장자})
        String originalFilename = image.getOriginalFilename();
        String[] fileNameSplit = originalFilename.split("\\.");
        String extension = fileNameSplit[fileNameSplit.length - 1]; //마지막 배열은 확장자가 되겠지?
        String profileFilename = "chattingRoomImg." + extension;

        // 2-3. 폴더와 파일 경로를 포함한 이름 만들기
        String profilePath = profileDir + profileFilename;

        // 3. MultipartFile 을 저장하기
        try {
            image.transferTo(Path.of(profilePath));
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // 4. Entity 업데이트 (정적 프로필 이미지를 회수할 수 있는 URL)
        ChattingRoom chattingRoomEntity = new ChattingRoom();
        chattingRoomEntity.setImageUrl(String.format("/static/%d/%s", id, profileFilename));
        chatRoomRepository.save(chattingRoomEntity);
    }


    // 채팅방 삭제
    public void deleteChatRoom(Long id){
        Optional<ChattingRoom> optionalChattingRoom = chatRoomRepository.findById(id);
        if(optionalChattingRoom.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        chatRoomRepository.deleteById(id);
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