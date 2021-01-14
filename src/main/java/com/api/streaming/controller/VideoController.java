package com.api.streaming.controller;

import javax.validation.Valid;

import com.api.streaming.model.Video;
import com.api.streaming.model.request.VideoEditRequest;
import com.api.streaming.model.request.VideoUploadRequest;
import com.api.streaming.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.data.util.Pair;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/videos")
public class VideoController{

    @Autowired
    private VideoService videoService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Video> videoUpload(@ModelAttribute @Valid VideoUploadRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(videoService.storeVideo(request));
    }

    @GetMapping("/watch")
    public ResponseEntity<ResourceRegion> watchVideo(@RequestHeader HttpHeaders headers, @RequestParam String id){
        Pair<UrlResource,ResourceRegion> videoContent = videoService.getVideoAndPartialContent(headers.getRange().get(0),id);
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(videoContent.getFirst()).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(videoContent.getSecond());
    }
  
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Video> deleteVideo(@PathVariable("id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(videoService.deleteVideo(id));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Video> editVideo(@PathVariable("id") String id,@RequestBody VideoEditRequest videoEditRequest){
        return ResponseEntity.ok().body(videoService.editVideo(id,videoEditRequest));
    }

}
