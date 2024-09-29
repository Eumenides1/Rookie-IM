package com.rookie.stack.im.auth.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.rookie.stack.framework.common.constant.RedisKeyConstants;
import com.rookie.stack.framework.common.domain.enums.LoginTypeEnum;
import com.rookie.stack.framework.common.exception.BusinessException;
import com.rookie.stack.framework.common.exception.CommonErrorEnum;
import com.rookie.stack.framework.common.utils.AssertUtil;
import com.rookie.stack.framework.common.utils.JsonUtil;
import com.rookie.stack.im.auth.dao.AuthImUserDao;
import com.rookie.stack.im.auth.domain.entity.ImUser;
import com.rookie.stack.im.auth.domain.model.req.UpdatePasswordReq;
import com.rookie.stack.im.auth.domain.model.req.UserLoginReq;
import com.rookie.stack.im.auth.exception.AuthErrorEnum;
import com.rookie.stack.im.auth.rpc.UserRpcService;
import com.rookie.stack.im.auth.service.ImUserService;
import com.rookie.stack.im.context.holder.LoginUserContextHolder;
import com.rookie.stack.im.user.model.resp.GetUserByPhoneResp;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
* @author liujiapeng
* @description 针对表【im_user(IM用户表)】的数据库操作Service实现
* @createDate 2024-09-25 16:17:37
*/
@Service
@Slf4j
public class ImUserServiceImpl implements ImUserService {

    @Resource
    private AuthImUserDao userDao;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private UserRpcService userRpcService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public SaTokenInfo loginOrRegister(UserLoginReq req) {
        LoginTypeEnum loginTypeEnum = LoginTypeEnum.valueOf(req.getType());
        Long userId = null;
        switch (Objects.requireNonNull(loginTypeEnum,"不合法的登录/注册类型")){
            case VERIFICATION_CODE -> {
                userId = doVerificationCodeLogin(req);
            }
            case PASSWORD -> {
                userId = doPasswordLogin(req);
            }
        }
        // SaToken 登录用户, 入参为用户 ID
        StpUtil.login(userId);
        // 获取 Token 令牌
        return StpUtil.getTokenInfo();
    }

    private Long doPasswordLogin(UserLoginReq req) {
        Long userId;
        // 根据手机号获取用户
        GetUserByPhoneResp userByPhone = userRpcService.getUserByPhone(req.getPhone());
        // 解密密码与入参密码对比
        if (!(passwordEncoder.matches(req.getPassword(), userByPhone.getPassword()))) {
            throw new BusinessException(AuthErrorEnum.PASSWORD_NOT_MATCH);
        }
        userId = userByPhone.getId();
        return userId;
    }

    @Override
    public Long logout() {
        Long userId = LoginUserContextHolder.getUserId();
        log.info("==> 用户退出登录, userId: {}", userId);
        StpUtil.logout(userId);
        return userId;
    }

    @SneakyThrows
    @Override
    public void updatePassword(UpdatePasswordReq req) {
        // 获取当前请求对应的用户 ID
        Long userId = LoginUserContextHolder.getUserId();
        ImUser user = userDao.getUserByRookieId(userId);
        AssertUtil.isNotEmpty(user, "该用户状态异常，请确认");
        String password = passwordEncoder.encode(req.getNewPassword());
        user = ImUser.builder()
                .id(user.getId())
                .password(password)
                .updateTime(LocalDateTime.now())
                .build();
        userDao.updateByPrimaryKey(user);
    }

    private Long doVerificationCodeLogin(UserLoginReq req) {
        Long userId;
        // 校验入参验证码是否为空
        if (StringUtils.isBlank(req.getCode())) {
            throw new BusinessException(AuthErrorEnum.VERIFICATION_CODE_NOT_BLANK);
        }
        // 查询存储在 Redis 中该用户的登录验证码
        String code = (String) redisTemplate.opsForValue().get(RedisKeyConstants.buildVerificationCodeKey(req.getPhone()));
        // 判断用户提交的验证码，与 Redis 中的验证码是否一致
        if (!StringUtils.equals(req.getCode(), code)) {
            throw new BusinessException(AuthErrorEnum.VERIFICATION_CODE_ERROR);
        }
        GetUserByPhoneResp userByPhone = userRpcService.getUserByPhone(req.getPhone());
        log.info("==> 用户是否注册, phone: {}, userDO: {}", req.getPhone(), JsonUtil.toJsonString(userByPhone));
        // 判断是否注册
        if (Objects.isNull(userByPhone)) {
            // 若此用户还没有注册，系统自动注册该用户
            userId = userRpcService.registerUser(req.getPhone());
            if (Objects.isNull(userId)) {
                throw new BusinessException(CommonErrorEnum.SYSTEM_ERROR);
            }
        } else {
            // 已注册，则获取其用户 ID
            userId = userByPhone.getId();
        }
        return userId;
    }


}




