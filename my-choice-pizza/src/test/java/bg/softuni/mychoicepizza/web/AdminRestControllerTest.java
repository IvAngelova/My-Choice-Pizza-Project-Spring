package bg.softuni.mychoicepizza.web;

import bg.softuni.mychoicepizza.model.entity.OrderEntity;
import bg.softuni.mychoicepizza.model.entity.PizzaEntity;
import bg.softuni.mychoicepizza.model.entity.UserEntity;
import bg.softuni.mychoicepizza.model.entity.enums.DeliveryEnum;
import bg.softuni.mychoicepizza.model.entity.enums.OrderStatusEnum;
import bg.softuni.mychoicepizza.model.entity.enums.PizzaBaseEnum;
import bg.softuni.mychoicepizza.model.entity.enums.SizeEnum;
import bg.softuni.mychoicepizza.repository.OrderRepository;
import bg.softuni.mychoicepizza.repository.PizzaRepository;
import bg.softuni.mychoicepizza.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
@SpringBootTest
@AutoConfigureMockMvc
class AdminRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    private UserEntity testUser;

    @BeforeEach
    void setUp() {
        testUser = new UserEntity();
        testUser.setPassword("password")
                .setUsername("pesho")
                .setAddress("address")
                .setFullName("Pesho Petrov")
                .setPhoneNumber("0000000000");

        testUser = userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() {
        orderRepository.deleteAll();
        pizzaRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testGetAllOrders() throws Exception {
        OrderEntity orderEntity = initOrders(initPizzas());

        mockMvc.perform(get("/admin/api/orders")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(1))).
                andExpect(jsonPath("$.[0].total", is("20,00"))).
                andExpect(jsonPath("$.[0].user.username", is("pesho"))).
                andExpect(jsonPath("$.[0].delivery", is("НА_МЯСТО")));
    }

    private OrderEntity initOrders(List<PizzaEntity> pizzas) {
        OrderEntity order = new OrderEntity();
        order.setStatus(OrderStatusEnum.ЧАКАЩА)
                .setTotal(new BigDecimal("20"))
                .setPizzas(pizzas)
                .setDelivery(DeliveryEnum.НА_МЯСТО)
                .setCreated(LocalDateTime.now())
                .setUser(testUser);
        return orderRepository.save(order);
    }

    private List<PizzaEntity> initPizzas() {
        return pizzaRepository.saveAll(List.of(new PizzaEntity()
                .setQuantity(1)
                .setBase(PizzaBaseEnum.ДОМАТЕНА)
                .setPrice(new BigDecimal("20"))
                .setSize(SizeEnum.ГОЛЯМА)
                .setUser(testUser)
                .setIngredients(new ArrayList<>())));
    }


}