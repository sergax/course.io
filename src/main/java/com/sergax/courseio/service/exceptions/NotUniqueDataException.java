package com.sergax.courseio.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "This entity already present")
public class NotUniqueDataException extends RuntimeException {
    public NotUniqueDataException(String message) {
        super(message);
    }
}
