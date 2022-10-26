package com.atguigu.mybatis_plus;

import com.atguigu.mybatis_plus.entity.Product;
import com.atguigu.mybatis_plus.entity.User;
import com.atguigu.mybatis_plus.mapper.ProductMapper;
import com.atguigu.mybatis_plus.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Test
    public void testSelectBatchIds(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);

    }

    @Test   //简单的条件查询
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","Jone");
        map.put("age",18);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);

    }
    @Test
    public void testSelectPage(){
     Page<User> page=new Page(1,5);
     Page<User> pageParam=userMapper.selectPage(page,null);
     List<User> records = pageParam.getRecords();
        records.forEach(System.out::println);
        System.out.println(pageParam.getPages());//总页数
        System.out.println(pageParam.getTotal());//总记录数
        System.out.println(pageParam.getCurrent());//当前页码
        System.out.println(pageParam.getSize());//每页记录数
        System.out.println(pageParam.hasNext());//是否有下一页
        System.out.println(pageParam.hasPrevious());//是否有上一页
    }

    @Test
    public void testSelectMapsPage(){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","name");

        Page<Map<String,Object>> page=new Page(1,5);
        Page<Map<String,Object>> pageParam = userMapper.selectMapsPage(page, queryWrapper);


        List<Map<String, Object>> records = pageParam.getRecords();

        records.forEach(System.out::println);
        System.out.println(pageParam.getPages());//总页数
        System.out.println(pageParam.getTotal());//总记录数
        System.out.println(pageParam.getCurrent());//当前页码
        System.out.println(pageParam.getSize());//每页记录数
        System.out.println(pageParam.hasNext());//是否有下一页
        System.out.println(pageParam.hasPrevious());//是否有上一页

    }

    @Test
    public void testDeleteById(){
       int result=userMapper.deleteById(5l);
        System.out.println("删除了"+result+"行");
    }
    //批量删除
    @Test
    public void testDeleteBatchByIds(){
        int result=userMapper.deleteBatchIds(Arrays.asList(3,4));
        System.out.println("删除了"+result+"行");
    }

    //条件删除
    @Test
    public void testDeleteByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","Summerpepe");
        map.put("age",23);
        int result=userMapper.deleteByMap(map);
        System.out.println("删除了"+result+"行");
    }

    //逻辑删除
    @Test
    public void testLogicDeleteById(){
        int result=userMapper.deleteById(1L);
        System.out.println("删除了"+result+"行");
    }
    @Test
    void testLogicSelectList(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }




}
