package com.cakefactory.repository;

import com.cakefactory.model.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<ItemEntity, String> {
}
