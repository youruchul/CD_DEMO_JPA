package com.papa.yogiyogi.domain.request;

import com.papa.yogiyogi.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    @NotEmpty(message = "필수입력사항입니다")
    private String userId;
    @NotEmpty(message = "필수입력사항입니다")
    private String userPw;
    @NotEmpty(message = "필수입력사항입니다")
    private String nickName;
    @NotEmpty(message = "필수입력사항입니다")

    private String birth; //yymmdd
    @NotEmpty(message = "필수입력사항입니다")
    private String phoneNumber;
    @NotEmpty(message = "필수입력사항입니다")
    private String email;

    public User toEntity() {
        return new User(
                this.userId,
                this.userPw,
                this.nickName,
                this.birth,
                this.phoneNumber,
                this.email
        );
    }
}
