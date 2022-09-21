package com.cakefactory.basket;

import com.cakefactory.client.BrowserClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class BasketIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    private BrowserClient browserClient;

    @BeforeEach
    void setUp() {
        browserClient = new BrowserClient(mockMvc);
    }

    @Test
    void userShouldAddOneItemToBasket() throws IOException {
        browserClient.goToHomePage();
        browserClient.clickAddToBasket("Red Velvet");
        var basketTotal = browserClient.getBasketItemsTotal();

        assertThat(basketTotal).isEqualTo(1);
    }

    @Test
    void userShouldAddTheSameItemTwice() throws IOException {
        browserClient.goToHomePage();
        browserClient.clickAddToBasket("Red Velvet");
        browserClient.clickAddToBasket("Red Velvet");
        var basketTotal = browserClient.getBasketItemsTotal();

        assertThat(basketTotal).isEqualTo(2);
    }

    @Test
    void userShouldAddDifferentItems() throws IOException {
        browserClient.goToHomePage();
        browserClient.clickAddToBasket("Red Velvet");
        browserClient.clickAddToBasket("Croissant with almonds");
        var basketTotal = browserClient.getBasketItemsTotal();

        assertThat(basketTotal).isEqualTo(2);
    }

    @Test
    void shouldAddItemsToBasketPerUserSession() throws IOException {
        browserClient.goToHomePage();
        browserClient.clickAddToBasket("Red Velvet");
        browserClient.clickAddToBasket("Croissant with almonds");

        var secondClient = new BrowserClient(mockMvc);
        secondClient.goToHomePage();
        secondClient.clickAddToBasket("Cheese cake");

        var firstBasket = browserClient.getBasketItemsTotal();
        var secondBasket = secondClient.getBasketItemsTotal();

        assertThat(firstBasket).isEqualTo(2);
        assertThat(secondBasket).isEqualTo(1);
    }

    @Test
    void userShouldSeeItemInBasket() throws IOException {
        var itemTitle = "Red Velvet";
        browserClient.goToHomePage();
        browserClient.clickAddToBasket(itemTitle);
        browserClient.goToBasket();

        var quantity = browserClient.getBasketItemQtyByTitle(itemTitle);

        assertThat(quantity).isEqualTo("1");
    }
}
