package ru.voronin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.voronin.domain.Role;
import ru.voronin.domain.User;
import ru.voronin.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * User service.
 *
 * @author Alexey Voronin.
 * @since 19.04.2018.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SecurityService securityService;

    public User save(final User user) {
        return this.userRepository.save(user);
    }

    public User getUserById(final UUID id) {
        return this.userRepository.findById(id).get();
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User findUserByEmail(final String email) {
        return this.userRepository.findByEmail(email);
    }

    public void regUser(final User user) {
        final String pass = user.getPassword();
        user.setPassword(encoder.encode(pass));
        Set<Role> roles = new HashSet<>();
        roles.add(this.roleService.findRoleByName("user"));
        user.setRoles(roles);
        this.save(user);
        securityService.autoLogin(user.getEmail(), pass);
    }
}
