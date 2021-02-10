package com.api.streaming.service;

import com.api.streaming.model.Video;
import com.api.streaming.model.request.VideoEditRequest;
import com.api.streaming.model.request.VideoUploadRequest;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpRange;

import java.util.List;

public interface VideoService {
    List<Video> searchVideos(String query);
    Video storeVideo(VideoUploadRequest request);
    Pair<UrlResource, ResourceRegion> getVideoAndPartialContent(HttpRange rango, String id);
    Video getVideo(String id);
    Video deleteVideo(String id);
    Video editVideo(String id,VideoEditRequest request);
}
