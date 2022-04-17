package com.inepp.security.service.handler;

import com.inepp.common.dto.HttpResp;
import com.inepp.common.dto.RespEnum;
import com.inepp.common.exception.security.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

/**
 * 异常捕获处理类(异常切面)
 */
@RestControllerAdvice
@Slf4j
public class AuthorityExceptionHandler {

    @ExceptionHandler
    public HttpResp emptyAccountHandler(EmptyAccountException e) {
        return new HttpResp(RespEnum.EMPTY_ACCOUNT.getCode(), e.getMessage(), null, LocalDate.now());
    }

    @ExceptionHandler
    public HttpResp unKnowAccountHandler(UnknownAccountException e) {
        return new HttpResp(RespEnum.UNKNOWN_ACCOUNT.getCode(), e.getMessage(), null, LocalDate.now());
    }

    @ExceptionHandler
    public HttpResp IncorrectCredentialsHandler(IncorrectCredentialsException e) {
        return new HttpResp(RespEnum.INCORRECT_CREDENTIALS.getCode(), e.getMessage(), null, LocalDate.now());
    }

    @ExceptionHandler
    public HttpResp HostUnauthorizedHandler(HostUnauthorizedException e) {
        return new HttpResp(RespEnum.HOST_UNAUTHORIZED.getCode(), e.getMessage(), null, LocalDate.now());
    }

    @ExceptionHandler
    public HttpResp hashNotLoginHandler(HasNotLoginException e) {
        return new HttpResp(RespEnum.HAS_NOT_LOGIN_ERROR.getCode(), RespEnum.HAS_NOT_LOGIN_ERROR.getMsg(), null, LocalDate.now());
    }

    @ExceptionHandler
    public HttpResp accountAlreadyHandler(AccountAlreadyExistException e){
        log.debug(e.getMessage());
        return new HttpResp(RespEnum.ACCOUNT_ALREADY_EXIST.getCode(), e.getMessage(),null,LocalDate.now());
    }

}
