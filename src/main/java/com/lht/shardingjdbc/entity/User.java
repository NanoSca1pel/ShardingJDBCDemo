package com.lht.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lhtao
 * @date 2020/8/20 14:33
 */
@Data
@TableName("t_user")
public class User {

    private Long id;

    private String username;

    private Integer state;
}
