package com.example.Final_Project_mutso.stomp.service;

import com.example.Final_Project_mutso.entity.UserEntity;
import com.example.Final_Project_mutso.repository.UserRepository;
import com.example.Final_Project_mutso.stomp.dto.ChatMessageDto;
import com.example.Final_Project_mutso.stomp.dto.ChatRoomDto;
import com.example.Final_Project_mutso.stomp.entity.ChatMessage;
import com.example.Final_Project_mutso.stomp.entity.ChattingRoom;
import com.example.Final_Project_mutso.stomp.jpa.ChatMessageRepository;
import com.example.Final_Project_mutso.stomp.jpa.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    // 채팅방 조회하기
    public List<ChatRoomDto> getChatRooms() {

        List<ChatRoomDto> chatRoomDtoList = new ArrayList<>();
        for (ChattingRoom chatRoomEntity: chatRoomRepository.findAll())
            chatRoomDtoList.add(ChatRoomDto.fromEntity(chatRoomEntity));
        return chatRoomDtoList;
    }

    // 채팅방 생성하기
    public ChatRoomDto createChatRoom(ChatRoomDto chatRoomDto, MultipartFile file) throws IOException {
        String profileDir = "back/chatRoom/";
        try {
            Files.createDirectories(Path.of(profileDir));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // 이미지를 업로드하고 고유한 파일 이름 생성
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        file.transferTo(Path.of(profileDir+fileName));

        // 이미지 URL 생성
        String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("static/chatRoom/") // 이미지 업로드 경로
                .path(fileName)
                .toUriString();

        // 4. Entity 업데이트
        ChattingRoom chattingRoom = new ChattingRoom();
        chattingRoom.setRoomName(chatRoomDto.getRoomName());
        chattingRoom.setImageUrl(imageUrl);
        return ChatRoomDto.fromEntity(chatRoomRepository.save(chattingRoom));
    }

    // 채팅방 삭제
    public void deleteChatRoom(Long id){
        Optional<ChattingRoom> optionalChattingRoom = chatRoomRepository.findById(id);
        if(optionalChattingRoom.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        chatRoomRepository.deleteById(id);
    }

    // 파일 업로드 (받아오기)
    public String uploadFile(MultipartFile image) throws IOException {
        String profileDir = "back/Message/";
        try {
            Files.createDirectories(Path.of(profileDir));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // 이미지를 업로드하고 고유한 파일 이름 생성
        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        image.transferTo(Path.of(profileDir+fileName));

        // 이미지 URL 생성
        String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/chat/file/") // 이미지 업로드 경로
                .path(fileName)
                .toUriString();

        ChatMessage message = new ChatMessage();
        message.setFileUrl(imageUrl);
        chatMessageRepository.save(message);
        return imageUrl;
    }



    //--//
    // 채팅방 인원 +1
    public void increaseUser(Long roomId){
        // 접속한 방의 roomId 확인
        Optional<ChattingRoom> optionalChatRoom = chatRoomRepository.findById(roomId);
        ChattingRoom chatRoom = optionalChatRoom.get();
        chatRoom.setUserCount(chatRoom.getUserCount()+1);
        chatRoomRepository.save(chatRoom);
    }

    // 채팅방 인원 -1
    public void decreaseUser(Long roomId){
        Optional<ChattingRoom> optionalChatRoom = chatRoomRepository.findById(roomId);
        ChattingRoom chatRoom = optionalChatRoom.get();
        chatRoom.setUserCount(chatRoom.getUserCount()-1);
        chatRoomRepository.save(chatRoom);
    }

    // 채팅방 입장 시 사용되는 사용자 닉네임
    public String getNickname(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findAllByUsername(username);

        return user.getNickname();
    }
}


// 메시지 조회
//    public List<ChatMessageDto> readMessage(Long roomId){
//        List<ChatMessageDto> chatMessageDtoList = new ArrayList<>();
//        Optional<ChattingRoom> optionalChatRoom = chatRoomRepository.findById(roomId);
//        if (optionalChatRoom.isEmpty())
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        for (ChatMessage chatMessage : chatMessageRepository.findAll()){
//            chatMessageDtoList.add(ChatMessageDto.fromEntity(chatMessage));
//        }
//        return chatMessageDtoList;
//    }


// 채팅방 정보 불러오기
//    public ChatRoomDto findRoomById(Long id) {
////        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
////        UserEntity user = userRepository.findAllByUsername(auth);
//
//        Optional<ChattingRoom> optionalChatRoom
//                = chatRoomRepository.findById(id);
//        if (optionalChatRoom.isEmpty())
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        return ChatRoomDto.fromEntity(optionalChatRoom.get());
//    }


//    // 메세지 저장하기
//    public void saveChatMessage(ChatMessageDto chatMessageDto) {
//        chatMessageRepository.save(chatMessageDto.newEntity());
//    }