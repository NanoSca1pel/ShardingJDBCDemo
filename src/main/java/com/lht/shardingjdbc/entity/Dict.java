package com.lht.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lhtao
 * @date 2020/8/20 16:12
 */
@Data
@TableName("t_dict")
public class Dict {

    private Long id;

    private Integer state;

    private String value;
}
