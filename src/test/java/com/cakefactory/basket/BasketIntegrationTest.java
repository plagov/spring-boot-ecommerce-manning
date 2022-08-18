package com.cakefactory.basket;

import com.cakefactory.client.BrowserClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class BasketIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void userShouldAddOneItemToBasket() throws IOException {
        var browserClient = new BrowserClient(mockMvc);

        browserClient.goToHomePage();
        browserClient.clickAddToBasket("Red Velvet");
        var basketTotal = browserClient.getBasketItems();

        assertThat(basketTotal).isEqualTo(1);
    }
}
