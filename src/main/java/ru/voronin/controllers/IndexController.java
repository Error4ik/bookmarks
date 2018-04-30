package ru.voronin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.voronin.services.UserService;

import java.security.Principal;

/**
 * Index controller.
 *
 * @author Alexey Voronin.
 * @since 15.04.2018.
 */
@RestController
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView getIndex(final Principal principal) {
        final ModelAndView view = new ModelAndView("index");
        view.addObject("user", this.userService.findUserByEmail(principal.getName()));
        return view;
    }
}
