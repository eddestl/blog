package com.smallblog.blog.services;


import com.smallblog.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;


    public boolean validate(User user, String password) {
        return password.equals(user.getPassword());
    }


}
