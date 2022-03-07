package com.codepresso.codepressoblog.service;

import com.codepresso.codepressoblog.mapper.CommentMapper;
import com.codepresso.codepressoblog.vo.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CommentService {
    private CommentMapper commentMapper;

    public List<Comment> getCommentListByPostIdAndPage(Integer postId, Integer page, Integer size) {
        return commentMapper.findByPostIdAndPage(postId, size, (page-1) * size);
    }

    public Integer getCommentCountByPostId(Integer postId) {
        return commentMapper.count(postId);
    }

    public boolean saveComment(Comment comment) {
        Integer result = commentMapper.save(comment);
        return result == 1;
    }

    public boolean updateComment(Comment comment) {
        Integer result = commentMapper.update(comment);
        return result == 1;
    }

    public boolean deleteComment(Integer id) {
        Integer result = commentMapper.delete(id);
        return result == 1;
    }
}
