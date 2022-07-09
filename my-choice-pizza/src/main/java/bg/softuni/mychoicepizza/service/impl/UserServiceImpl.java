package bg.softuni.mychoicepizza.service.impl;

import bg.softuni.mychoicepizza.model.entity.UserEntity;
import bg.softuni.mychoicepizza.model.entity.UserRoleEntity;
import bg.softuni.mychoicepizza.model.entity.enums.RoleNameEnum;
import bg.softuni.mychoicepizza.model.service.UserServiceModel;
import bg.softuni.mychoicepizza.repository.UserRepository;
import bg.softuni.mychoicepizza.repository.UserRoleRepository;
import bg.softuni.mychoicepizza.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
}
