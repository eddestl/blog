package com.smallblog.blog.config;

import com.smallblog.blog.entity.Post;
import com.smallblog.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    PostService service;

    @Override
    public void run(String... args) throws Exception{
        List<Post> posts = service.getAll();

        if(posts.size() == 0){
            Post post1 = new Post();
            post1.setTitle("title of post 1");
            post1.setText("text of post 1");

            Post post2 = new Post();
            post2.setTitle("Title of post 2");
            post2.setText("Text of post 2");

            service.savePost(post1);
            service.savePost(post2);
        }
    }

}
