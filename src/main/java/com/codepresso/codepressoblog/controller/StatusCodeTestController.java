package com.codepresso.codepressoblog.controller;

import com.codepresso.codepressoblog.controller.dto.ResponseDto;
import com.codepresso.codepressoblog.vo.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class StatusCodeTestController {
    @GetMapping("/status")
    public String getStatus(@RequestParam Integer id) {
        if(id == 0) {
            throw new NullPointerException();
        }

        return "Success";
    }

    @GetMapping("/status/response")
    public ResponseEntity<ResponseDto<String>> getResponse() {
        ResponseDto<String> responseDto = new ResponseDto<>(4001, "email is mandatory", "Fail");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
 }
