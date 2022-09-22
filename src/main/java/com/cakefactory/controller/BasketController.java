package com.cakefactory.controller;

import com.cakefactory.service.Basket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class BasketController {

    private final Basket basket;

    public BasketController(Basket basket) {
        this.basket = basket;
    }

    @PostMapping("/basket")
    public String addToBasket(@RequestParam String sku) {
        basket.addItem(sku);
        return "redirect:/";
    }

    @GetMapping("/basket")
    public ModelAndView getBasketItems(Map<String, Object> model) {
        var basketItems = basket.getBasketItems();
        model.put("basketItems", basketItems);
        return new ModelAndView("basket", model);
    }

    @PostMapping("/basket/delete")
    public String deleteBasketItem(@RequestParam String sku) {
        basket.deleteBasketItem(sku);
        return "redirect:/basket";
    }
}
