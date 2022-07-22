package bg.softuni.mychoicepizza.admin_panel;

import bg.softuni.mychoicepizza.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBAdminInit implements CommandLineRunner {

    private final UserService userService;

    public DBAdminInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initializeAdmin();
    }
}
