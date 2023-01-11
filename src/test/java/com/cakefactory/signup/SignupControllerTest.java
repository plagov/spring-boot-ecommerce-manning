package com.cakefactory.signup;

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
class SignupControllerTest {

    @Autowired
    MockMvc mockMvc;

    private BrowserClient browserClient;

    @BeforeEach
    void setUp() {
        browserClient = new BrowserClient(mockMvc);
    }

    @Test
    void userShouldBeAbleToSignup() throws IOException {
        browserClient.goToHomePage();
        browserClient.openSignupPage();
        browserClient.fillInSignupForm("email",
                "password",
                "address line 1",
                "address line 2",
                "postcode");

        var signupCompleteMessage = browserClient.getSignupCompleteMessage();
        assertThat(signupCompleteMessage).isEqualTo("Your account details have been received successfully!");
    }
}
