package com.api.streaming.service.impl;

import java.util.Optional;

import javax.swing.text.html.Option;

import com.api.streaming.exception.NotFoundException;
import com.api.streaming.exception.AlreadyRegistered;
import com.api.streaming.model.User;
import com.api.streaming.model.UserPreferencesTags;
import com.api.streaming.model.dto.TokenDto;
import com.api.streaming.model.request.LoginUserRequest;
import com.api.streaming.model.request.RegisterUserRequest;
import com.api.streaming.repository.RolesRepository;
import com.api.streaming.repository.UserPreferencesTagsRepository;
import com.api.streaming.repository.UserRepository;
import com.api.streaming.service.UserService;
import com.api.streaming.util.JwtTokenUtil;
import com.api.streaming.model.Clasification;
import com.api.streaming.model.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository; 

    @Autowired
    private UserPreferencesTagsRepository userPreferencesTagsRepository; 

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
        throw new NotFoundException("El usuario no fue encontrado");
    }

    @Override
    public User getUser(Integer id) {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new NotFoundException();
    }

    @Override
    public User registerUser(RegisterUserRequest request){
        Optional<User> opt = userRepository.findByEmail(request.getEmail());
        if (!opt.isPresent()){
            //Para crear el nuevo usuario
            User nuevoUser = new User();
            nuevoUser.setName(request.getName());
            nuevoUser.setEmail(request.getEmail());
            nuevoUser.setPassword(request.getPassword());
            nuevoUser.setRole(rolesRepository.findByName("USER").get());
            nuevoUser = userRepository.save(nuevoUser);

            //Para agregar sus categorias preferidas
            UserPreferencesTags userTags = new UserPreferencesTags();
            userTags.setId(nuevoUser.getId());
            userTags.setUser(nuevoUser);
            //Solo meto la cadena con los enum
            userTags.setTag(request.getPreferences().toString());
            userTags = userPreferencesTagsRepository.save(userTags);
            return nuevoUser;
        }
        throw new AlreadyRegistered("Ya existe una cuenta asociada a ese correo");
    }

    @Override
    public String getRecommendations(Integer id){
        Optional<UserPreferencesTags> tags = userPreferencesTagsRepository.findById(id);
        if(tags.isPresent()){
            //Solo devuelvo el string, no está como array
            return tags.get().getTag();
        }
        throw new NotFoundException("No se encontraron tags asociadas al usuario");
    }
}
