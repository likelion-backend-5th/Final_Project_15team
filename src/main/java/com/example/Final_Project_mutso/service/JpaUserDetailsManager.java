package com.example.Final_Project_mutso.service;

import com.example.Final_Project_mutso.entity.CustomUserDetails;
import com.example.Final_Project_mutso.entity.UserEntity;
import com.example.Final_Project_mutso.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

        UserEntity testUser = new UserEntity();
        testUser.setEmail("test@gmail.com");
        testUser.setPassword("test");
        testUser.setUsername("test");
        testUser.setImage("test.img");

        userRepository.save(testUser);


        UserEntity testUser2 = new UserEntity();
        testUser2.setEmail("test2@gmail.com");
        testUser2.setPassword("test2");
        testUser2.setUsername("test2");
        testUser2.setImage("test2.img");

        userRepository.save(testUser2);
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

}
