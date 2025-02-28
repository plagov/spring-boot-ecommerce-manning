package com.cakefactory.controller;

import com.cakefactory.service.AccountAddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Map;

@Controller
public class AccountController {

    private final AccountAddressService accountAddressService;

    public AccountController(AccountAddressService accountAddressService) {
        this.accountAddressService = accountAddressService;
    }

    @GetMapping("/account")
    public ModelAndView getAccountAddress(Map<String, Object> model, Principal principal) {
        if (principal != null) {
            var accountAddress = accountAddressService.getAccountAddress(principal.getName());
            model.put("addressLine1", accountAddress.addressLine1());
            model.put("addressLine2", accountAddress.addressLine2());
            model.put("postcode", accountAddress.postcode());
        }

        return new ModelAndView("account", model);
    }
}
