package com.papa.yogiyogi.security;

import io.jsonwebtoken.JwtException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class SecurityAspect {
    @Autowired
    SecurityService securityService;
    @Before("@annotation(tokenRequired)")
    public void authenticationWithToken(TokenRequired tokenRequired) {

        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        String token = (String) request.getHeader("authorization");
        if (token == null || token.isBlank()|| token.isEmpty()) throw new JwtException("token is null") ;
        if (securityService.parseToken(token).getId()==null)
            throw new JwtException("token err");
    }
}

