package com.cakefactory.controller;

import com.cakefactory.model.AccountAddress;
import com.cakefactory.model.AccountDetails;
import com.cakefactory.service.AccountAddressService;
import com.cakefactory.service.AccountDetailsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignupController {

    private final AccountDetailsService accountDetailsService;
    private final AccountAddressService accountAddressService;

    private static final Logger LOGGER = LogManager.getLogger(SignupController.class.getName());

    public SignupController(AccountDetailsService accountDetailsService,
                            AccountAddressService accountAddressService) {
        this.accountDetailsService = accountDetailsService;
        this.accountAddressService = accountAddressService;
    }

    @GetMapping("/signup")
    public ModelAndView getSignup() {
        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    public String saveAccountData(@RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String addressLine1,
                                @RequestParam String addressLine2,
                                @RequestParam String postcode) {
        accountAddressService.saveAccountAddress(new AccountAddress(email, addressLine1, addressLine2, postcode));
        accountDetailsService.saveAccountDetails(new AccountDetails(email, password));
        LOGGER.info("Save user with email {}", email);
        return "redirect:/signup-complete";
    }
}
