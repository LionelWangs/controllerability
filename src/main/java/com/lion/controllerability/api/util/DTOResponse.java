package com.lion.controllerability.api.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author wang.hongyu
 * @Version V1.02020/03/12
 **/
public class DTOResponse<T> extends ResponseBase {
    private T dto = null;

    public DTOResponse() {
    }

    public DTOResponse(int statusCode, String message) {
        super(statusCode, message);
    }

    public T getDto() {
        return this.dto;
    }

    public void setDto(T dto) {
        this.dto = dto;
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
