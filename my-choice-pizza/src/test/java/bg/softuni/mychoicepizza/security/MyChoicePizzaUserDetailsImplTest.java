package bg.softuni.mychoicepizza.security;

import bg.softuni.mychoicepizza.model.entity.UserEntity;
import bg.softuni.mychoicepizza.model.entity.UserRoleEntity;
import bg.softuni.mychoicepizza.model.entity.enums.RoleNameEnum;
import bg.softuni.mychoicepizza.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MyChoicePizzaUserDetailsImplTest {

    private UserEntity testUser;
    private UserRoleEntity adminRole, userRole;

    private MyChoicePizzaUserDetailsImpl serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp(){
        serviceToTest = new MyChoicePizzaUserDetailsImpl(mockUserRepository);

        adminRole = new UserRoleEntity().setRole(RoleNameEnum.ADMIN);

        userRole = new UserRoleEntity().setRole(RoleNameEnum.USER);

        testUser = new UserEntity()
                .setUsername("pesho")
                .setFullName("Pesho Petrov")
                .setPhoneNumber("0000000000")
                .setPassword("topsecret")
                .setAddress("address")
                .setRoles(Set.of(adminRole, userRole));
    }

    @Test
    void testUserNotFound() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("invalid_username")
        );
    }

    @Test
    void testUserFound() {
        // Arrange
        Mockito.when(mockUserRepository.findByUsername(testUser.getUsername())).
                thenReturn(Optional.of(testUser));

        // Act
       UserDetails actual = serviceToTest.loadUserByUsername(testUser.getUsername());

        // Assert
        Assertions.assertEquals(actual.getUsername(), testUser.getUsername());
        String actualRoles = actual.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(
                Collectors.joining(", "));
        String expectedRoles = "ROLE_ADMIN, ROLE_USER";
        Assertions.assertEquals(expectedRoles, actualRoles);
    }


}