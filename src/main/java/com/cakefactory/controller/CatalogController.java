package com.cakefactory.controller;

import com.cakefactory.service.CatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/catalog")
    public ModelAndView catalog(Map<String, Object> model) {
        var items = catalogService.getItems();
        model.put("items", items);
        model.put("page_title", "Cake factory items");
        return new ModelAndView("catalog", model);
    }
}
