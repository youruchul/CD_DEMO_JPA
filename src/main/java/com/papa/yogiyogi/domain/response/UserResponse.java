package com.papa.yogiyogi.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String nickName;
    private String birth; //yymmdd
    private String phoneNumber;
    private String email;
    private String token;
}
