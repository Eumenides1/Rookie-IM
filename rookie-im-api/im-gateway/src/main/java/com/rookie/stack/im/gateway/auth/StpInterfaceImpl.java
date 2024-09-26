package com.rookie.stack.im.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;

import java.util.Collections;
import java.util.List;

/**
 * @Classname StpInterfaceImpl
 * @Description 自定义权限验证接口扩展
 * @Date 2024/9/26 14:26
 * @Created by liujiapeng
 */
public class StpInterfaceImpl implements StpInterface {
    @Override
    public List<String> getPermissionList(Object o, String s) {
        return Collections.emptyList();
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        return Collections.emptyList();
    }
}
