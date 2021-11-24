package appl.config;

import appl.models.Role;
import appl.models.User;
import appl.service.UserService;
import appl.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInit {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @PostConstruct
    private void postConstruct() {
        /*Role user = new Role("ROLE_USER");
        Role admin = new Role("ROLE_ADMIN");*/

        userService.addUser(new User("cj", encoder.encode("cj24"),
                "Casey", "Jones", 24,
                new HashSet<Role>(Arrays.asList(new Role("ROLE_USER")))));
        userService.addUser(new User("dj", encoder.encode("dj103"),
                "Davy", "Jones", 103,
                new HashSet<Role>(Arrays.asList(new Role("ROLE_ADMIN")))));
        userService.addUser(new User("rh", encoder.encode("rh36"),
                "Robin", "Hood", 36,
                new HashSet<Role>(Arrays.asList(new Role("ROLE_ADMIN")))));
        userService.addUser(new User("fk", encoder.encode("fk45"),
                "Freddy", "Krueger", 45,
                new HashSet<Role>(Arrays.asList(new Role("ROLE_ADMIN"), new Role("ROLE_USER")))));
    }
}
