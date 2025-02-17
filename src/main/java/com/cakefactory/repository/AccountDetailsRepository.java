package com.cakefactory.repository;

import com.cakefactory.model.AccountDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountDetailsRepository extends JpaRepository<AccountDetailsEntity, String> {

    Optional<AccountDetailsEntity> findByEmail(String email);
}
