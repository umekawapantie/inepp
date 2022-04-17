package com.inepp.common.exception.security;

public class HostUnauthorizedException extends SecurityException{
    public HostUnauthorizedException(String message) {
        super(message);
    }
}
