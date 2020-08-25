package com.lht.shardingjdbc.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lhtao
 * @date 2020/8/7 17:30
 */
@Data
@Accessors(chain = true)
public class Course {

    private Long id;

    private String name;

    private Long userId;

    private Integer state;
}
