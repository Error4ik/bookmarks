package ru.voronin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.voronin.domain.Image;

import java.util.UUID;

/**
 * Image repository.
 *
 * @author Alexey Voronin.
 * @since 24.04.2018.
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, UUID>{

    Image getImageById(final UUID id);
}
