package com.codepresso.codepressoblog.mapper;

import com.codepresso.codepressoblog.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    Integer save(@Param("User") User user);
    User findOneByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
