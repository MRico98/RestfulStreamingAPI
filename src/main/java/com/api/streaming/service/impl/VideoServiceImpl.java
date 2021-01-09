package com.api.streaming.service.impl;

import com.api.streaming.config.StorageProperties;
import com.api.streaming.exception.FailChargeException;
import com.api.streaming.exception.IncorrectFileException;
import com.api.streaming.model.Clasification;
import com.api.streaming.model.User;
import com.api.streaming.model.Video;
import com.api.streaming.model.VideoClasification;
import com.api.streaming.service.UserService;
import com.api.streaming.service.VideoClasificationService;
import com.api.streaming.util.TokenGenerator;
import com.api.streaming.model.request.VideoUploadRequest;
import com.api.streaming.repository.UserRepository;
import com.api.streaming.repository.VideoRepository;
import com.api.streaming.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class VideoServiceImpl implements VideoService{

    private final Path rootLocation;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private VideoClasificationService videoClasificationService;

    @Autowired
    public VideoServiceImpl(StorageProperties storageProperties){
        this.rootLocation = Paths.get(storageProperties.getLocation());
    }

    @Override
    public Video storeVideo(VideoUploadRequest request){
        String extension = FilenameUtils.getExtension(request.getVideo().getOriginalFilename());
        if(!extension.equalsIgnoreCase("mp4")){
            throw new IncorrectFileException();
        }
        String videoId = TokenGenerator.generadorTokens();
        storageProcess(request.getVideo(),videoId);
        Video newVideo = createVideoEntity(request.getTitulo(),videoId);
        newVideo = videoRepository.save(newVideo);
        createClasificationEntities(newVideo,request.getClasificaciones());
        return newVideo;
    }

    private Video createVideoEntity(String titulo, String videoId){
        Video nuevoVideo = new Video();
        nuevoVideo.setId(videoId);
        nuevoVideo.setAutor(userService.getUser(getActualSessionId()));
        nuevoVideo.setTitulo(titulo);
        nuevoVideo.setLocation(this.rootLocation.toString() + videoId + ".mp4");
        return nuevoVideo;
    }

    private List<VideoClasification> createClasificationEntities(Video video,ArrayList<Clasification> clasificaciones){
        return videoClasificationService.storeMultipleVideoClasification(video,clasificaciones);
    }

    private void storageProcess(MultipartFile video,String videoId) {
        try {
            String path = videoId + ".mp4";
            Path destinationFile = this.rootLocation.resolve(Paths.get(Objects.requireNonNull(path))).normalize();
            InputStream inputStream = video.getInputStream();
            Files.copy(inputStream,destinationFile,StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FailChargeException();
        }
    }

    private int getActualSessionId(){
        User actualUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return actualUser.getId();
    }
}
