package com.inepp.common.dto;

/**
 * 系统响应枚举常量
 */
public enum RespEnum {
    EMPTY_ACCOUNT(4000, "账号为空"),
    UNKNOWN_ACCOUNT(4001, "账号不存在"),
    INCORRECT_CREDENTIALS(4002, "用户名|密码错误"),
    HOST_UNAUTHORIZED(4010, "权限异常"),
    LOGIN_SUCCESS(2001, "用户登录成功"),
    LOGIN_OUT_SUCCESS(2009,"退出登录 成功"),
    REGISTRY_SUCCESS(3001, "用户注册成功"),
    LOAD_ROLE_FROM_EXCEL(3010,"从excel中读取角色成功"),
    LOAD_PRIVS_FROM_EXCEL(3011,"从excel中读取权限成功"),
    LOAD_GP_FROM_EXCEL(3012,"从excel中读取授权成功"),
    ACCOUNT_ALREADY_EXIST(4100, "账号已存在"),
    CHANGE_PASSWORD_SUCCESS(3002,"修改密码成功"),
    HAS_NOT_LOGIN_ERROR(4200,"用户还没有登录")

    ;

    private final int code;
    private final String msg;

    RespEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
