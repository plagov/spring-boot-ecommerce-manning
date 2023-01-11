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
    private HtmlPage signupPage;
    private HtmlPage signupCompletePage;

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
        this.currentPage = this.webClient.getPage("http://localhost:8080/basket");
    }

    public String getBasketItemQtyByTitle(String title) {
        DomNode itemRow = getBasketItemRow(title);
        if (itemRow == null) {
            return "";
        }

        return itemRow.querySelector("[data-testid='quantity']").asNormalizedText();
    }

    public void clickRemoveFromBasket(String title) throws IOException {
        DomNode itemRow = getBasketItemRow(title);
        if (itemRow == null) {
            return;
        }

        HtmlElement deleteButton = itemRow.querySelector("input[value='Remove item']");
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
        List<DomNode> itemCards = this.currentPage.getByXPath("//tr[@data-testid='%s']".formatted(title));
        if (itemCards.size() != 1) {
            logger.warn("No item found with title {}", title);
            return null;
        }

        return itemCards.get(0);
    }

    private void setValue(String selector, String value) {
        HtmlInput input = this.currentPage.querySelector(selector);
        input.setValueAttribute(value);
    }

    public String getAlertText() {
        var alertElement = this.currentPage.querySelector(".alert");
        return alertElement.asNormalizedText();
    }

    public void openSignupPage() throws IOException {
        HtmlElement signUpLink = this.currentPage.querySelector("#signup-link");
        signupPage = signUpLink.click();
    }

    public void fillInSignupForm(String email,
                                 String password,
                                 String addressLine1,
                                 String addressLine2,
                                 String postcode) throws IOException {
        HtmlInput emailInput = this.signupPage.querySelector("input[name='email']");
        HtmlInput passwordInput = this.signupPage.querySelector("input[name='password']");
        HtmlInput address1Input = this.signupPage.querySelector("input[name='addressLine1']");
        HtmlInput address2Input = this.signupPage.querySelector("input[name='addressLine2']");
        HtmlInput postcodeInput = this.signupPage.querySelector("input[name='postcode']");

        emailInput.setValueAttribute(email);
        passwordInput.setValueAttribute(password);
        address1Input.setValueAttribute(addressLine1);
        address2Input.setValueAttribute(addressLine2);
        postcodeInput.setValueAttribute(postcode);

        HtmlElement signupButton = this.signupPage.querySelector("#signup-button");
        signupCompletePage = signupButton.click();
    }

    public String getSignupCompleteMessage() {
        HtmlElement signupCompleteMessage = this.signupCompletePage.querySelector("#signup-complete-message");
        return signupCompleteMessage.asNormalizedText();
    }
}
