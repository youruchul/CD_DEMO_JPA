package com.papa.yogiyogi.service;

import com.papa.yogiyogi.Exception.IdCheckException;
import com.papa.yogiyogi.Exception.LoginException;
import com.papa.yogiyogi.domain.entity.User;
import com.papa.yogiyogi.domain.request.LoginRequest;
import com.papa.yogiyogi.domain.request.SignupRequest;
import com.papa.yogiyogi.domain.response.UserResponse;
import com.papa.yogiyogi.repository.UserRepository;
import com.papa.yogiyogi.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SecurityService securityService;
    public UserResponse loginService(LoginRequest request) throws LoginException{
        Optional < User> findByUserIdAndUserPw =
                userRepository.findByUserIdAndUserPw(
                        request.getUserId(), request.getUserPw()
                );
        User user =
                findByUserIdAndUserPw
                        .orElseThrow(LoginException::new);
        String token = securityService.createToken(user);
        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getNickName(),
                user.getBirth(),
                user.getPhoneNumber(),
                user.getEmail(),
                token
        );
        return userResponse;
    }

    public void signupService (SignupRequest request) throws IdCheckException, LoginException {
        User user = request.toEntity();
        Optional<User> findUserId = userRepository.findByUserId(request.getUserId());
        if (findUserId.isPresent()) {
            throw new IdCheckException();
        }
        userRepository.save(user);


    }

    public String deleteUserById() {
        Long myId = securityService.parseToken(securityService.getToken()).getId();
        userRepository.deleteById(myId);
        return "회원 탈퇴 완료";
    }
}
