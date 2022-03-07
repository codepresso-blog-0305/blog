package com.codepresso.codepressoblog.mapper;

import com.codepresso.codepressoblog.vo.UserSession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserSessionMapper {
    Integer save(@Param("user_session") UserSession userSession);
    UserSession findOneById(@Param("id") Integer id);
    void delete(Integer id);
}
