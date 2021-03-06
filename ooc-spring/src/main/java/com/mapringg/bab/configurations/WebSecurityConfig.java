package com.mapringg.bab.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author mapring
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/user/add").permitAll()
                .antMatchers("/user/all").hasRole("ADMIN")
                .antMatchers("/user/find").permitAll()
                .antMatchers("/user/update").permitAll()
                .antMatchers("/user/login").authenticated().and()
                .httpBasic().and()
                .csrf().disable()
                .cors();
    }
}
