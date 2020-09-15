package com.my.project.mybatis.mapper;

import com.my.project.mybatis.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("select * from user where id = #{id}")
    List<User> selectUserList(Long id, String name);
}
