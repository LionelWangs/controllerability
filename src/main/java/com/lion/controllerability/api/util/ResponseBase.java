package com.lion.controllerability.api.util;


import com.alibaba.fastjson.JSONObject;

/**
 * @Author wang.hongyu
 * @Version V1.02020/03/12
 **/
public class ResponseBase {
    public static final ResponseBase SUCCESS = new ResponseBase(100, "成功");
    public static final ResponseBase SERVICE_NOT_EXIST = new ResponseBase(200, "请求的服务不存在");
    public static final ResponseBase SERVICE_REQUEST_OUT_LIMIT = new ResponseBase(201, "请求次数超限或者过于频繁，暂时封禁");
    public static final ResponseBase SERVICE_FIELD_EXPECTED = new ResponseBase(202, "请求参数缺少必要字段");
    public static final ResponseBase PERMISSION_DENIED = new ResponseBase(300, "当前操作无权限");
    public static final ResponseBase PERMISSION_EXPIRED_TOKEN = new ResponseBase(301, "Token过期");
    public static final ResponseBase PERMISSION_INVALID_SIGN = new ResponseBase(302, "签名sign无效");
    public static final ResponseBase PERMISSION_INVALID_IP = new ResponseBase(303, "请求IP不被允许");
    public static final ResponseBase ERROR_NETWORK = new ResponseBase(400, "网络错误");
    public static final ResponseBase ERROR_LOGIC = new ResponseBase(401, "业务逻辑错误");
    public static final ResponseBase ERROR_DATABASE = new ResponseBase(402, "数据库错误");
    public static final ResponseBase ERROR_FILE_OPERATE = new ResponseBase(403, "文件操作错误");
    public static final ResponseBase DATA_NOT_FOUND = new ResponseBase(500, "记录未发现");
    public static final ResponseBase DATA_DUPLICATE = new ResponseBase(501, "数据库中存在重复的记录");
    public static final ResponseBase DATA_BLANK = new ResponseBase(502, "无数据");
    public static final ResponseBase DATA_PROTECTED = new ResponseBase(503, "系统保护数据，不可修改或删除");
    public static final ResponseBase USER_LOGIN_FAILED = new ResponseBase(1000, "账号或者密码错误");
    public static final ResponseBase USER_DISABLED = new ResponseBase(1001, "账号已经被禁用");
    public static final ResponseBase USER_IDENTITY_MISS = new ResponseBase(1002, "没有登录或者长时间未操作导致登录信息丢失");
    private int statusCode = 0;
    private String message = null;

    public ResponseBase() {
        this.statusCode = 100;
    }

    public ResponseBase(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ResponseBase set(ResponseBase responseBase) {
        this.setStatusCode(responseBase.statusCode);
        this.setMessage(responseBase.message);
        return this;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
