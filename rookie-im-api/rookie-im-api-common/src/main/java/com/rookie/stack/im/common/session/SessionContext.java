package com.rookie.stack.im.common.session;

import com.rookie.stack.im.common.exception.BusinessException;
import com.rookie.stack.im.common.exception.HttpErrorEnum;
import com.rookie.stack.im.common.model.constants.IMApiConstants;
import com.rookie.stack.im.common.model.session.UserSessionInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author eumenides
 * @description
 * @date 2024/9/7
 */
public class SessionContext {

    public static UserSessionInfo getSession(){
        // 从请求上下文里获取Request对象
        ServletRequestAttributes requestAttributes = ServletRequestAttributes.class.
                cast(RequestContextHolder.getRequestAttributes());
        HttpServletRequest request = requestAttributes.getRequest();
        Object object = request.getAttribute(IMApiConstants.SESSION);
        if (object == null){
            throw new BusinessException(HttpErrorEnum.NO_LOGIN);
        }
        return  (UserSessionInfo) object;
    }
}
