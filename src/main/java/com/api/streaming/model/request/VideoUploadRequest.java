package com.api.streaming.model.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VideoUploadRequest {

    @NotEmpty
    private String titulo;

    @NotNull
    private MultipartFile video;

    private ArrayList<String> clasificaciones;

}
