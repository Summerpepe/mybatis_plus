package com.atguigu.mybatis_plus;

import com.atguigu.mybatis_plus.entity.User;
import com.atguigu.mybatis_plus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {
@Autowired
   private UserMapper userMapper;
    @Test
    void contextLoads() {
     List<User> users= userMapper.selectList(null);
      users.forEach(System.out::println);
    }

}
