package com.cakefactory.service;

import com.cakefactory.model.Item;
import com.cakefactory.repository.CatalogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService implements CatalogService {

    private final CatalogRepository catalogRepository;

    public DatabaseService(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public List<Item> getItems() {
        return catalogRepository.findAll().stream()
                .map(en -> new Item(en.getSku(), en.getTitle(), en.getPrice()))
                .toList();
    }

    @Override
    public Item findItem(String sku) {
        var itemEntity = catalogRepository.findItemEntityBySku(sku);
        return new Item(itemEntity.getSku(), itemEntity.getTitle(), itemEntity.getPrice());
    }
}
