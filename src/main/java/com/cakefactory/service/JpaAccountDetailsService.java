package com.cakefactory.service;

import com.cakefactory.model.AccountDetails;
import com.cakefactory.model.AccountDetailsEntity;
import com.cakefactory.repository.AccountDetailsRepository;
import org.springframework.stereotype.Component;

@Component
public class JpaAccountDetailsService implements AccountDetailsService {

    private final AccountDetailsRepository accountDetailsRepository;

    public JpaAccountDetailsService(AccountDetailsRepository accountDetailsRepository) {
        this.accountDetailsRepository = accountDetailsRepository;
    }

    @Override
    public void saveAccountDetails(AccountDetails accountDetails) {
        var entity = new AccountDetailsEntity(accountDetails.email(), accountDetails.password());
        accountDetailsRepository.save(entity);
    }
}
