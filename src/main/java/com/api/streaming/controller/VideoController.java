package com.api.streaming.controller;

import com.api.streaming.model.User;
import com.api.streaming.model.request.VideoUploadRequest;
import com.api.streaming.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class VideoController{

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService){
        this.videoService = videoService;
    }

    @PostMapping("/videoupload")
    public ResponseEntity<String/*VideoUploadRequest*/> videoUpload(@RequestHeader(value="Authorization") String jwtToken , @RequestPart String titulo, @RequestPart MultipartFile video){
        videoService.storeVideo(setVideoUploadRequest(titulo,video));
        return ResponseEntity.status(HttpStatus.CREATED).body("Aun no se que enviar");
    }

    private VideoUploadRequest setVideoUploadRequest(String titulo,MultipartFile video){
        VideoUploadRequest videoUploadRequest = new VideoUploadRequest();
        videoUploadRequest.setTitulo(titulo);
        videoUploadRequest.setVideo(video);
        return videoUploadRequest;
    }

}
