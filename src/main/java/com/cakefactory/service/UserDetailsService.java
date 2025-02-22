package com.cakefactory.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {

    UserDetails loadAccountByUsername(String username) throws UsernameNotFoundException;
}
