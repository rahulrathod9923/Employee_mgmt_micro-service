package com.employee.exceptions;


@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Resource Not found on server");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    //extra properties that you want to manage
}

