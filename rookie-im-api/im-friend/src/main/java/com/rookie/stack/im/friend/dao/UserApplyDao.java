package com.rookie.stack.im.friend.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rookie.stack.im.friend.domain.entity.UserApply;
import com.rookie.stack.im.friend.domain.enums.ApplyStatusEnum;
import com.rookie.stack.im.friend.domain.enums.ApplyTypeEnum;
import com.rookie.stack.im.friend.domain.mapper.UserApplyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.rookie.stack.im.friend.domain.enums.ApplyReadStatusEnum.READ;
import static com.rookie.stack.im.friend.domain.enums.ApplyReadStatusEnum.UNREAD;
import static com.rookie.stack.im.friend.domain.enums.ApplyStatusEnum.AGREE;

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
    public IPage<UserApply> friendApplyPage(Long uid, Page page) {
        return lambdaQuery()
                .eq(UserApply::getTargetId, uid)
                .eq(UserApply::getType, ApplyTypeEnum.ADD_FRIEND.getCode())
                .orderByDesc(UserApply::getCreateTime)
                .page(page);
    }

    public void readApples(Long uid, List<Long> applyIds) {
        lambdaUpdate()
                .set(UserApply::getReadStatus, READ.getCode())
                .eq(UserApply::getReadStatus, UNREAD.getCode())
                .in(UserApply::getId, applyIds)
                .eq(UserApply::getTargetId, uid)
                .update();
    }

    public Integer getUnReadCount(Long targetId) {
        return Math.toIntExact(lambdaQuery().eq(UserApply::getTargetId, targetId)
                .eq(UserApply::getReadStatus, UNREAD.getCode())
                .count());
    }

    public void agree(Long applyId) {
        lambdaUpdate().set(UserApply::getStatus, AGREE.getCode())
                .eq(UserApply::getId, applyId)
                .update();
    }

}
