package com.codepresso.codepressoblog.mapper;

import com.codepresso.codepressoblog.vo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<Comment> findByPostIdAndPage(@Param("postId") Integer postId, @Param("limit") Integer limit, @Param("offset") Integer offset);
    Integer count(@Param("postId") Integer postId);
    Integer save(@Param("comment") Comment comment);
    Integer update(@Param("comment") Comment comment);
    Integer delete(@Param("id") Integer id);
}
