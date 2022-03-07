package com.codepresso.codepressoblog.service;

import com.codepresso.codepressoblog.mapper.PostMapper;
import com.codepresso.codepressoblog.vo.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PostService {
    private PostMapper postMapper;

    public List<Post> getAllPost() {
        return postMapper.fineAll();
    }

    public List<Post> getPostListByPage(Integer page, Integer size) {
        return postMapper.findByPage(size, (page-1) * size);
    }

    public Post getPostById(Integer id) {
        return postMapper.findOne(id);
    }

    public boolean savePost(Post post) {
        Integer result = postMapper.save(post);
        return result == 1;
    }

    public boolean updatePost(Post post) {
        Integer result = postMapper.update(post);
        return result == 1;
    }
}
