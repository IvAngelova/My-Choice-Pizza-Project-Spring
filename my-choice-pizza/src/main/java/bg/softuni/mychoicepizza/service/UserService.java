package bg.softuni.mychoicepizza.service;

import bg.softuni.mychoicepizza.model.service.UserProfileServiceModel;
import bg.softuni.mychoicepizza.model.service.UserServiceModel;
import bg.softuni.mychoicepizza.model.view.UserViewModel;

public interface UserService {
    boolean isUsernameFree(String username);

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserViewModel findUserByUsername(String username);

    void editProfile(UserProfileServiceModel userProfileServiceModel);

    void initializeAdmin();
}
