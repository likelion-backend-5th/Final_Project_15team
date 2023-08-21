package com.example.Final_Project_mutso.controller;

import com.example.Final_Project_mutso.entity.CustomUserDetails;
import com.example.Final_Project_mutso.entity.UserEntity;
import com.example.Final_Project_mutso.jwt.JwtTokenUtils;
import com.example.Final_Project_mutso.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
//@RestController
@RequestMapping("/users")

public class UserController {
    private final UserRepository userRepository;

    @GetMapping("/login")
    public String loginForm() {
        return "login-form";
    }

    @GetMapping("/my-profile")
    public String myProfile(
            Authentication authentication
    ) {
        CustomUserDetails userDetails
                = (CustomUserDetails) authentication.getPrincipal();
        log.info(userDetails.getUsername());
        log.info(userDetails.getEmail());
        return "my-profile";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register-form";
    }

    private final UserDetailsManager manager;
    private final JwtTokenUtils jwtTokenUtils;
    private final PasswordEncoder passwordEncoder;

    public UserController(
            UserRepository userRepository, UserDetailsManager manager,
            JwtTokenUtils jwtTokenUtils, PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.manager = manager;
        this.jwtTokenUtils = jwtTokenUtils;
        this.passwordEncoder = passwordEncoder;
    }

    /*@PostMapping("/login")
    public JwtTokenDto issueJwt(@RequestBody JwtRequestDto dto) {
        UserDetails userDetails
                = manager.loadUserByUsername(dto.getUsername());

        if (!passwordEncoder.matches(dto.getPassword(), userDetails.getPassword()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        JwtTokenDto response = new JwtTokenDto();
        response.setToken(jwtTokenUtils.generateToken(userDetails));
        return response;
    }*/

    @PostMapping("/register")
    public String registerPost(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("password-check") String passwordCheck
            //@RequestParam("name") String name,
            //@RequestParam("nickname") String nickname,
            //@RequestParam("phonenumber") String phonenumber,
            //@RequestParam("email") String email
    ) {
        if (password.equals(passwordCheck)) {
            log.info("password match!");
            manager.createUser(CustomUserDetails.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .build());

            return "redirect:/users/login";
        }
        log.warn("password does not match...");
        return "redirect:/users/register?error";
    }

    @PostMapping("/login")
    public String loginPost(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        return "redirect:/users/my-profile";
    }

    @PostMapping("/my-profile")
    public String myProfilePost(
            Authentication authentication
    ) {
        CustomUserDetails customUserDetails
                = (CustomUserDetails) authentication.getPrincipal();
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(customUserDetails.getUsername());
        userEntity.setPassword(customUserDetails.getPassword());
        userEntity.setEmail(customUserDetails.getEmail());
        userEntity.setImage(customUserDetails.getImage());

        return "redirect:/users/my-profile";
    }

}
