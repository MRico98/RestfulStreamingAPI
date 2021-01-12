package com.api.streaming.service;

import com.api.streaming.model.Rating;
import com.api.streaming.model.request.RatingRequest;


public interface RatingService {

    public Rating crearRating(Integer id, RatingRequest request);

    public Rating getRating(int userId, int videoId);

    public void actualizarRating(int videoId);

    public void validateCreateRating(int videoId, int userId);

}