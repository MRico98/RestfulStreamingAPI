package com.api.streaming.repository;

import com.api.streaming.model.VideoClasification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoClasificationRepository extends JpaRepository<VideoClasification,Integer> {
}
