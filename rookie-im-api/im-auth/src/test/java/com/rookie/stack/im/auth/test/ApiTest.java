package com.rookie.stack.im.auth.test;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.rookie.stack.im.auth.domain.entity.User;
import com.rookie.stack.im.auth.domain.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author eumenides
 * @description
 * @date 2024/9/24
 */
@SpringBootTest
public class ApiTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.isTrue(5 == userList.size(), "");
        userList.forEach(System.out::println);
    }

}
