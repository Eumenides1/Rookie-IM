package com.rookie.stack.im.api.user.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rookie.stack.im.api.user.repository.UserRepository;
import com.rookie.stack.im.common.model.entity.User;
import com.rookie.stack.im.common.model.enums.UserStatusEnum;
import org.springframework.stereotype.Service;

/**
 * @author eumenides
 * @description
 * @date 2024/9/7
 */
@Service
public class UserDao extends ServiceImpl<UserRepository, User> {
    public User getUserByUserName(String userName){
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getUserName, userName);
        queryWrapper.eq(User::getUserStatus, UserStatusEnum.NORMAL.getCode());
        return this.getOne(queryWrapper);
    }

    public boolean saveOrUpdateUser(User user){
        // TODO 待完善逻辑
        return this.saveOrUpdate(user);
    }
}
