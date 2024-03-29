package bg.softuni.mychoicepizza.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser("pesho")
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testOpenCartPage() throws Exception {

        mockMvc.perform(get("/cart")).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("pizzas")).
                andExpect(model().attributeExists("estimatedTotal")).
                andExpect(model().attributeExists("estimatedEndPrice")).
                andExpect(view().name("shopping-cart"));
    }
}