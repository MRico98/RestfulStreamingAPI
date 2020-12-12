package com.api.streaming.service;

import com.api.streaming.model.User;
import com.api.streaming.model.dto.TokenDto;
import com.api.streaming.model.request.LoginUserRequest;


public interface UserService {
    public TokenDto loadUser(LoginUserRequest request);

    public User getUser(Integer id);
}
