package com.example.Final_Project_mutso.service;

import com.example.Final_Project_mutso.dto.FollowDto;
import com.example.Final_Project_mutso.dto.MypageDto;
import com.example.Final_Project_mutso.dto.ProfileDto;
import com.example.Final_Project_mutso.entity.Follow;
import com.example.Final_Project_mutso.entity.UserEntity;
import com.example.Final_Project_mutso.repository.FollowRepository;
import com.example.Final_Project_mutso.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public MypageDto getMypage(String username) {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        UserEntity userEntity = optionalUser.get();

        List<Follow> followsOptional = followRepository.findByUser(userEntity);
        if(followsOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Follow follow = followsOptional.get(0);

        return MypageDto.fromEntity(userEntity, follow);
    }

    public ProfileDto getProfile(String username){
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        UserEntity userEntity = optionalUser.get();

        return ProfileDto.fromEntity(userEntity);
    }

    public ResponseEntity<Map<String, String>> userFollow(String username){
        Map<String, String> responseBody = new HashMap<>();

        String lusername = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Optional<UserEntity> optionalFollowing = userRepository.findByUsername(username); // 팔로우 할
        Optional<UserEntity> optionalFollower = userRepository.findByUsername(lusername); // 로그인 된

        if (optionalFollowing.isEmpty() || optionalFollower.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        UserEntity following = optionalFollowing.get();
        UserEntity follower = optionalFollower.get();

        List<Follow> optionalFollowingUserFollows = followRepository.findByUser(following);
        List<Follow> optionalFollowerUserFollows = followRepository.findByUser(follower);

        if (optionalFollowingUserFollows.isEmpty()) {
            Follow followingUserFollows = new Follow();
            followingUserFollows.setUser(following);
            optionalFollowingUserFollows = List.of(followingUserFollows);
        }

        if (optionalFollowerUserFollows.isEmpty()) {
            Follow followerUserFollows = new Follow();
            followerUserFollows.setUser(follower);
            optionalFollowerUserFollows = List.of(followerUserFollows);
        }

        Follow followingUserFollows = optionalFollowingUserFollows.get(0);
        Follow followerUserFollows = optionalFollowerUserFollows.get(0);

        if(!username.equals(lusername)){
            if(!followerUserFollows.getFollowingList().contains(following)) {
                followerUserFollows.getFollowingList().add(following);
                followingUserFollows.getFollowerList().add(follower);
                responseBody.put(username, "를 팔로우했습니다.");


            }else if(followerUserFollows.getFollowingList().contains(following)){
                followerUserFollows.getFollowingList().remove(following);
                followingUserFollows.getFollowerList().remove(follower);
                responseBody.put(username, "를 팔로우 취소했습니다.");

            }

        } else {
            responseBody.put("error","본인을 팔로우 할 수 없습니다");
        }

        followRepository.save(followerUserFollows);
        followRepository.save(followingUserFollows);
        return ResponseEntity.ok(responseBody);
    }

    public FollowDto getUserFollows(String username) {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        UserEntity userEntity = optionalUser.get();


        List<Follow> followsOptional = followRepository.findByUser(userEntity);
        if(followsOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Follow follow = followsOptional.get(0);

        return FollowDto.fromEntity(follow, userEntity);
    }

    public UserEntity readUser(String userName) {
        if (userRepository.findByUsername(userName).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return userRepository.findByUsername(userName).get();
    }

}
