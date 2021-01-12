package com.api.streaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.streaming.model.User;
import com.api.streaming.model.dto.TokenDto;
import com.api.streaming.model.request.LoginUserRequest;
import com.api.streaming.exception.NotFoundException;
import com.api.streaming.repository.RatingRepository;
import com.api.streaming.repository.UserRepository;
import com.api.streaming.repository.VideoRepository;
import com.api.streaming.service.RatingService;
import com.api.streaming.model.Rating;
import com.api.streaming.model.Video;
import com.api.streaming.model.request.RatingRequest;
import com.api.streaming.service.VideoService;
import java.util.Optional;
import java.util.List;
import java.util.LinkedList;


@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VideoRepository videoRepository;

    public Rating crearRating(Integer id, RatingRequest request){
        Rating rating = new Rating();

        validateCreateRating(id, request.getVideo());
        
        rating.setIdUser(id);
        rating.setVideo(request.getVideo());
        rating.setRating(request.getRating());
        Rating rate = ratingRepository.save(rating);

        actualizarRating(request.getVideo());
        
        return rate;
    }

    public Rating getRating(int userId, int videoId){
        //validateExistanceRating(videoId, userId);
        return ratingRepository.findByIdUserAndVideo(userId, videoId).get();
    }

    public void actualizarRating(int videoId){
        Optional<Video> video = videoRepository.findById(videoId);

        List<Rating> ratingList = ratingRepository.findByVideo(videoId);

        int n = 0;
        int total = 0;

        if (ratingList.size() == 0){
            return;
        }

        for (int i = 0; i < ratingList.size(); i++) {
            total += ratingList.get(i).getRating();
            n += 1;
        }

        if (n != 0 && total != 0){
            video.get().setRating(total/n);
        }
        else{
            video.get().setRating(0);
        }
        
    }

    public void validateCreateRating(int videoId, int userId){
        if( !videoRepository.existsById(videoId)){
            throw new NotFoundException("No se encontro el video");
        }
        if( !userRepository.existsById(userId)){
            throw new NotFoundException("No se encontro el usuario");
        }
        
    }
}