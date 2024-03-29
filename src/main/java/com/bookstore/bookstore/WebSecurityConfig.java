package com.bookstore.bookstore;

//import static method antMatcher
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bookstore.bookstore.web.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    @Autowired
	private UserDetailServiceImpl userDetailsService;


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.requestMatchers(antMatcher("/css/**")).permitAll()
                .requestMatchers(antMatcher("/delete/**")).hasRole("ADMIN")
                .anyRequest().authenticated())
            .formLogin(
                formlogin -> formlogin.loginPage("/login").defaultSuccessUrl("/index", true).permitAll())
            .logout(
                logout -> logout.permitAll()); 

        return http.build();
    }

    @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

}
