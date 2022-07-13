package com.cakefactory.service;

import com.cakefactory.model.Item;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class InMemoryCatalogService implements CatalogService{

    @Override
    public Iterable<Item> getItems() {
        return List.of(
                new Item("Test item 1", BigDecimal.ONE),
                new Item("Test item 2", BigDecimal.TEN),
                new Item("Test item 3", BigDecimal.valueOf(5))
        );
    }
}
