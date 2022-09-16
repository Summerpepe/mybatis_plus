package com.atguigu.mybatis_plus.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.FileFilter;
import java.util.Date;

/**
 * @Author Summerpepe
 * @Date 2022-09-06
 * @Version 1.0 
 */
@Data
public class User {

       // @TableId(type= IdType.ASSIGN_ID)//运用了雪花算法,如果是type= IdType.AUTO就是主键自增
        private Long id;
        private String name;
        private Integer age;
        private String email;

        @TableField(fill = FieldFill.INSERT)
        private Date createTime;

        @TableField(fill = FieldFill.INSERT_UPDATE)
        private  Date updateTime;
}
