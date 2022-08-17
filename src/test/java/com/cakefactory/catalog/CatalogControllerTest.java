package com.cakefactory.catalog;

import com.cakefactory.controller.CatalogController;
import com.cakefactory.model.Item;
import com.cakefactory.service.Basket;
import com.cakefactory.service.CatalogService;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = CatalogController.class)
@MockBeans({ @MockBean(Basket.class) })
public class CatalogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatalogService catalogService;

    private WebClient webClient;

    @BeforeEach
    void setUp() {
        this.webClient = MockMvcWebClientBuilder.mockMvcSetup(mockMvc).build();
    }

    @Test
    void returnCatalogPage() throws Exception {
        when(catalogService.getItems()).thenReturn(List.of(new Item("01", "Red Velvet", BigDecimal.valueOf(3))));

        HtmlPage page = webClient.getPage("http://localhost:8080/catalog");
        var actual = page.querySelectorAll(".item-title")
                .stream().map(DomNode::asNormalizedText).toList();

        assertThat(actual).contains("Red Velvet");
    }
}
