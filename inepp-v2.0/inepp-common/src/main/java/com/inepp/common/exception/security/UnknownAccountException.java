package com.inepp.common.exception.security;

public class UnknownAccountException extends SecurityException{
    public UnknownAccountException(String message) {
        super(message);
    }
}
