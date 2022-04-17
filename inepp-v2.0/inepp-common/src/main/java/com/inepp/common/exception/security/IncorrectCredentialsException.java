package com.inepp.common.exception.security;

public class IncorrectCredentialsException extends  SecurityException{
    public IncorrectCredentialsException(String message) {
        super(message);
    }
}
