package com.cakefactory.service;

import com.cakefactory.model.BasketItem;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BasketService implements Basket {

    private final Map<String, BasketItem> basketItems =new ConcurrentHashMap<>();

    private final CatalogService catalogService;

    public BasketService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Override
    public void addItem(String sku) {
        var item = catalogService.findItem(sku);
        if (basketItems.containsKey(sku)) {
            var quantity = basketItems.get(sku).quantity() + 1;
            basketItems.replace(sku, new BasketItem(quantity, item));
        } else {
            basketItems.put(sku, new BasketItem(1, item));
        }
    }

    @Override
    public int getBasketItemsCount() {
        return basketItems.values().stream().map(BasketItem::quantity).reduce(0, Integer::sum);
    }
}