package com.cakefactory.service;

import com.cakefactory.model.BasketItem;

import java.util.List;

public interface BasketService {

    void addItem(String sku);

    int getBasketItemsCount();

    List<BasketItem> getBasketItems();

    void deleteBasketItem(String sku);
}
