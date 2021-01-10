package com.api.streaming.repository;

import com.api.streaming.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video,Integer>{
    Optional<Video> findByIdSerializable(String idSerializable);
}
