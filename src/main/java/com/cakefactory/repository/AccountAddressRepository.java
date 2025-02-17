package com.cakefactory.repository;

import com.cakefactory.model.AccountAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountAddressRepository extends JpaRepository<AccountAddressEntity, String> {

    AccountAddressEntity getAccountAddressByEmail(String email);
}
