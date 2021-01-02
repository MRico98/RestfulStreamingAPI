package com.api.streaming.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class FailChargeException extends RuntimeException{
    public FailChargeException(){
        super("Fallo al cargar el video");
    }
}
