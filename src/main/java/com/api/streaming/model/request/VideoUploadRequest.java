package com.api.streaming.model.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class VideoUploadRequest {

    @NotEmpty
    private String titulo;

    @NotEmpty
    private MultipartFile video;

}
