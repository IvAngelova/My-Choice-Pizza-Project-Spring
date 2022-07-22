package bg.softuni.mychoicepizza.service.impl;

import bg.softuni.mychoicepizza.model.entity.UserEntity;
import bg.softuni.mychoicepizza.model.entity.UserRoleEntity;
import bg.softuni.mychoicepizza.model.entity.enums.RoleNameEnum;
import bg.softuni.mychoicepizza.model.service.UserProfileServiceModel;
import bg.softuni.mychoicepizza.model.service.UserServiceModel;
import bg.softuni.mychoicepizza.model.view.UserViewModel;
import bg.softuni.mychoicepizza.repository.UserRepository;
import bg.softuni.mychoicepizza.repository.UserRoleRepository;
import bg.softuni.mychoicepizza.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isUsernameFree(String username) {
        return userRepository.findByUsernameIgnoreCase(username)
                .isEmpty();
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {

        UserRoleEntity userRole = userRoleRepository.findByRole(RoleNameEnum.USER);

        UserEntity newUser = modelMapper.map(userServiceModel, UserEntity.class);

        newUser
                .setPassword(passwordEncoder.encode(userServiceModel.getPassword()))
                .setRoles(Set.of(userRole))
                .setAddresses(List.of(userServiceModel.getAddress()));


        UserEntity savedUser = userRepository.save(newUser);

        return modelMapper.map(savedUser, UserServiceModel.class);
    }

    @Override
    public UserViewModel findUserByUsername(String username) {
        //todo
        return userRepository.findByUsername(username)
                .map(userEntity -> modelMapper.map(userEntity, UserViewModel.class))
                .orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public void editProfile(UserProfileServiceModel userProfileServiceModel) {
        //todo
        UserEntity userEntity = userRepository.findById(userProfileServiceModel.getId())
                .orElseThrow(() -> new IllegalArgumentException());

        if (!userProfileServiceModel.getNewAddress().trim().equals("")) {
            userEntity.getAddresses().add(userProfileServiceModel.getNewAddress());
        }
        userEntity.setFullName(userProfileServiceModel.getFullName())
                .setPhoneNumber(userProfileServiceModel.getPhoneNumber());

        userRepository.save(userEntity);

    }

    @Override
    public void initializeAdmin() {
        initializeRoles();
        if (userRepository.count() == 0) {
            UserRoleEntity adminRole = userRoleRepository.findByRole(RoleNameEnum.ADMIN);
            UserRoleEntity userRole = userRoleRepository.findByRole(RoleNameEnum.USER);

            UserEntity admin = new UserEntity();
            admin
                    .setUsername("admin")
                    .setFullName("Admin Adminov")
                    .setPassword(passwordEncoder.encode("11111"))
                    .setPhoneNumber("0898888888")
                    .setAddresses(List.of("ул. Родна стряха 56В"))
                    .setRoles(Set.of(adminRole, userRole));

            userRepository.save(admin);
        }
    }

    @Override
    public List<UserViewModel> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(userEntity -> {
                    UserViewModel userViewModel = modelMapper.map(userEntity, UserViewModel.class);
                    Set<RoleNameEnum> roles = new HashSet<>();
                    for (UserRoleEntity role : userEntity.getRoles()) {
                        roles.add(role.getRole());
                    }
                    userViewModel.setRoles(roles);
                    return userViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void changeRole(Long id) {
        //todo
        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        UserRoleEntity adminRole = userRoleRepository.findByRole(RoleNameEnum.ADMIN);
        UserRoleEntity userRole = userRoleRepository.findByRole(RoleNameEnum.USER);

        int size = userEntity.getRoles().size();
        if (size == 2) {
            userEntity.setRoles(Set.of(userRole));
        } else {
            userEntity.setRoles(Set.of(userRole, adminRole));
        }

        userRepository.save(userEntity);
    }

    private void initializeRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setRole(RoleNameEnum.ADMIN);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setRole(RoleNameEnum.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }
}
