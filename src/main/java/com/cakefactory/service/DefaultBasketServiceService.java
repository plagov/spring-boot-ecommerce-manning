package com.cakefactory.service;

import com.cakefactory.model.BasketItem;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SessionScope
@Component
public class DefaultBasketServiceService implements BasketService {

    private final Map<String, BasketItem> basketItems = new ConcurrentHashMap<>();

    private final CatalogService catalogService;

    public DefaultBasketServiceService(CatalogService catalogService) {
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

    @Override
    public List<BasketItem> getBasketItems() {
        return basketItems.values().stream().toList();
    }

    @Override
    public void deleteBasketItem(String sku) {
        var basketItem = basketItems.get(sku);
        if (basketItem.quantity() == 1) {
            basketItems.remove(sku);
        } else {
            var quantity = basketItem.quantity() - 1;
            basketItems.replace(sku, new BasketItem(quantity, basketItem.item()));
        }
    }
}
