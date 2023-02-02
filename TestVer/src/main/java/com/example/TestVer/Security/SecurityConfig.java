package com.example.TestVer.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()//누구나접근
                .antMatchers("/userInfo").hasRole("USER")//user만 접근 가능
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/Main")
                .usernameParameter("id")
                .passwordParameter("pw")
                .and()
                .build();

    }
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/CSS/**");
        web.ignoring().antMatchers("/JS/**");
        web.ignoring().antMatchers("/image/**");
    }



    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }



}

