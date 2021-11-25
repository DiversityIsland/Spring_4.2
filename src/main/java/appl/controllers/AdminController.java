package appl.controllers;

import appl.config.DataInit;
import appl.models.Role;
import appl.models.User;
import appl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private DataInit init;

    @GetMapping("/new")
    public String newUser(Model model) {
        Boolean r_user = null;
        Boolean r_admin = null;

        model.addAttribute("user", new User());
        model.addAttribute("r_user", r_user);
        model.addAttribute("r_admin", r_admin);

        return "/admin/new";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user,
                          @ModelAttribute("r_user") Boolean r_user,
                          @ModelAttribute("r_admin") Boolean r_admin) {
        if (r_user) {
            user.getRoles().add(init.R_USER);
        }
        if (r_admin) {
            user.getRoles().add(init.R_ADMIN);
        }

        userService.addUser(user);

        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));

        return "admin/getUser";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        Boolean r_user = null;
        Boolean r_admin = null;
        User user = userService.getUser(id);

        if (user.getRoles().contains(new Role("ROLE_USER"))) {
            r_user = true;
        }
        if (user.getRoles().contains(new Role("ROLE_ADMIN"))) {
            r_admin = true;
        }
        model.addAttribute("user", user);
        model.addAttribute("r_user", r_user);
        model.addAttribute("r_admin", r_admin);

        return "admin/edit";
    }

    @PutMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @ModelAttribute("r_user") String r_user,
                             @ModelAttribute("r_admin") String r_admin,
                             @PathVariable("id") Long id) {
        if (r_user.equals("r_user")) {
            user.getRoles().add(init.R_USER);
        }
        if (r_admin.equals("r_admin")) {
            user.getRoles().add(init.R_ADMIN);
        }

        userService.updateUser(id, user);

        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);

        return "redirect:/admin";
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute(("users"), userService.getAllUsers());

        return "admin/getAllUsers";
    }
}
