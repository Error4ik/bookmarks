package ru.voronin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.voronin.domain.User;
import ru.voronin.services.UserService;

import java.security.Principal;

/**
 * User controller.
 *
 * @author Alexey Voronin.
 * @since 22.04.2018.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/current")
    public User getCurrentUser(final Principal principal) {
        if (principal != null) {
            return this.userService.findUserByEmail(principal.getName());
        }
        return null;
    }
}
