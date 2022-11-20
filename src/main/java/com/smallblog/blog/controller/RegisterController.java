package com.smallblog.blog.controller;


import com.smallblog.blog.entity.Account;
import com.smallblog.blog.services.AccountRepository;
import com.smallblog.blog.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    AccountService service;

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        Account account = new Account();
        model.addAttribute("account", account);

        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute Account account){

        service.save(account);
        return "redirect:/";
    }
}
