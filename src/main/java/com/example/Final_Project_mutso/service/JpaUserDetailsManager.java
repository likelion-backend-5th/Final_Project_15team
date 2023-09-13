package com.example.Final_Project_mutso.service;

import com.example.Final_Project_mutso.entity.CustomUserDetails;
import com.example.Final_Project_mutso.entity.UserEntity;
import com.example.Final_Project_mutso.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Slf4j
@Service
public class JpaUserDetailsManager implements UserDetailsManager {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public JpaUserDetailsManager(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

//        UserEntity testUser = new UserEntity();
//        testUser.setEmail("test@gmail.com");
//        testUser.setPassword("test");
//        testUser.setUsername("test");
//        testUser.setName("test");
//        testUser.setProfileImage("test");
//        testUser.setPhonenumber("test");
//        testUser.setNickname("test");
////                testUser.setImage("test.img");
//
//        userRepository.save(testUser);
        //test user entity 생성
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser
                = userRepository.findByUsername(username);
        if (optionalUser.isEmpty())
            throw new UsernameNotFoundException(username);
        return CustomUserDetails.fromEntity(optionalUser.get());
    }

    @Override
    public void createUser(UserDetails user) {
        if (this.userExists(user.getUsername()))
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        try {
            this.userRepository.save(
                    ((CustomUserDetails) user).newEntity());
        } catch (ClassCastException e) {
            log.error("failed to cast to {}", CustomUserDetails.class);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean userExists(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public void updateUser(UserDetails user) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public void deleteUser(String username) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    public void ImageUpload(MultipartFile avatarImage, String nickname, String introduction) throws IOException {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Optional<UserEntity> optionalUser
                = userRepository.findByUsername(username);

        if(optionalUser.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        UserEntity userEntity = optionalUser.get();
        String profileDir = "back/profile/";
//        String profileDir = String.format("media/%s/",username);
        try {
            Files.createDirectories(Path.of(profileDir));
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String originalFilename = System.currentTimeMillis() + "_" + avatarImage.getOriginalFilename();
        avatarImage.transferTo(Path.of(profileDir + originalFilename));
//        String[] fileNameSplit = originalFilename.split("\\.");
//        String extension = fileNameSplit[fileNameSplit.length - 1];
//
//        String profileFilename = "profile." + extension;
//
//        String profilePath = profileDir + profileFilename;
//        try {
//            avatarImage.transferTo(Path.of(profilePath));
//
//        } catch (IOException e) {
//            log.error(e.getMessage());
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("static/profile/")
                .path(originalFilename)
//                .path("/static/{username}/{profileFilename}")
//                .buildAndExpand(username, profileFilename)
                .toUriString();
        userEntity.setProfileImage(imageUrl);
        userEntity.setNickname(nickname);
        userEntity.setIntroduction(introduction);
        userRepository.save(userEntity);
//        userEntity.setProfileImage(String.format("/static/%s/%s",username,profileFilename));
//        userRepository.save(userEntity);
    }

}
