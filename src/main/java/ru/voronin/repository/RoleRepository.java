package ru.voronin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.voronin.domain.Role;

import java.util.UUID;

/**
 * Role repository.
 *
 * @author Alexey Voronin.
 * @since 20.04.2018.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findByRole(final String name);
}
