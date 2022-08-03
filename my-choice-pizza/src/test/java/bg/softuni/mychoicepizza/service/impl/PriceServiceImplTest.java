package bg.softuni.mychoicepizza.service.impl;

import bg.softuni.mychoicepizza.exception.ObjectNotFoundException;
import bg.softuni.mychoicepizza.model.entity.PriceEntity;
import bg.softuni.mychoicepizza.model.entity.enums.SizeEnum;
import bg.softuni.mychoicepizza.model.service.PriceServiceModel;
import bg.softuni.mychoicepizza.model.view.PriceViewModel;
import bg.softuni.mychoicepizza.repository.PriceRepository;
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
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    private PriceEntity testPrice;

    private PriceServiceImpl serviceToTest;

    private ModelMapper modelMapper = new ModelMapper();

    @Mock
    private PriceRepository mockPriceRepository;


    @BeforeEach
    void setUp() {
        serviceToTest = new PriceServiceImpl(modelMapper, mockPriceRepository);

        testPrice = new PriceEntity()
                .setAdditionalProductPrice(new BigDecimal(1))
                .setBasePrice(new BigDecimal(20))
                .setPizzaSize(SizeEnum.ГОЛЯМА);
        testPrice.setId(1L);
    }

    @Test
    void testPriceByPizzaSizeNotFound() {
        assertThrows(
                ObjectNotFoundException.class,
                () -> serviceToTest.findPriceByPizzaSize(SizeEnum.ГОЛЯМА)
        );
    }

    @Test
    void testGetPriceByPizzaSize() {
        Mockito.when(mockPriceRepository.findByPizzaSize(testPrice.getPizzaSize())).
                thenReturn(Optional.of(testPrice));

        PriceViewModel actual = serviceToTest.findPriceByPizzaSize(testPrice.getPizzaSize());

        Assertions.assertEquals(actual.getBasePrice(), testPrice.getBasePrice());
        Assertions.assertEquals(actual.getAdditionalProductPrice(), testPrice.getAdditionalProductPrice());
        Assertions.assertEquals(actual.getPizzaSize(), testPrice.getPizzaSize());
    }

    @Test
    void testEditPrice() {
        Mockito.when(mockPriceRepository.findById(testPrice.getId())).
                thenReturn(Optional.of(testPrice));

        PriceServiceModel priceServiceModel = new PriceServiceModel()
                .setAdditionalProductPrice(new BigDecimal(2))
                .setBasePrice(new BigDecimal(40))
                .setId(testPrice.getId())
                .setPizzaSize(testPrice.getPizzaSize());

        PriceEntity updated = modelMapper.map(priceServiceModel, PriceEntity.class);

        Mockito.when(mockPriceRepository.save(any(PriceEntity.class))).thenReturn(updated);

        //Act
        PriceServiceModel editedPrice = serviceToTest.editPrice(priceServiceModel);

        assertThat(editedPrice).isNotNull();
        Assertions.assertEquals(editedPrice.getBasePrice(), priceServiceModel.getBasePrice());
        Assertions.assertEquals(editedPrice.getAdditionalProductPrice(), priceServiceModel.getAdditionalProductPrice());
        Assertions.assertEquals(editedPrice.getPizzaSize(), priceServiceModel.getPizzaSize());
        Assertions.assertEquals(editedPrice.getId(), priceServiceModel.getId());

    }

    @Test
    void testPriceByIdNotFound() {
        assertThrows(
                ObjectNotFoundException.class,
                () -> serviceToTest.findById(1L)
        );
    }

}