package bg.softuni.mychoicepizza.service.impl;

import bg.softuni.mychoicepizza.model.entity.CartItemEntity;
import bg.softuni.mychoicepizza.model.entity.PriceEntity;
import bg.softuni.mychoicepizza.model.entity.UserEntity;
import bg.softuni.mychoicepizza.model.entity.enums.PizzaBaseEnum;
import bg.softuni.mychoicepizza.model.entity.enums.SizeEnum;
import bg.softuni.mychoicepizza.model.service.PizzaServiceModel;
import bg.softuni.mychoicepizza.repository.CartItemRepository;
import bg.softuni.mychoicepizza.repository.IngredientRepository;
import bg.softuni.mychoicepizza.repository.PriceRepository;
import bg.softuni.mychoicepizza.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    private UserEntity testUser;

    private PriceEntity testPrice;

    private CartServiceImpl serviceToTest;

    private ModelMapper modelMapper = new ModelMapper();

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private CartItemRepository mockCartItemRepository;

    @Mock
    private IngredientRepository mockIngredientRepository;

    @Mock
    private PriceRepository mockPriceRepository;


    @BeforeEach
    void setUp() {
        serviceToTest = new CartServiceImpl(mockIngredientRepository, mockUserRepository, mockPriceRepository, modelMapper, mockCartItemRepository);

        testUser = new UserEntity()
                .setUsername("pesho")
                .setFullName("Pesho Petrov")
                .setPhoneNumber("0000000000")
                .setPassword("topsecret")
                .setAddress("address");

        testPrice = new PriceEntity()
                .setPizzaSize(SizeEnum.ГОЛЯМА)
                .setBasePrice(new BigDecimal(20))
                .setAdditionalProductPrice(new BigDecimal(1));
    }

    @Test
    void testAddNewCartItem() {

        Mockito.when(mockUserRepository.findByUsername(testUser.getUsername())).
                thenReturn(Optional.of(testUser));

        Mockito.when(mockPriceRepository.findByPizzaSize(testPrice.getPizzaSize())).
                thenReturn(Optional.of(testPrice));

        PizzaServiceModel pizzaServiceModel = new PizzaServiceModel()
                .setBase(PizzaBaseEnum.ДОМАТЕНА)
                .setSize(SizeEnum.ГОЛЯМА);

        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setQuantity(1)
                .setBase(pizzaServiceModel.getBase())
                .setSize(pizzaServiceModel.getSize())
                .setUser(testUser)
                .setPrice(new BigDecimal(20));

        Mockito.when(mockCartItemRepository.save(any(CartItemEntity.class))).thenReturn(cartItemEntity);

        CartItemEntity saved = serviceToTest.addNewCartItem(pizzaServiceModel, testUser.getUsername());

        Assertions.assertNotNull(saved);
        Assertions.assertEquals(0, saved.getIngredients().size());
        Assertions.assertEquals(saved.getUser(), cartItemEntity.getUser());
        Assertions.assertEquals(saved.getBase(), cartItemEntity.getBase());
        Assertions.assertEquals(saved.getSize(), cartItemEntity.getSize());

    }

}