package com.rookie.stack.im.api.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rookie.stack.im.api.user.dao.UserDao;
import com.rookie.stack.im.api.user.repository.UserRepository;
import com.rookie.stack.im.api.user.service.UserDomainService;
import com.rookie.stack.im.api.user.service.adapter.UserAdapter;
import com.rookie.stack.im.common.config.JwtProperties;
import com.rookie.stack.im.common.exception.BusinessException;
import com.rookie.stack.im.common.exception.HttpErrorEnum;
import com.rookie.stack.im.common.exception.UserErrorEnum;
import com.rookie.stack.im.common.model.dto.LoginDTO;
import com.rookie.stack.im.common.model.dto.RegisterDTO;
import com.rookie.stack.im.common.model.entity.User;
import com.rookie.stack.im.common.model.vo.LoginVO;
import com.rookie.stack.im.common.utils.AssertUtil;
import com.rookie.stack.im.common.utils.BeanUtils;
import com.rookie.stack.im.common.utils.id.SnowFlakeFactory;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author eumenides
 * @description
 * @date 2024/9/7
 */
@Service
public class UserDomainServiceImpl extends ServiceImpl<UserRepository, User> implements UserDomainService {
    private final Logger logger = LoggerFactory.getLogger(UserDomainServiceImpl.class);
    @Resource
    private UserDao userDao;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtProperties jwtProperties;
    @Override
    public LoginVO login(LoginDTO dto) {
        User user = userDao.getUserByUserName(dto.getUserName());
        AssertUtil.isNotEmpty(user, UserErrorEnum.USER_NOT_FOUND);
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new BusinessException(HttpErrorEnum.PASSWOR_ERROR);
        }
        return UserAdapter.userToLoginVo(user.getId(),
                dto.getTerminal(),
                user.getUserName(),
                user.getNickName(),
                jwtProperties);
    }

    @Override
    public void register(RegisterDTO dto) {
        User user = userDao.getUserByUserName(dto.getUserName());
        AssertUtil.isEmpty(user, UserErrorEnum.USER_EXIST,"");
        user = BeanUtils.copyProperties(dto, User.class);
        user.setId(SnowFlakeFactory.getSnowFlakeFromCache().nextId());
        user.setCreatedTime(new Date());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveOrUpdateUser(user);
        logger.info("注册用户，用户id:{},用户名:{},昵称:{}", user.getId(), dto.getUserName(), dto.getNickName());
    }

}
