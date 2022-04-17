package com.inepp.common.exception.security;

public class AccountAlreadyExistException extends SecurityException{
    public AccountAlreadyExistException(String message) {
        super(message);
    }
}
