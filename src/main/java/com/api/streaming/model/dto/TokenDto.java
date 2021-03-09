package com.api.streaming.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDto {

    private String token;
    private String type = "Bearer";
    
}
