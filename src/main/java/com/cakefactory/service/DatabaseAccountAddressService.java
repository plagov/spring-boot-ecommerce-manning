package com.cakefactory.service;

import com.cakefactory.model.AccountAddress;
import com.cakefactory.model.AccountAddressEntity;
import com.cakefactory.repository.AccountAddressRepository;
import org.springframework.stereotype.Component;

@Component
public class DatabaseAccountAddressService implements AccountAddressService {

    private final AccountAddressRepository accountAddressRepository;

    public DatabaseAccountAddressService(AccountAddressRepository accountAddressRepository) {
        this.accountAddressRepository = accountAddressRepository;
    }

    @Override
    public void saveAccountAddress(AccountAddress accountAddress) {
        var entity = new AccountAddressEntity(accountAddress.email(),
                accountAddress.addressLine1(),
                accountAddress.addressLine2(),
                accountAddress.postcode());
        accountAddressRepository.save(entity);
    }

    @Override
    public AccountAddress getAccountAddress(String email) {
        var accountAddress = accountAddressRepository.getAccountAddressByEmail(email);
        return new AccountAddress(
                accountAddress.getEmail(),
                accountAddress.getAddressLine1(),
                accountAddress.getAddressLine2(),
                accountAddress.getPostcode());
    }
}
