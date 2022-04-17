package com.inepp.common.exception.security;

public class HasNotLoginException extends  SecurityException{
    public HasNotLoginException(String message) {
        super(message);
    }
}
