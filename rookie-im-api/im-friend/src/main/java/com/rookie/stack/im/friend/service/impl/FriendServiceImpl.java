package com.rookie.stack.im.friend.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rookie.stack.framework.common.domain.model.req.PageBaseReq;
import com.rookie.stack.framework.common.domain.model.resp.PageBaseResp;
import com.rookie.stack.framework.common.exception.BusinessException;
import com.rookie.stack.framework.common.utils.AssertUtil;
import com.rookie.stack.im.context.holder.LoginUserContextHolder;
import com.rookie.stack.im.friend.dao.UserApplyDao;
import com.rookie.stack.im.friend.dao.UserFriendDao;
import com.rookie.stack.im.friend.domain.entity.UserApply;
import com.rookie.stack.im.friend.domain.entity.UserFriend;
import com.rookie.stack.im.friend.domain.model.req.FriendApplyReq;
import com.rookie.stack.im.friend.domain.model.req.FriendApproveReq;
import com.rookie.stack.im.friend.domain.model.req.FriendCheckReq;
import com.rookie.stack.im.friend.domain.model.resp.FriendApplyResp;
import com.rookie.stack.im.friend.domain.model.resp.FriendCheckResp;
import com.rookie.stack.im.friend.domain.model.resp.FriendUnreadResp;
import com.rookie.stack.im.friend.exception.FriendErrorEnum;
import com.rookie.stack.im.friend.service.FriendService;
import com.rookie.stack.im.friend.service.adapter.FriendAdapter;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.rookie.stack.im.friend.domain.enums.ApplyStatusEnum.WAIT_APPROVAL;

/**
 * @author eumenides
 * @description
 * @date 2024/10/13
 */
@Service
@Slf4j
public class FriendServiceImpl implements FriendService {
    @Resource
    private UserFriendDao userFriendDao;
    @Resource
    private UserApplyDao userApplyDao;
    @Override
    public FriendCheckResp check(FriendCheckReq request) {
        boolean isFriend = false;
        // 1. 获取用户的 id
        Long userId = LoginUserContextHolder.getUserId();
        // 2. 判断用户关系
        UserFriend userFriend = userFriendDao.getByFriend(userId, request.getFriendId());
        if (ObjectUtil.isNotEmpty(userFriend)) {
            isFriend = true;
        }
        return new FriendCheckResp(request.getFriendId(), isFriend);
    }

    // TODO 添加分布式锁
    @Override
    public void apply(FriendApplyReq req) {
        // 1. 获取用户的 id
        Long userId = LoginUserContextHolder.getUserId();
        if (Objects.equals(userId, req.getTargetUid())) {
            throw new BusinessException(FriendErrorEnum.CAN_NOT_ADD_YOURSELF);
        }
        // 判断是否已经是好友了
        UserFriend userFriend = userFriendDao.getByFriend(userId, req.getTargetUid());
        AssertUtil.isEmpty(userFriend,"你们已经是好友啦！");
        // 是否有等待审批的记录
        //是否有待审批的申请记录(自己的)
        UserApply selfApproving = userApplyDao.getFriendApproving(userId, req.getTargetUid());
        if (Objects.nonNull(selfApproving)) {
            log.info("已有好友申请记录,uid:{}, targetId:{}", userId, req.getTargetUid());
            return;
        }
        //是否有待审批的申请记录(别人请求自己的)
        UserApply friendApproving = userApplyDao.getFriendApproving(req.getTargetUid(), userId);
        if (Objects.nonNull(friendApproving)) {
            //  Spring AOP 的 AopContext.currentProxy() 来获取当前的代理对象，
            //  然后通过代理对象调用 applyApprove 方法
            // 某个方法调用在当前类中能够使用代理对象（如事务生效），就会用到这种方式。
            // 如果直接调用类中的方法（比如 this.applyApprove()），
            // Spring 的 AOP 机制是不会对这种内部调用生效的，
            // 所以需要通过 AopContext.currentProxy() 获取代理对象再进行方法调用。
            ((FriendService) AopContext.currentProxy()).applyApprove(new FriendApproveReq(friendApproving.getId()));
            return;
        }
        UserApply insert = FriendAdapter.buildFriendApply(userId, req);
        userApplyDao.save(insert);
        // TODO 发布好友申请事件
    }

    @Override
    public PageBaseResp<FriendApplyResp> pageApplyFriend(PageBaseReq req) {
        // 1. 获取用户的 id
        Long userId = LoginUserContextHolder.getUserId();
        IPage<UserApply> userApplyIPage = userApplyDao.friendApplyPage(userId, req.plusPage());
        // TODO MyBatis 分页查询 total bug
        if (CollectionUtil.isEmpty(userApplyIPage.getRecords())) {
            return PageBaseResp.empty();
        }
        //将这些申请列表设为已读
        readApples(userId, userApplyIPage);
        return PageBaseResp.init(userApplyIPage, FriendAdapter.buildFriendApplyList(userApplyIPage.getRecords()));
    }

    @Override
    public FriendUnreadResp unread() {
        Long userId = LoginUserContextHolder.getUserId();
        Integer unReadCount = userApplyDao.getUnReadCount(userId);
        return new FriendUnreadResp(unReadCount);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void applyApprove(FriendApproveReq request) {
        Long userId = LoginUserContextHolder.getUserId();
        UserApply userApply = userApplyDao.getById(request.getApplyId());
        AssertUtil.isNotEmpty(userApply, "不存在申请记录");
        // 如果记录中被添加者 id 和用户 id 不符，为非法申请
        AssertUtil.equal(userApply.getTargetId(), userId, "不存在申请记录");
        AssertUtil.equal(userApply.getStatus(), WAIT_APPROVAL.getCode(), "已同意好友申请");
        //同意申请
        userApplyDao.agree(request.getApplyId());
        //创建双方好友关系
        createFriend(userId, userApply.getUid());
        // TODO 创建一个聊天房间
        // TODO 发送好友添加成功消息推送
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFriend(Long friendUid) {
        Long userId = LoginUserContextHolder.getUserId();
        List<UserFriend> userFriends = userFriendDao.getUserFriend(userId, friendUid);
        if (CollectionUtil.isEmpty(userFriends)) {
            log.info("没有好友关系：{},{}", userId, friendUid);
            return;
        }
        List<Long> friendRecordIds = userFriends.stream().map(UserFriend::getId).collect(Collectors.toList());
        userFriendDao.removeByIds(friendRecordIds);
        //TODO 禁用好友聊天房间
    }

    private void readApples(Long uid, IPage<UserApply> userApplyIPage) {
        List<Long> applyIds = userApplyIPage.getRecords()
                .stream().map(UserApply::getId)
                .collect(Collectors.toList());
        userApplyDao.readApples(uid, applyIds);
    }

    private void createFriend(Long uid, Long targetUid) {
        UserFriend userFriend1 = new UserFriend();
        userFriend1.setUid(uid);
        userFriend1.setFriendUid(targetUid);
        UserFriend userFriend2 = new UserFriend();
        userFriend2.setUid(targetUid);
        userFriend2.setFriendUid(uid);
        userFriendDao.saveBatch(Lists.newArrayList(userFriend1, userFriend2));
    }
}
