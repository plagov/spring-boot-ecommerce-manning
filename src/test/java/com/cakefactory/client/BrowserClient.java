package com.cakefactory.client;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;

import java.io.IOException;
import java.util.List;

public class BrowserClient {

    private final Logger logger = LoggerFactory.getLogger(BrowserClient.class);

    private final WebClient webClient;
    private HtmlPage currentPage;

    public BrowserClient(MockMvc mockMvc) {
        this.webClient = MockMvcWebClientBuilder.mockMvcSetup(mockMvc).build();
    }

    public void goToHomePage() throws IOException {
        this.currentPage = this.webClient.getPage("http://localhost:8080/");
    }

    public void clickAddToBasket(String title) throws IOException {
        List<DomNode> itemCards = this.currentPage
                .getByXPath("//div[@data-testid='%s']".formatted(title));
        if (itemCards.size() != 1) {
            logger.warn("No item card found for {}", title);
            return;
        }

        HtmlElement addButton = itemCards.get(0).querySelector(".add-to-basket");
        this.currentPage = addButton.click();
    }

    public Integer getBasketItemsTotal() {
        try {
            String basketTotalValue = this.currentPage.querySelector(".basket-total").asNormalizedText();
            return Integer.parseInt(basketTotalValue);
        } catch (NumberFormatException | NullPointerException e) {
            return 0;
        }
    }

    public void goToBasket() throws IOException {
        this.currentPage = this.webClient.getPage("http://localhost/basket");
    }

    public String getBasketItemQtyLabel(String title) {
        DomNode itemRow = getBasketItemRow(title);
        if (itemRow == null) {
            return "";
        }

        return itemRow.querySelector(".qty").asNormalizedText();
    }

    public void clickRemoveFromBasket(String title) throws IOException {
        DomNode itemRow = getBasketItemRow(title);
        if (itemRow == null) {
            return;
        }

        HtmlElement deleteButton = itemRow.querySelector(".btn.remove-item");
        this.currentPage = deleteButton.click();
    }

    public void fillInAddress(String line1, String line2, String postcode) {
        setValue("#addressLine1", line1);
        setValue("#addressLine2", line2);
        setValue("#postcode", postcode);
    }

    public void completeOrder() throws IOException {
        HtmlElement completeOrderButton = this.currentPage.querySelector("#complete-order");
        this.currentPage = completeOrderButton.click();
    }

    public String pageText() {
        return this.currentPage.asNormalizedText();
    }

    private DomNode getBasketItemRow(String title) {
        List<DomNode> items = this.currentPage.getByXPath(String.format("//tr[descendant::td[text()='%s']]", title));
        if (items.size() != 1) {
            logger.warn("No item found with title {}", title);
            return null;
        }

        return items.get(0);
    }

    private void setValue(String selector, String value) {
        HtmlInput input = this.currentPage.querySelector(selector);
        input.setValueAttribute(value);
    }
}
