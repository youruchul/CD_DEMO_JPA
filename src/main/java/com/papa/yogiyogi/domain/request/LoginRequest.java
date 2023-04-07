package com.papa.yogiyogi.domain.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotEmpty(message = "필수입력항목입니다")
    private String userId;
    @NotEmpty(message = "필수입력항목입니다")
    private String userPw;

}
