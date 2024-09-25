package com.rookie.stack.im.auth.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rookie.stack.im.auth.domain.entity.ImUser;
import com.rookie.stack.im.auth.domain.enums.UserStatusEnum;
import com.rookie.stack.im.auth.domain.mapper.ImUserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Classname ImUserDao
 * @Description TODO
 * @Date 2024/9/25 16:26
 * @Created by liujiapeng
 */
@Service
public class ImUserDao extends ServiceImpl<ImUserMapper, ImUser> {

    @Resource
    private ImUserMapper imUserMapper;

    public ImUser getUserByPhone(String phone) {
        LambdaQueryWrapper<ImUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ImUser::getPhone, phone);
        queryWrapper.eq(ImUser::getStatus, UserStatusEnum.ENABLE.getValue());
        return imUserMapper.selectOne(queryWrapper);
    }

    public int insertUser(ImUser user) {
        return imUserMapper.insert(user);
    }

}
