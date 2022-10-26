package com.atguigu.mybatis_plus;

import com.atguigu.mybatis_plus.entity.User;
import com.atguigu.mybatis_plus.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ZhangWenjing
 * @Param:
 * @return:
 * @description:QueryWrapper
 */
@SpringBootTest
public class QueryWrapperTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testDelete(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        userQueryWrapper.eq("age",18);
        userQueryWrapper.gt("age",18).
                isNotNull("email").
                isNotNull("age");


        int result=userMapper.delete(userQueryWrapper);
        System.out.println("删除了"+result+"行");
    }

}
