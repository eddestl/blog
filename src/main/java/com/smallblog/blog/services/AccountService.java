package com.smallblog.blog.services;

import com.smallblog.blog.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository repository;


    public Account save(Account account){
        return repository.save(account);
    }


    public Optional<Account> findByEmail(String email){

        return repository.findOneByEmail(email);
    }
}
