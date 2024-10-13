package com.rookie.stack.im.friend.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rookie.stack.im.friend.domain.entity.UserApply;
import com.rookie.stack.im.friend.domain.enums.ApplyStatusEnum;
import com.rookie.stack.im.friend.domain.enums.ApplyTypeEnum;
import com.rookie.stack.im.friend.domain.mapper.UserApplyMapper;
import org.springframework.stereotype.Service;

/**
 * @Classname UserApplyDao
 * @Description TODO
 * @Date 2024/9/29 14:36
 * @Created by liujiapeng
 */
@Service
public class UserApplyDao extends ServiceImpl<UserApplyMapper, UserApply> {
    public UserApply getFriendApproving(Long uid, Long targetUid) {
        return lambdaQuery().eq(UserApply::getUid, uid)
                .eq(UserApply::getTargetId, targetUid)
                .eq(UserApply::getStatus, ApplyStatusEnum.WAIT_APPROVAL)
                .eq(UserApply::getType, ApplyTypeEnum.ADD_FRIEND.getCode())
                .one();
    }
}
