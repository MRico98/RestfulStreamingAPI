package com.api.streaming.service;

import com.api.streaming.model.User;
import com.api.streaming.model.dto.TokenDto;
import com.api.streaming.model.request.LoginUserRequest;
import com.api.streaming.model.request.RegisterUserRequest;
import com.api.streaming.model.Clasification;


public interface UserService {
    public TokenDto loadUser(LoginUserRequest request);

    public User getUser(Integer id);

    public User registerUser(RegisterUserRequest request);

    public String getRecommendations(Integer id);
}
