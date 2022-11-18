package com.smallblog.blog.services;


import com.smallblog.blog.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PostService {

    @Autowired
    PostRepository repository;

    public Optional<Post> getById(Long id){
        return repository.findById(id);
    }

    public List<Post> getAll(){

        return repository.findAll();
    }

    public Post savePost(Post post){
        if(post.getId()== null){
            post.setCreatedAt(LocalDateTime.now());
        }
        return repository.save(post);
    }
}
