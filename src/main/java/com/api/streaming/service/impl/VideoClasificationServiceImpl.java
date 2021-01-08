package com.api.streaming.service.impl;

import com.api.streaming.model.Clasification;
import com.api.streaming.model.Video;
import com.api.streaming.model.VideoClasification;
import com.api.streaming.repository.VideoClasificationRepository;
import com.api.streaming.service.VideoClasificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VideoClasificationServiceImpl implements VideoClasificationService {

    @Autowired
    private VideoClasificationRepository videoClasificationRepository;

    @Override
    public ArrayList<VideoClasification> storeMultipleVideoClasification(Video video, ArrayList<Clasification> clasificaciones) {
        ArrayList<VideoClasification> videoClasifications = new ArrayList<>();
        clasificaciones.forEach((clasificacionVideo) -> {
            VideoClasification videoClasification = new VideoClasification();
            videoClasification.setVideo(video);
            videoClasification.setTag(clasificacionVideo.name());
            videoClasifications.add(videoClasification);
            videoClasificationRepository.save(videoClasification);
        });
        return videoClasifications;
    }

}
