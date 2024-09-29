package com.rookie.stack.im.user.service.impl;

import com.alibaba.nacos.shaded.com.google.common.base.Preconditions;
import com.rookie.stack.framework.common.constant.AuthConstants;
import com.rookie.stack.framework.common.domain.enums.DeletedEnum;
import com.rookie.stack.framework.common.domain.enums.UserStatusEnum;
import com.rookie.stack.framework.common.utils.AssertUtil;
import com.rookie.stack.framework.common.utils.id.SnowFlakeFactory;
import com.rookie.stack.im.context.holder.LoginUserContextHolder;
import com.rookie.stack.im.user.common.utils.ParamUtils;
import com.rookie.stack.im.user.dao.ImUserDao;
import com.rookie.stack.im.user.domain.entity.ImUser;
import com.rookie.stack.im.user.domain.model.req.UpdateUserInfoReq;
import com.rookie.stack.im.user.exception.UserParamsErrorEnum;
import com.rookie.stack.im.user.model.req.GetUserByPhoneReq;
import com.rookie.stack.im.user.model.req.RegisterUserReq;
import com.rookie.stack.im.user.model.req.UpdateUserPasswordReq;
import com.rookie.stack.im.user.model.resp.GetUserInfoResp;
import com.rookie.stack.im.user.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * @Classname UserServiceImpl
 * @Description 用户服务实现类
 * @Date 2024/9/27 15:50
 * @Created by liujiapeng
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private ImUserDao imUserDao;

    @Resource
    private TransactionTemplate transactionTemplate;


    @Override
    public void updateUserInfo(UpdateUserInfoReq req) {
        // 如果所有字段都为空，直接返回，避免不必要的数据库操作
        if (isAllFieldsNull(req)) {
            return; // 不更新，直接返回
        }
        // 标记是否有字段被更新
        AtomicBoolean isUpdated = new AtomicBoolean(false);
        // 获取用户 id
        Long userId = LoginUserContextHolder.getUserId();

        ImUser userByRookieId = imUserDao.getUserByRookieId(userId);
        AssertUtil.isNotEmpty(userByRookieId, "用户状态异常，请检查用户状态");

        // 动态更新各字段
        updateField(req.getAvatarUrl(), userByRookieId::setAvatar, isUpdated);
        updateField(req.getNickname(), userByRookieId::setNickname, isUpdated, this::validateNickname);
        updateField(req.getSex(), userByRookieId::setSex, isUpdated);
        updateField(req.getBirthday(), userByRookieId::setBirthday, isUpdated);
        updateField(req.getIntroduction(), userByRookieId::setIntroduction, isUpdated);

        // 如果有任何字段被更新，更新更新时间并保存
        if (isUpdated.get()) {
            // 设置更新时间
            userByRookieId.setUpdateTime(LocalDateTime.now());
            imUserDao.updateByPrimaryKey(userByRookieId);
        }
    }

    @Override
    public Long register(RegisterUserReq req) {
        ImUser user = imUserDao.getUserByPhone(req.getPhone());
        AssertUtil.isEmpty(user, "用户状态异常！");
        return transactionTemplate.execute(status -> {
            try {
                // 生成用户ID
                Long rookieId = SnowFlakeFactory.getSnowFlakeFromCache().nextId();
                ImUser build = ImUser.builder()
                        .rookieId(rookieId)
                        .phone(req.getPhone())
                        .nickname(AuthConstants.IM_USER_KEY_PREFIX + rookieId)
                        .status(UserStatusEnum.ENABLE.getValue())
                        .createTime(LocalDateTime.now())
                        .updateTime(LocalDateTime.now())
                        .isDeleted(DeletedEnum.NO.getValue())
                        .build();
                imUserDao.insertUser(build);
                return rookieId;
            } catch (Exception e) {
                status.setRollbackOnly(); // 标记事务为回滚
                log.error("==> 系统注册用户异常: ", e);
                return null;
            }
        });
    }

    @Override
    public GetUserInfoResp getUserByPhone(GetUserByPhoneReq req) {
        ImUser userByPhone = imUserDao.getUserByPhone(req.getPhone());
        if (Objects.isNull(userByPhone)) {
            return null;
        }
        return GetUserInfoResp.builder().
                id(userByPhone.getRookieId()).
                password(userByPhone.getPassword()).
                build();
    }

    @Override
    public void updatePassword(UpdateUserPasswordReq req) {
        // 获取当前请求对应的用户 ID
        Long userId = LoginUserContextHolder.getUserId();
        ImUser build = ImUser.builder()
                .id(req.getUserId())
                .rookieId(userId)
                .password(req.getEncodePassword())
                .updateTime(LocalDateTime.now())
                .build();
        imUserDao.updateByPrimaryKey(build);
    }

    @Override
    public GetUserInfoResp getUserByRookieId() {
        Long userId = LoginUserContextHolder.getUserId();
        ImUser userByRookieId = imUserDao.getUserByRookieId(userId);
        return GetUserInfoResp.builder().id(userByRookieId.getId()).password(userByRookieId.getPassword()).build();
    }

    private boolean isAllFieldsNull(UpdateUserInfoReq req) {
        // 检查所有字段是否为空
        return Optional.ofNullable(req.getAvatarUrl()).isEmpty() &&
                Optional.ofNullable(req.getNickname()).isEmpty() &&
                Optional.ofNullable(req.getSex()).isEmpty() &&
                Optional.ofNullable(req.getBirthday()).isEmpty() &&
                Optional.ofNullable(req.getIntroduction()).isEmpty();
    }

    private <T> void updateField(T value, Consumer<T> updateAction, AtomicBoolean isUpdated) {
        Optional.ofNullable(value).ifPresent(v -> {
            updateAction.accept(v);
            isUpdated.set(true);
        });
    }

    private <T> void updateField(T value, Consumer<T> updateAction, AtomicBoolean isUpdated, Consumer<T> validateAction) {
        Optional.ofNullable(value).ifPresent(v -> {
            validateAction.accept(v);
            updateAction.accept(v);
            isUpdated.set(true);
        });
    }

    private void validateNickname(String nickname) {
        Preconditions.checkArgument(
                ParamUtils.checkNickname(nickname),
                UserParamsErrorEnum.NICK_NAME_VALID_FAIL.getErrorMsg()
        );
    }
}
