package com.rookie.stack.common.response.defaults;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rookie.stack.common.response.data.Response;
import com.rookie.stack.common.response.data.ResponseStatus;

import java.util.Collections;

/**
 * @author eumenides
 * @description
 * @date 2024/9/10
 */
public class DefaultResponseImplStyle1 implements Response {
    private Object data = Collections.emptyMap();

    private ResponseStatus status;

    @Override
    public void setStatus(ResponseStatus statusLine) {
        this.status = statusLine;
    }

    @Override
    @JsonIgnore
    public ResponseStatus getStatus() {
        return status;
    }

    @Override
    public void setPayload(Object payload) {
        this.data = payload;
    }

    @Override
    @JsonIgnore
    public Object getPayload() {
        return this.data;
    }

    public String getCode() {
        return this.status.getCode();
    }

    public void setCode(String code) {
        this.status.setCode(code);
    }

    public String getMsg() {
        return this.status.getMsg();
    }

    public void setMsg(String msg) {
        this.status.setMsg(msg);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
