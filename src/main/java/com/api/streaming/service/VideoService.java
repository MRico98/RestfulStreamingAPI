package com.api.streaming.service;

import com.api.streaming.model.Video;
import com.api.streaming.model.request.VideoEditRequest;
import com.api.streaming.model.request.VideoUploadRequest;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpRange;

public interface VideoService {
    Video storeVideo(VideoUploadRequest request);
    Pair<UrlResource, ResourceRegion> getVideoAndPartialContent(HttpRange rango, String id);
    Video getVideo(String id);
    Video editVideo(VideoEditRequest request);
}
