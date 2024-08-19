package com.devvictor.spring_boot_refresh_token.exceptions;


import java.io.Serializable;
import java.util.Date;

public record ExceptionResponse(
        Date timestamp,
        String message,
        String details) implements Serializable {
}
