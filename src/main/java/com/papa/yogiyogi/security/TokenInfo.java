package com.papa.yogiyogi.security;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenInfo {
    private Long id;
    private String nickName;
    private String birth; //yymmdd
    private String phoneNumber;
    private String email;
    public TokenInfo parseToken(Claims claims) {
        Long id = Long.parseLong(String.valueOf(claims.get("id")));
        String nick_name = (String) claims.get("nickName");
        String birth = (String) claims.get("birth");
        String phone_number = (String) claims.get("phoneNumber");
        String email = (String) claims.get("email");

        return new TokenInfo(id,nick_name,birth,phone_number,email);
    }
}

