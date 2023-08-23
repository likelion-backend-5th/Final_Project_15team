package com.example.Final_Project_mutso.controller;

import com.example.Final_Project_mutso.entity.CustomUserDetails;
import com.example.Final_Project_mutso.jwt.JwtTokenDto;
import com.example.Final_Project_mutso.jwt.JwtTokenUtils;
import com.example.Final_Project_mutso.repository.FollowRepository;
import com.example.Final_Project_mutso.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Controller
//@RestController
@RequestMapping("/users")

public class UserController {
    private final UserRepository userRepository;

    private final FollowRepository followRepository;

    @GetMapping("/login")
    public String loginForm() {
        return "login-form";
    }

    @GetMapping("/profile")
    public String myProfile(
//            Authentication authentication
    ) {
//        CustomUserDetails userDetails
//                = (CustomUserDetails) authentication.getPrincipal();
//        log.info(userDetails.getUsername());
//        log.info(userDetails.getEmail());
        return "profile-form";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register-form";
    }

    @GetMapping("/logout")
    public String logoutForm() {
        return "login-form";
    }

    private final UserDetailsManager manager;
    private final JwtTokenUtils jwtTokenUtils;
    private final PasswordEncoder passwordEncoder;

    public UserController(
            UserRepository userRepository, FollowRepository followRepository, UserDetailsManager manager,
            JwtTokenUtils jwtTokenUtils, PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
        this.manager = manager;
        this.jwtTokenUtils = jwtTokenUtils;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostMapping("/login")
//    public JwtTokenDto issueJwt(@RequestBody JwtRequestDto dto) {
//        UserDetails userDetails
//                = manager.loadUserByUsername(dto.getUsername());
//
//        if (!passwordEncoder.matches(dto.getPassword(), userDetails.getPassword()))
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
//
//        JwtTokenDto response = new JwtTokenDto();
//        response.setToken(jwtTokenUtils.generateToken(userDetails));
//        return response;
//    }

    @PostMapping("/register")
    public String registerPost(
            @RequestParam("name") String name,
            @RequestParam("username") String username,
            @RequestParam("nickname") String nickname,
            @RequestParam("password") String password,
            @RequestParam("password-check") String passwordCheck,
            @RequestParam("phonenumber") String phonenumber,
            @RequestParam("email") String email
    ) {
        if (password.equals(passwordCheck)) {
            log.info("password match!");
            manager.createUser(CustomUserDetails.builder()
                    .name(name)
                    .username(username)
                    .nickname(nickname)
                    .password(passwordEncoder.encode(password))
                    .phonenumber(phonenumber)
                    .email(email)
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
        UserDetails userDetails = manager.loadUserByUsername(username);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        JwtTokenDto response = new JwtTokenDto();
        response.setToken(jwtTokenUtils.generateToken(userDetails));

        return "redirect:/users/profile";
    }

    @PostMapping("/profile")
    public String myProfilePost(
            Authentication authentication
    ) {
        CustomUserDetails customUserDetails
                = (CustomUserDetails) authentication.getPrincipal();
        manager.updateUser(CustomUserDetails.builder()
                .username(customUserDetails.getUsername())
                .password(customUserDetails.getPassword())
                .email(customUserDetails.getEmail())
                .imageurl(customUserDetails.getImageurl())
                .build());

        return "redirect:/users/profile";
    }

    @PostMapping("/logout")
    public String logoutPost() {
        return "redirect:/users/login";
    }

//    @PostMapping("/my-page")
//    public String myPagePost() {
//        return null;
//    }

    // 20230823
//    @PostMapping("/follow/{id}")
//    public @ResponseBody String follow
//            (
//                    @AuthenticationPrincipal CustomUserDetails userDetail,
//                    @PathVariable Long id
//            )
//    {
//        User fromUser = userDetail.getUser();
//        Optional<User> oToUser =
//                userRepository.findById(id);
//        User toUser = oToUser.get();
//
//        Follow follow = new Follow();
//        follow.setFromUser(fromUser);
//        follow.setToUser(toUser);
//
//        followRepository.save(follow);
//
//        return "ok";
//    }
//
//    @DeleteMapping("/follow/{id}")
//    public @ResponseBody String unFollow
//            (
//                    @AuthenticationPrincipal CustomUserDetails userDetail,
//                    @PathVariable Long id
//            )
//    {
//        User fromUser = userDetail.getUser();
//        Optional<User> oToUser =
//                userRepository.findById(id);
//        User toUser = oToUser.get();
//
//        followRepository.deleteByFromUserIdAndToUserId(fromUser.getId(), toUser.getId());
//
//        List<Follow> follows = followRepository.findAll();
//        return "ok";
//    }
//
//    @GetMapping("/follow/follower/{id}")
//    public String followFollower(@PathVariable Long id) {
//
//        // 팔로워 리스트
//
//        return "follow/follow";
//    }
//
//    @GetMapping("/follow/follow/{id}")
//    public String followFollow(@PathVariable Long id) {
//
//        // 팔로우 리스트
//
//        return "follow/follow";
//    }

}
