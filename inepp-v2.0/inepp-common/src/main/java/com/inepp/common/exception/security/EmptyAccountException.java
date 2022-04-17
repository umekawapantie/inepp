package com.inepp.common.exception.security;

/**
 * 账号为空异常
 */
public class EmptyAccountException extends SecurityException{
    public EmptyAccountException(String message) {
        super(message);
    }
}
