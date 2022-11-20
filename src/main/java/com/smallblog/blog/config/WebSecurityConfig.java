package com.smallblog.blog.config;


import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig {

    //What endpoints we want spring to allow us into
    private static final String[] WHITELIST = {
            "/register",
            "/h2-console/*",
            "/"
    };

    @Bean
    public static PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    /*
    injects a HttpSecurity object
    Build the URLs that we listen for in the application
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeRequests()
                .antMatchers(WHITELIST).permitAll()
                .antMatchers(HttpMethod.GET,"/posts/*").permitAll()
                .anyRequest().authenticated();

        httpSecurity
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .and()
                .httpBasic();



        //to allow h2
        httpSecurity
                .csrf().disable();
        //headers interfere with h2
        httpSecurity
                .headers().frameOptions().disable();
        //^Both^ can be removed when I choose another database than h2

        return httpSecurity.build();
    }
}
