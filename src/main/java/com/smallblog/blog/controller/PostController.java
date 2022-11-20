package com.smallblog.blog.controller;


import com.smallblog.blog.entity.Account;
import com.smallblog.blog.entity.Post;
import com.smallblog.blog.repository.PostRepository;
import com.smallblog.blog.services.AccountService;
import com.smallblog.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    AccountService accountService;

    @Autowired
    PostRepository postRepository;

    @GetMapping("posts/{id}")
    public String getPost(@PathVariable Long id, Model model){
        //find post by id
        Optional<Post> optionalPost = postService.getById(id);
        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "post";
        }else{
            return "404";
        }

    }

    @GetMapping("/posts/new")
    public String createNewPost(Model model){
        Optional<Account> optionalAccount = accountService.findByEmail("amanda@mail.com");
        if(optionalAccount.isPresent()){
            Post post = new Post();
            post.setAccount(optionalAccount.get());
            model.addAttribute("post", post);

            return "post_new";
        }else{
            return "404";
        }
    }

    @PostMapping("/posts/new")
    public String saveNewPost(@ModelAttribute Post post){
        postService.savePost(post);

        return "redirect:/posts/" + post.getId();
    }


    @GetMapping("posts/{id}/edit")
    @PreAuthorize("isAuthenticated()") //want to make sure that its authenticated
    public String getPostForEdit(@PathVariable Long id, Model model){
        Optional<Post> optionalPost = postService.getById(id);
        if(optionalPost.isPresent()){

            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "post_edit";
        }else{

            return "404";
        }

    }

    @PostMapping("/posts/{id}")
    @PreAuthorize("isAuthenticated()")
    public String editPost(@PathVariable Long id, Post post){

        Optional<Post> optionalPost = postService.getById(id);
        if(optionalPost.isPresent()){
            //the post we want to edit is the post we found by id
            Post existingPost = optionalPost.get();

            existingPost.setTitle(post.getTitle());
            existingPost.setText(post.getText());

            postService.savePost(existingPost);
        }

        return"redirect:/";
    }


    @GetMapping("/posts/{id}/delete")
    @PreAuthorize("isAuthenticated()")
    public String toDeletePost(@PathVariable Long id){

        Optional<Post> optionalPost = postService.getById(id);
        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            postService.delete(post);

            return "redirect:/";
        }


        return"404";
    }
}
