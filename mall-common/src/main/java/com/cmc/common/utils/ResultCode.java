package com.cmc.common.utils;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2020/8/17 9:43 下午
 */
public enum  ResultCode {
    UNKNOWN_EXCEPTION(10000,"系统未知异常"),
    VALIDATE_FAILED(10001, "参数检验失败"),
    UPLOAD_FAILED(10002,"文件上传失败"),
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),

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
