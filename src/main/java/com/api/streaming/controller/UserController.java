package com.api.streaming.controller;

import javax.validation.Valid;

import com.api.streaming.model.dto.TokenDto;
import com.api.streaming.model.request.LoginUserRequest;
import com.api.streaming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> postMethodName(@RequestBody @Valid LoginUserRequest request) {
        return ResponseEntity.ok().body(userService.loadUser(request));
    }
    
    
}
