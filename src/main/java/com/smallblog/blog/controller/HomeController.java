package com.smallblog.blog.controller;


import com.smallblog.blog.entity.Post;
import com.smallblog.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;

@Controller
public class HomeController {

    @Autowired
    PostService service;

    @GetMapping("/")
    public String home(Model model){
        List<Post> posts = service.getAll();

        model.addAttribute("posts", posts);

        return "home";
    }
}
