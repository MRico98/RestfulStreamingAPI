package com.api.streaming.model.request;

import com.api.streaming.model.Clasification;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

@Getter
@Setter
public class VideoEditRequest {

    private String titulo;

    private String description;

    private ArrayList<Clasification> clasificaciones;
}
