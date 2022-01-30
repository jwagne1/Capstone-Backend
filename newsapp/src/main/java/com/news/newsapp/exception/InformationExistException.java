package com.news.newsapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InformationExistException extends RuntimeException{
    public InformationExistException(String message){
        super(message);
    }
}
