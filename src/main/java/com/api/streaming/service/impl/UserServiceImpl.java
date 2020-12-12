package com.api.streaming.service.impl;

import java.util.Optional;

import com.api.streaming.exception.NotFoundException;
import com.api.streaming.model.User;
import com.api.streaming.model.dto.TokenDto;
import com.api.streaming.model.request.LoginUserRequest;
import com.api.streaming.repository.UserRepository;
import com.api.streaming.service.UserService;
import com.api.streaming.util.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public TokenDto loadUser(LoginUserRequest request) {
        Optional<User> opt = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
        if (opt.isPresent()) {
            User user = opt.get();
            //Se crea el token
            TokenDto token = new TokenDto();
            token.setToken(jwtTokenUtil.generateToken(user));
            return token;
        }
        throw new NotFoundException();
    }

    @Override
    public User getUser(Integer id) {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            User user = opt.get();
            return user;
        }
        throw new NotFoundException();
    }
    
}
