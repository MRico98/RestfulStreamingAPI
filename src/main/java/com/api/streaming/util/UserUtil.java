package com.api.streaming.util;

import com.api.streaming.exception.AccessDeniedException;
import com.api.streaming.model.User;
import com.api.streaming.model.Video;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {
    public static void checkUserAuthorization(User actualUser, Video video){
        if(!actualUser.getRole().getName().equals("ADMIN") && !actualUser.getId().equals(video.getAutor().getId())){
            throw new AccessDeniedException("El usuario no tiene permitido eliminar este video");
        }
    };

    public static User getActualSession(){
        User actualUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return actualUser;
    }
}
