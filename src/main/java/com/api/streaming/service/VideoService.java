package com.api.streaming.service;

import com.api.streaming.model.request.VideoUploadRequest;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {
    void storeVideo(VideoUploadRequest request);

}
