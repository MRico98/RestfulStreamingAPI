package com.api.streaming.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.api.streaming.model.Clasification;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    @Size(min=5, max = 15)
    private List<Clasification> preferences;

}


//Terminar lista de recomendaciones del usuario