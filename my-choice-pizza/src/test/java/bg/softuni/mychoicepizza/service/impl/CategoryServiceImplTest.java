package bg.softuni.mychoicepizza.service.impl;

import bg.softuni.mychoicepizza.exception.ObjectNotFoundException;
import bg.softuni.mychoicepizza.model.entity.CategoryEntity;
import bg.softuni.mychoicepizza.model.entity.enums.CategoryNameEnum;
import bg.softuni.mychoicepizza.model.view.CategoryViewModel;
import bg.softuni.mychoicepizza.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    private CategoryEntity testCategory;

    private CategoryServiceImpl serviceToTest;

    private ModelMapper modelMapper = new ModelMapper();

    @Mock
    private CategoryRepository mockCategoryRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new CategoryServiceImpl(mockCategoryRepository, modelMapper);

        testCategory = new CategoryEntity()
                .setName(CategoryNameEnum.ЗЕЛЕНЧУЦИ);
        testCategory.setId(1L);
    }

    @Test
    void testCategoryByNameNotFound() {
        assertThrows(
                ObjectNotFoundException.class,
                () -> serviceToTest.findCategoryByName(CategoryNameEnum.ЗЕЛЕНЧУЦИ)
        );
    }

    @Test
    void testGetCategoryByName() {
        Mockito.when(mockCategoryRepository.findByName(testCategory.getName())).
                thenReturn(Optional.of(testCategory));

        CategoryEntity actual = serviceToTest.findCategoryByName(testCategory.getName());

        assertThat(actual).isNotNull();
        Assertions.assertEquals(actual.getName(), testCategory.getName());
        Assertions.assertEquals(0, actual.getIngredients().size());

    }



    @Test
    void testGetAllCategories(){
        Mockito.when(mockCategoryRepository.findAll()).
                thenReturn(List.of(testCategory));

        List<CategoryViewModel> allCategories = serviceToTest.getAllCategories();

        assertThat(allCategories).isNotNull();
        Assertions.assertEquals(1, allCategories.size());

    }

}