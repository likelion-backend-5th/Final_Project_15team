package com.example.Final_Project_mutso.controller;

import com.example.Final_Project_mutso.dto.*;
import com.example.Final_Project_mutso.entity.Follow;
import com.example.Final_Project_mutso.entity.UserEntity;
import com.example.Final_Project_mutso.jwt.JwtRequestDto;
import com.example.Final_Project_mutso.jwt.JwtTokenDto;
import com.example.Final_Project_mutso.jwt.JwtTokenUtils;
import com.example.Final_Project_mutso.repository.FollowRepository;
import com.example.Final_Project_mutso.repository.UserRepository;
import com.example.Final_Project_mutso.service.UserService;
import com.example.Final_Project_mutso.service.JpaUserDetailsManager;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

// cors 설정
@CrossOrigin(origins = "*")

public class UserController {
    private final UserRepository userRepository;

    private final FollowRepository followRepository;

    private final JpaUserDetailsManager jpaUserDetailsManager;

    private final UserDetailsManager userDetailsManager;
    private final JwtTokenUtils jwtTokenUtils;
    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    @PostMapping("/login")
    public JwtTokenDto issueJwt(@RequestBody JwtRequestDto dto) {
        UserDetails userDetails
                = userDetailsManager.loadUserByUsername(dto.getUsername());

        if (!passwordEncoder.matches(dto.getPassword(), userDetails.getPassword()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        JwtTokenDto response = new JwtTokenDto();
        response.setToken(jwtTokenUtils.generateToken(userDetails));
        return response;
    }

    @PostMapping("/secure-resource")
    public ResponseEntity<UserDetails> secureResource(@RequestHeader("Authorization") String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or missing token");
        }

        String jwtToken = token.substring(7); // "Bearer " 이후의 토큰 부분 추출

        if (jwtTokenUtils.validate(jwtToken)) {
            Claims claims = jwtTokenUtils.parseClaims(jwtToken);
            String username = claims.getSubject();
            UserDetails userDetails = userDetailsManager.loadUserByUsername(username);

            return ResponseEntity.ok(userDetails);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(
            @RequestParam("name") String name,
            @RequestParam("username") String username,
            @RequestParam("nickname") String nickname,
            @RequestParam("password") String password,
            @RequestParam("password-check") String passwordCheck,
            @RequestParam("phonenumber") String phonenumber,
            @RequestParam("email") String email
    ) {
        Map<String, String> responseBody = new HashMap<>();
        if (password.equals(passwordCheck) && !jpaUserDetailsManager.userExists(username)) {
            UserEntity userEntity = new UserEntity();
            userEntity.setName(name);
            userEntity.setUsername(username);
            userEntity.setNickname(nickname);
            userEntity.setPassword(passwordEncoder.encode(password));
            userEntity.setPhonenumber(phonenumber);
            userEntity.setEmail(email);

            Follow follow = new Follow();
            follow.setUser(userEntity);

            userRepository.save(userEntity);
            followRepository.save(follow);

            responseBody.put("message", "등록이 완료 되었습니다.");
        } else if (jpaUserDetailsManager.userExists(username)) {
            responseBody.put("message", "아이디가 중복됩니다.");
        } else if (!password.equals(passwordCheck)) {
            responseBody.put("message", "패스워드가 일치하지 않습니다.");
        }
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/mypage/{username}")
    public MypageDto mypage(
            @PathVariable("username") String username

    ){
        return userService.getMypage(username);
    }

    @GetMapping("/mypage/{username}/profile")
    public ProfileDto profile(
            @PathVariable("username") String username

    ){
        return userService.getProfile(username);
    }

    @PutMapping(value = "/mypage/profile/imgupload" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> updateImage(
            @RequestParam("image") MultipartFile avatarImage
    ) throws IOException {

        jpaUserDetailsManager.ImageUpload(avatarImage);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "이미지가 등록되었습니다.");

        return ResponseEntity.ok(responseBody);
    }

    @PutMapping("/mypage/{username}/follow")
    public ResponseEntity<Map<String, String>> userFollow(
            @PathVariable("username") String username
    ){
        return userService.userFollow(username);
    }

    @GetMapping("/mypage/{username}/follow")
    public FollowDto getUserFollows(
            @PathVariable("username") String username
    ){
        return userService.getUserFollows(username);
    }

    @PutMapping("/{id}/scrap")
    public ResponseEntity<Map<String, String>> userScrap(
            @PathVariable("id") Long id
    ) {
        return userService.userScrap(id);
    }

    @GetMapping("/mypage/{id}/scrap")
    public ScrapDto getUserScraps(
            @PathVariable("id") Long id
    ) {
        return userService.getUserScraps(id);
    }

}
