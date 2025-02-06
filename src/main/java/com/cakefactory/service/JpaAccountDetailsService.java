package com.cakefactory.service;

import com.cakefactory.model.AccountDetails;
import com.cakefactory.model.AccountDetailsEntity;
import com.cakefactory.repository.AccountDetailsRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaAccountDetailsService implements AccountDetailsService {

    private final AccountDetailsRepository accountDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    public JpaAccountDetailsService(
            AccountDetailsRepository accountDetailsRepository,
            PasswordEncoder passwordEncoder) {
        this.accountDetailsRepository = accountDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveAccountDetails(AccountDetails accountDetails) {
        accountDetailsRepository.save(
                new AccountDetailsEntity(
                        accountDetails.email(),
                        passwordEncoder.encode(accountDetails.password())));

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(accountDetails.email(), null, List.of());
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
