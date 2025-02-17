package com.cakefactory.configuration;

import com.cakefactory.model.AccountDetailsEntity;
import com.cakefactory.repository.AccountDetailsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(AccountDetailsRepository repository) {
        return email -> {
            AccountDetailsEntity account = repository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User with email %s not found".formatted(email)));


            return new User(
                    account.getEmail(),
                    account.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_USER"))
            );
        };
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers("/", "/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .and().build();
    }
}
