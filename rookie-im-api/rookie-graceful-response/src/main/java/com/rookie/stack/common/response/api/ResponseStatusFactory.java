package com.rookie.stack.common.response.api;

import com.rookie.stack.common.response.data.ResponseStatus;

/**
 * @author eumenides
 * @description
 * @date 2024/9/10
 */
public interface ResponseStatusFactory {
    /**
     * 获得响应成功的ResponseMeta.
     *
     * @return
     */
    ResponseStatus defaultSuccess();

    /**
     * 获得失败的ResponseMeta.
     *
     * @return
     */
    ResponseStatus defaultError();


    /**
     * 从code和msg创建ResponseStatus
     * @param code
     * @param msg
     * @return
     */
    ResponseStatus newInstance(String code,String msg);
}
