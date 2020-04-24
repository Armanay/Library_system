package com.example.library.config;

import com.example.library.server.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers(HttpMethod.POST,"/members/**").permitAll() //sign up
                .antMatchers(HttpMethod.DELETE,"/members/{id}/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/members/**").hasAnyAuthority("ADMIN", "Manager")
                .antMatchers(HttpMethod.POST,"/books/**").hasAnyAuthority("ADMIN","Manager")
                .antMatchers(HttpMethod.PATCH,"/books/{id}**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/borrowedBooks//{memeberId}/{bookId}").hasAnyAuthority("Manager")
                .antMatchers(HttpMethod.POST, "/returnedBooks//{memberId}/{bookId}").hasAnyAuthority("Manager")
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtTokenGeneratorFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(userService)
                .passwordEncoder((passwordEncoder()));
    }
}
