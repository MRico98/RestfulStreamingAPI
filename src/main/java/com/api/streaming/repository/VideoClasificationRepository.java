package com.api.streaming.repository;

import com.api.streaming.model.VideoClasification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface VideoClasificationRepository extends JpaRepository<VideoClasification,Integer> {
    Long deleteByVideoId(Integer id);
}
