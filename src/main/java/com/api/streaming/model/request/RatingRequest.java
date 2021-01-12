package com.api.streaming.model.request;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingRequest {

    //@NotEmpty
    //private int idUser;

    @NotEmpty
    private int video;

    @NotEmpty
    private Integer rating;

}
