package com.cakefactory.service;

import com.cakefactory.model.BasketItem;

import java.util.List;

public interface Basket {

    void addItem(String sku);

    int getBasketItemsCount();

    List<BasketItem> getBasketItems();
}
