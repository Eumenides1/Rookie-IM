package com.rookie.stack.im.user.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rookie.stack.framework.common.domain.enums.UserStatusEnum;
import com.rookie.stack.im.user.domain.entity.ImUser;
import com.rookie.stack.im.user.domain.mapper.ImUserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Classname ImUserDao
 * @Description TODO
 * @Date 2024/9/27 15:51
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

    public ImUser getUserByRookieId(Long rookieId){
        LambdaQueryWrapper<ImUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ImUser::getRookieId, rookieId);
        queryWrapper.eq(ImUser::getStatus, UserStatusEnum.ENABLE.getValue());
        return imUserMapper.selectOne(queryWrapper);
    }

    public void updateByPrimaryKey(ImUser user){
        imUserMapper.updateById(user);
    }

    public void insertUser(ImUser user) {
        imUserMapper.insert(user);
    }

}
