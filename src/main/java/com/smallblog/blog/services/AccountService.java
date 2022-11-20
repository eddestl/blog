package com.smallblog.blog.services;

import com.smallblog.blog.entity.Account;
import com.smallblog.blog.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AccountRepository repository;


    public Account save(Account account){

        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return repository.saveAndFlush(account);
    }


    public Optional<Account> findByEmail(String email){

        return repository.findOneByEmail(email);
    }
}
