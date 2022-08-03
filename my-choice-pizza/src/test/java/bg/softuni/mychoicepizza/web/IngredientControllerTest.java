package bg.softuni.mychoicepizza.web;

import bg.softuni.mychoicepizza.model.entity.CategoryEntity;
import bg.softuni.mychoicepizza.model.entity.IngredientEntity;
import bg.softuni.mychoicepizza.model.entity.UserEntity;
import bg.softuni.mychoicepizza.model.entity.enums.CategoryNameEnum;
import bg.softuni.mychoicepizza.repository.CategoryRepository;
import bg.softuni.mychoicepizza.repository.IngredientRepository;
import bg.softuni.mychoicepizza.repository.PictureRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
class IngredientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private CategoryEntity category;

    @AfterEach
    void tearDown() {
        ingredientRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @BeforeEach
    void setUp() {
        category = categoryRepository.save(new CategoryEntity().setName(CategoryNameEnum.ЗЕЛЕНЧУЦИ));
    }

    @Test
    void testOpenAddIngredient() throws Exception {
        mockMvc.
                perform(get("/ingredients/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-ingredient"));
    }

    @Test
    void testAddIngredient() throws Exception {
        MockMultipartFile multiPFImage = new MockMultipartFile("picture", "TEST_pic.png",
                "img/png", "Generate bytes to simulate a picture".getBytes());

        mockMvc.perform(multipart("/ingredients/add")
                        .file(multiPFImage)
                        .param("name", "ingredient")
                        .param("category", CategoryNameEnum.ЗЕЛЕНЧУЦИ.name())
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(1, ingredientRepository.count());

        Optional<IngredientEntity> newlyAddedIngredientOpt = ingredientRepository.findByName("ingredient");

        Assertions.assertTrue(newlyAddedIngredientOpt.isPresent());

        IngredientEntity newlyAddedIngredient = newlyAddedIngredientOpt.get();

        Assertions.assertEquals(CategoryNameEnum.ЗЕЛЕНЧУЦИ.name(), newlyAddedIngredient.getCategory().getName().name());
    }


}