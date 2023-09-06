package com.example.Final_Project_mutso.jwt;

import com.example.Final_Project_mutso.entity.CustomUserDetails;
import com.example.Final_Project_mutso.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Component
public class AuthenticationFacade {
    public UserEntity getUser() {
        try {
            return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserEntity();
        } catch (ClassCastException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
