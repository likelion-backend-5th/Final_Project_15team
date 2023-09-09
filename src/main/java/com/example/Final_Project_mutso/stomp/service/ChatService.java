package com.example.Final_Project_mutso.stomp.service;

import com.example.Final_Project_mutso.entity.UserEntity;
import com.example.Final_Project_mutso.jwt.AuthenticationFacade;
import com.example.Final_Project_mutso.repository.UserRepository;
import com.example.Final_Project_mutso.stomp.dto.ChatMessageDto;
import com.example.Final_Project_mutso.stomp.dto.ChatRoomDto;
import com.example.Final_Project_mutso.stomp.dto.UserInfoDto;
import com.example.Final_Project_mutso.stomp.entity.ChatMessage;
import com.example.Final_Project_mutso.stomp.entity.ChattingRoom;
import com.example.Final_Project_mutso.stomp.jpa.ChatMessageRepository;
import com.example.Final_Project_mutso.stomp.jpa.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final AuthenticationFacade authFacade;
    private final UserRepository userRepository;

    // 채팅방 목록 조회
    public List<ChatRoomDto> getChatRooms() {
        List<ChatRoomDto> chatRoomDtoList = new ArrayList<>();
        for (ChattingRoom chatRoomEntity: chatRoomRepository.findAll())
            chatRoomDtoList.add(ChatRoomDto.fromEntity(chatRoomEntity));
        return chatRoomDtoList;
    }

    // 채팅방 단독 조회
    public ChatRoomDto findRoomById(Long id) {
        Optional<ChattingRoom> optionalChatRoom
                = chatRoomRepository.findById(id);
        if (optionalChatRoom.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ChatRoomDto.fromEntity(optionalChatRoom.get());
    }

    // 채팅방 생성하기
    public ChatRoomDto createChatRoom(ChatRoomDto chatRoomDto, MultipartFile file) throws IOException {
        UserEntity user = authFacade.getUser();

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

    // 파일 업로드 (받아오 저장 후 url 생성)
    public ChatMessageDto uploadFile(MultipartFile file, ChatMessageDto dto) throws IOException {
        String profileDir = "back/message/";
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
                .path("static/message/") // 이미지 업로드 경로
                .path(fileName)
                .toUriString();

        ChatMessage message = new ChatMessage();
        message.setFileUrl(imageUrl);
        return ChatMessageDto.fromEntity(message);
    }


    // -------- WebsocketMapping 부분 ----------- //
    // 채팅방 인원 +1
    public void increaseUser(Long roomId){
        // 접속한 방의 roomId 확인
        Optional<ChattingRoom> optionalChatRoom = chatRoomRepository.findById(roomId);
        if(optionalChatRoom.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        ChattingRoom chatRoom = optionalChatRoom.get();
        chatRoom.setUserCount(chatRoom.getUserCount()+1);
        chatRoomRepository.save(chatRoom);
        log.info("increaseUser 카운트 결과 : " + chatRoom.getUserCount());
    }

    // 채팅방 인원 -1
    public void decreaseUser(Long roomId){
        Optional<ChattingRoom> optionalChatRoom = chatRoomRepository.findById(roomId);
        if(optionalChatRoom.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        ChattingRoom chatRoom = optionalChatRoom.get();
        chatRoom.setUserCount(chatRoom.getUserCount()-1);
        chatRoomRepository.save(chatRoom);
        log.info("decreaseUser 카운트 결과 : " + chatRoom.getUserCount());
    }


    // 채팅방 유저 정보 조회
    public UserInfoDto getUserInfo(UserInfoDto dto){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findAllByUsername(username);
        dto.setUsername(username);
        dto.setNickname(user.getNickname());
        dto.setProfileImage(user.getProfileImage());
        return UserInfoDto.fromEntity(user);
    }

    // 채팅방 입장 시 url에 표시되는 닉네임 검증
    // url에 입력한 username이 현재 사용자와 일치하지 않으면 400 오류를 뿜는 메소드
    public void checkNickname(String nickname){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findAllByUsername(username);

        if(!nickname.equals(username))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}