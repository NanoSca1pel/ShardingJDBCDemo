package com.lht.shardingjdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lht.shardingjdbc.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author lhtao
 * @date 2020/8/20 14:35
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
