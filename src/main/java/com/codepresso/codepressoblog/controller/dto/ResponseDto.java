package com.codepresso.codepressoblog.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto<T> {
    Integer code;
    String message;
    T data;
}
