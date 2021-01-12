package com.api.streaming.repository;

import java.util.Optional;
import java.util.List;

import com.api.streaming.model.User;
import com.api.streaming.model.Rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer>{

    boolean existsByIdUserAndVideo(Integer userId, Integer video);

    public Optional<Rating> findByIdUserAndVideo(Integer userId, Integer video);

    List<Rating> findByVideo(Integer video);
}