package ru.voronin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.voronin.domain.User;

import java.util.UUID;

/**
 * User repository.
 *
 * @author Alexey Voronin.
 * @since 20.04.2018.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByEmail(final String email);
}
