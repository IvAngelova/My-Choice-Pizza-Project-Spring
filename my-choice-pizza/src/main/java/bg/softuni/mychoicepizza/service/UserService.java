package bg.softuni.mychoicepizza.service;

import bg.softuni.mychoicepizza.model.service.UserServiceModel;

public interface UserService {
    boolean isUsernameFree(String username);

    UserServiceModel registerUser(UserServiceModel userServiceModel);
}
