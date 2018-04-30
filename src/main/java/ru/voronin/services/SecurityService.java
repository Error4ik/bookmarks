package ru.voronin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Security service.
 *
 * @author Alexey Voronin.
 * @since 20.04.2018.
 */
@Service
public class SecurityService {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private DetailService detailService;

    public String findLoggedUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }

    public void autoLogin(final String userName, final String password) {
        UserDetails details = this.detailService.loadUserByUsername(userName);
        if (details != null) {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(details, password, details.getAuthorities());
            this.manager.authenticate(token);
            if (token.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
    }
}
