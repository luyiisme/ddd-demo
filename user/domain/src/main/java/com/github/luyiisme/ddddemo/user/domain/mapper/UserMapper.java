package com.github.luyiisme.ddddemo.user.domain.mapper;

import com.github.luyiisme.ddddemo.user.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: kevin.luy@antfin.com
 * @create: 2019-08-19 09:47
 **/
public interface UserMapper {

    @Select("SELECT * FROM user")
    List<User> getList();

    @Select("SELECT * FROM user where name=#{name}")
    User getUserByName(String name);
}
