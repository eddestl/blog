package com.smallblog.blog.config;

import com.smallblog.blog.entity.Account;
import com.smallblog.blog.entity.Post;
import com.smallblog.blog.services.AccountService;
import com.smallblog.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    PostService postService;
    @Autowired
    AccountService accountService;

    @Override
    public void run(String... args) throws Exception{
        List<Post> posts = postService.getAll();

        if(posts.size() == 0){

            Account account1 = new Account();

            account1.setFirstName("Amanda");
            account1.setLastName("Amandasson");
            account1.setEmail("amanda@mail.com");
            account1.setPassword("password");
            accountService.save(account1);

            Account account2 = new Account();
            account2.setFirstName("Admin");
            account2.setLastName("Adminsson");
            account2.setEmail("admin@mail.com");
            account2.setPassword("adminpassword");
            accountService.save(account2);

            Post post1 = new Post();
            post1.setTitle("title of post 1");
            post1.setText("text of post 1");
            post1.setAccount(account1);

            Post post2 = new Post();
            post2.setTitle("Title of post 2");
            post2.setText("Text of post 2");
            post2.setAccount(account2);

            postService.savePost(post1);
            postService.savePost(post2);
        }
    }

}
