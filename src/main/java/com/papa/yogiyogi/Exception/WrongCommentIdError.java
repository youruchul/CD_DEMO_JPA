package com.papa.yogiyogi.Exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;


public class WrongCommentIdError extends Exception{
    public WrongCommentIdError() {
    }
}
