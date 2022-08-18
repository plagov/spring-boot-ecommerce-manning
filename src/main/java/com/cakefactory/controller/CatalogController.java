package com.cakefactory.controller;

import com.cakefactory.service.Basket;
import com.cakefactory.service.CatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class CatalogController {

    private final CatalogService catalogService;
    private final Basket basketService;

    public CatalogController(CatalogService catalogService, Basket basketService) {
        this.catalogService = catalogService;
        this.basketService = basketService;
    }

    @GetMapping("/")
    public ModelAndView catalog(Map<String, Object> model) {
        var items = catalogService.getItems();
        var basketItemsCount = basketService.getBasketItemsCount();
        model.put("items", items);
        model.put("page_title", "Cake factory items");
        model.put("basketItemsCount", basketItemsCount);
        return new ModelAndView("catalog", model);
    }
}
