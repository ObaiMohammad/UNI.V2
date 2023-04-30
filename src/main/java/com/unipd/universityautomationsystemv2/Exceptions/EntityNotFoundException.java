package com.unipd.universityautomationsystemv2.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Some parameters are invalid")

public class EntityNotFoundException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public EntityNotFoundException(String message) {
            super(message);
        }
    }

