package com.cakefactory.service;

import com.cakefactory.model.Item;

public interface CatalogService {

    Iterable<Item> getItems();
}
