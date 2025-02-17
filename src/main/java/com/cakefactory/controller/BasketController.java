package com.cakefactory.controller;

import com.cakefactory.service.AccountAddressService;
import com.cakefactory.service.BasketService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Map;

@Controller
public class BasketController {

    private final BasketService basketService;
    private final AccountAddressService addressService;

    public BasketController(BasketService basketService, AccountAddressService addressService) {
        this.basketService = basketService;
        this.addressService = addressService;
    }

    @PostMapping("/basket")
    public String addToBasket(@RequestParam String sku) {
        basketService.addItem(sku);
        return "redirect:/";
    }

    @GetMapping("/basket")
    public ModelAndView getBasketItems(Map<String, Object> model, Principal principal) {
        var basketItems = basketService.getBasketItems();
        model.put("basketItems", basketItems);

        if (principal != null) {
            var accountAddress = addressService.getAccountAddress(principal.getName());
            model.put("addressLine1", accountAddress.addressLine1());
            model.put("addressLine2", accountAddress.addressLine2());
            model.put("postcode", accountAddress.postcode());
        }

        return new ModelAndView("basket", model);
    }

    @PostMapping("/basket/delete")
    public String deleteBasketItem(@RequestParam String sku) {
        basketService.deleteBasketItem(sku);
        return "redirect:/basket";
    }
}
