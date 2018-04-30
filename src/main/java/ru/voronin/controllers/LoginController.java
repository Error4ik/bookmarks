package ru.voronin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.voronin.domain.User;
import ru.voronin.services.UserService;

/**
 * Login controller.
 *
 * @author Alexey Voronin.
 * @since 19.04.2018.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView view = new ModelAndView();
        view.setViewName("login");
        return view;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView regUser(final User user) {
        ModelAndView view = new ModelAndView();
        User u = this.userService.findUserByEmail(user.getEmail());
        if (u != null) {
            view.setViewName("login");
            view.addObject("error", true);
            return view;
        }
        view.setViewName("redirect:/");
        this.userService.regUser(user);
        return view;
    }
}
