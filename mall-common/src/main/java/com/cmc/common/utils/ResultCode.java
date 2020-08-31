package com.cmc.common.utils;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2020/8/17 9:43 下午
 */
public enum  ResultCode {
    SUCCESS(0, "操作成功"),
    UNKNOWN_EXCEPTION(10000,"系统未知异常"),
    VALIDATE_FAILED(10001, "参数检验失败"),
    UPLOAD_FAILED(10002,"文件上传失败"),
    UNKNOWN_ACCOUNT(10003,"用户不存在"),
    INCORRECT_CREDENTIALS(10004,"密码错误"),
    LOCKED_ACCOUNT(10005,"账户已锁定,请联系管理员"),
    CAPTCHA_ERROR(10006,"验证码错误"),
    LOGIN_FAILED(10007,"登陆失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");
    private int code;
    private String message;

    private ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
