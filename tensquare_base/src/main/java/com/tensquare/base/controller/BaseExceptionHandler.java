package com.tensquare.base.controller;

import entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static entity.StatusCode.ERROR;

@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e) {
        e.printStackTrace();
        return new Result(false, ERROR, e.getMessage());
    }

}
