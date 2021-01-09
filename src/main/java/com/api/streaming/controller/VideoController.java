package com.api.streaming.controller;

import javax.validation.Valid;

import com.api.streaming.model.Video;
import com.api.streaming.model.request.VideoUploadRequest;
import com.api.streaming.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.data.util.Pair;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VideoController{

    @Autowired
    private VideoService videoService;

    @PostMapping(value = "/videoupload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Video> videoUpload(@ModelAttribute @Valid VideoUploadRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(videoService.storeVideo(request));
    }

    @GetMapping("/watch")
    public ResponseEntity<ResourceRegion> watchVideo(@RequestHeader("Range") HttpRange rango, @RequestParam String id){
        Pair<UrlResource,ResourceRegion> videoContent = videoService.getVideoAndPartialContent(rango,id);
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(videoContent.getFirst()).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(videoContent.getSecond());
    }

}
