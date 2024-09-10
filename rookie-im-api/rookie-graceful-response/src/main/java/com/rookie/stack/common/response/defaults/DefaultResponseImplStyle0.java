package com.rookie.stack.common.response.defaults;

import com.rookie.stack.common.response.data.Response;
import com.rookie.stack.common.response.data.ResponseStatus;

import java.util.Collections;

/**
 * @author eumenides
 * @description
 * @date 2024/9/10
 */
public class DefaultResponseImplStyle0 implements Response {

    private ResponseStatus status;

    private Object payload = Collections.emptyMap();

    public DefaultResponseImplStyle0() {
    }

    public DefaultResponseImplStyle0(Object payload) {
        this.payload = payload;
    }

    @Override
    public void setStatus(ResponseStatus responseStatus) {
        this.status = responseStatus;
    }

    @Override
    public ResponseStatus getStatus() {
        return status;
    }

    @Override
    public void setPayload(Object obj) {
        this.payload = obj;
    }

    @Override
    public Object getPayload() {
        return payload;
    }
}