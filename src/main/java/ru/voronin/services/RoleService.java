package ru.voronin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.voronin.domain.Role;
import ru.voronin.repository.RoleRepository;

/**
 * Role service.
 *
 * @author Alexey Voronin.
 * @since 20.04.2018.
 */
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findRoleByName(final String name) {
        return this.roleRepository.findByRole(name);
    }
}
