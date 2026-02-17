package com.enterprise.taskmanagement.exception;
 
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
