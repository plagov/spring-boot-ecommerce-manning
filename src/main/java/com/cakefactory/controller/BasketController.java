package com.cakefactory.controller;

import com.cakefactory.service.Basket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BasketController {

    private final Basket basket;

    public BasketController(Basket basket) {
        this.basket = basket;
    }

    @PostMapping("/basket")
    public String addToBasket(@RequestParam String sku) {
        basket.addItem(sku);
        return "redirect:/catalog";
    }
}
