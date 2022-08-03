package bg.softuni.mychoicepizza.web;

import bg.softuni.mychoicepizza.model.entity.UserEntity;
import bg.softuni.mychoicepizza.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void testOpenRegisterForm() throws Exception {
        mockMvc.
                perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void testRegisterUser() throws Exception {
        // delete the administrator, initialized in DB via admin-panel using CommandLineRunner interface
        userRepository.deleteAll();

        mockMvc.perform(post("/users/register").
                        param("username", "pesho").
                        param("fullName", "Pesho Petrov").
                        param("address", "address").
                        param("phoneNumber", "0000000000").
                        param("password", "12345").
                        param("confirmPassword", "12345").
                        with(csrf()).
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)
                ).
                andExpect(status().is3xxRedirection());

        Assertions.assertEquals(1, userRepository.count());

        Optional<UserEntity> newlyCreatedUserOpt = userRepository.findByUsername("pesho");

        Assertions.assertTrue(newlyCreatedUserOpt.isPresent());

        UserEntity newlyCreatedUser = newlyCreatedUserOpt.get();

        Assertions.assertEquals("Pesho Petrov", newlyCreatedUser.getFullName());
        Assertions.assertEquals("address", newlyCreatedUser.getAddress());
        Assertions.assertEquals("0000000000", newlyCreatedUser.getPhoneNumber());
    }

    @Test
    @WithMockUser("pesho")
    void testOpenUserProfile() throws Exception {
        UserEntity user = new UserEntity();
        user.setPassword("password");
        user.setUsername("pesho");
        user.setAddress("address");
        user.setFullName("Pesho Petrov");
        user.setPhoneNumber("0000000000");

        userRepository.save(user);

        mockMvc.
                perform(get("/users/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-profile"));
    }


    @Test
    @WithMockUser("pesho")
    void testEditUserProfile() throws Exception {
        UserEntity user = new UserEntity();
        user.setPassword("password");
        user.setUsername("pesho");
        user.setAddress("address");
        user.setFullName("Pesho Petrov");
        user.setPhoneNumber("0000000000");

        UserEntity userEntity = userRepository.save(user);

        mockMvc.perform(patch("/users/profile").
                        param("id", String.valueOf(userEntity.getId())).
                        param("fullName", "new_fullName").
                        param("address", "new_address").
                        param("phoneNumber", "new_PhoneNumber").
                        with(csrf()).
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)
                ).
                andExpect(status().is3xxRedirection());

        Optional<UserEntity> editedUserOpt = userRepository.findById(userEntity.getId());

        Assertions.assertTrue(editedUserOpt.isPresent());

        UserEntity editedUser = editedUserOpt.get();

        Assertions.assertEquals("new_fullName", editedUser.getFullName());
        Assertions.assertEquals("new_address", editedUser.getAddress());
        Assertions.assertEquals("new_PhoneNumber", editedUser.getPhoneNumber());
        Assertions.assertEquals("pesho", editedUser.getUsername());
    }


}