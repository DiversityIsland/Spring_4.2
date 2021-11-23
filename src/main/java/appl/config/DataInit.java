package appl.config;

import appl.models.User;
import appl.service.UserService;
import appl.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInit {
    @Autowired
    private UserService userService;

    @PostConstruct
    private void postConstruct() {
        userService.addUser(new User("Casey", "Jones", 24));
        userService.addUser(new User("Davy", "Jones", 103));
        userService.addUser(new User("Robin", "Hood", 36));
        userService.addUser(new User("Freddy", "Krueger", 45));
    }
}
