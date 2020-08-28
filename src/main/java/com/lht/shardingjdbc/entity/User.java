package com.lht.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @author lhtao
 * @date 2020/8/20 14:33
 */
@Data
@TableName("t_user")
public class User extends Model<User> {

    @TableId(type= IdType.ASSIGN_ID)
    private Long id;

    private String username;

    private Integer state;
}
