package com.api.streaming.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import com.api.streaming.model.Video;
import com.api.streaming.model.request.VideoUploadRequest;
import com.api.streaming.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

}
