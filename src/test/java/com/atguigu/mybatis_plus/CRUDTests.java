package com.atguigu.mybatis_plus;

import com.atguigu.mybatis_plus.entity.Product;
import com.atguigu.mybatis_plus.entity.User;
import com.atguigu.mybatis_plus.mapper.ProductMapper;
import com.atguigu.mybatis_plus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
@SpringBootTest
public class CRUDTests {
   @Autowired
    private UserMapper userMapper;
   @Autowired
    private ProductMapper productMapper;
   @Test
    public void testInsert(){
       User user =new User();
       user.setAge(23);
       user.setName("Summerpepe");
       user.setEmail("z1942693104@gmial.com");
       int result= userMapper.insert(user);
       System.out.println("影响的行数"+result);//影响的行数
       System.out.println("id"+user);//id自动回填

   }
   @Test
   public void testUpdateById(){
      User user = new User();
      user.setId(1567361058134188035L);
      user.setName("喵喵喵");
      user.setAge(28);
      int result=userMapper.updateById(user);
      System.out.println("影响行数"+result);

   }
    @Test
    public void testConcurrentUpdate() {
        //1、小李
        Product p1 = productMapper.selectById(1L);
        System.out.println("小李取出的价格：" + p1.getPrice());
        //2、小王
        Product p2 = productMapper.selectById(1L);
        System.out.println("小王取出的价格：" + p2.getPrice());
        //3、小李将 价格加了50元，存入了数据库
        p1.setPrice(p1.getPrice() + 50);
        productMapper.updateById(p1);
        //4、小王将商品减了30元，存入了数据库
        p2.setPrice(p2.getPrice() - 30);
        int result = productMapper.updateById(p2);
        if(result == 0){//更新失败，重试
            //重新获取数据
            p2 = productMapper.selectById(1L);
            //更新
            p2.setPrice(p2.getPrice() - 30);
            productMapper.updateById(p2);
        }
        //最后的结果
        Product p3 = productMapper.selectById(1L);
        System.out.println("最后的结果：" + p3.getPrice());
    }

}
