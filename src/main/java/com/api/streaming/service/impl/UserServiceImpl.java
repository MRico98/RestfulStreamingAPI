package com.api.streaming.service.impl;

import java.util.Optional;

import com.api.streaming.exception.NotFoundException;
import com.api.streaming.model.User;
import com.api.streaming.model.dto.TokenDto;
import com.api.streaming.model.request.LoginUserRequest;
import com.api.streaming.repository.UserRepository;
import com.api.streaming.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public TokenDto loadUser(LoginUserRequest request) {
        Optional<User> opt = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
        if (opt.isPresent()) {
            User user = opt.get();
            //Se crea el token
            TokenDto token = new TokenDto();
            token.setToken("Yo, this works");
            return token;
        }
        throw new NotFoundException();
    }
    
}
