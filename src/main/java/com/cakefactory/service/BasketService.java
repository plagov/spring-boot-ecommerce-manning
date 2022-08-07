package com.cakefactory.service;

import com.cakefactory.model.Item;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BasketService implements Basket {

    private final Map<String, Item> items =new ConcurrentHashMap<>();

    private final CatalogService catalogService;

    public BasketService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Override
    public void addItem(String sku) {
        var item = catalogService.findItem(sku);
        items.put(sku, item);
    }
}
