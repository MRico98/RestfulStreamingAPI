package com.api.streaming.service;

import com.api.streaming.model.Clasification;
import com.api.streaming.model.Video;
import com.api.streaming.model.VideoClasification;

import java.util.ArrayList;

public interface VideoClasificationService {
    ArrayList<VideoClasification> storeMultipleVideoClasification(Video video, ArrayList<Clasification> clasificaciones);
    void deleteMultipleVideoClasification(Integer id);
}
