package com.cakefactory.repository;

import com.cakefactory.model.AccountDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDetailsRepository extends JpaRepository<AccountDetailsEntity, String> {
}
