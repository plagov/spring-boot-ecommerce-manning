package com.cakefactory.controller;

import com.cakefactory.model.AccountAddress;
import com.cakefactory.service.AccountAddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        getExistingAddressDetails(model, principal);
        return new ModelAndView("account", model);
    }

    @GetMapping("/account/edit")
    public ModelAndView getEditAccountView(Map<String, Object> model, Principal principal) {
        getExistingAddressDetails(model, principal);
        return new ModelAndView("account-edit", model);
    }

    @PostMapping("/account")
    public String updateAccountAddress(
            @RequestParam String addressLine1,
            @RequestParam String addressLine2,
            @RequestParam String postcode,
            Principal principal) {
        if (principal != null) {
            var accountEmail = principal.getName();
            var updateAccountPayload = new AccountAddress(accountEmail, addressLine1, addressLine2, postcode);
            accountAddressService.saveAccountAddress(updateAccountPayload);
        }

        return "redirect:/account";
    }

    private void getExistingAddressDetails(Map<String, Object> model, Principal principal) {
        if (principal != null) {
            var accountAddress = accountAddressService.getAccountAddress(principal.getName());
            model.put("addressLine1", accountAddress.addressLine1());
            model.put("addressLine2", accountAddress.addressLine2());
            model.put("postcode", accountAddress.postcode());
        }
    }
}
