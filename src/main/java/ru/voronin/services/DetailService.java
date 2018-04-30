package ru.voronin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voronin.domain.Role;
import ru.voronin.domain.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Detail service implement {@link org.springframework.security.core.userdetails.UserDetailsService}.
 *
 * @author Alexey Voronin.
 * @since 20.04.2018.
 */
@Service
public class DetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        User user = this.userService.findUserByEmail(email);
        if (user != null) {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for (Role role : user.getRoles()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
            }
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
