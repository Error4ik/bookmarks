package ru.voronin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.voronin.domain.Bookmark;

import java.util.UUID;

/**
 * Bookmark repository.
 *
 * @author Alexey Voronin.
 * @since 24.04.2018.
 */
@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, UUID> {
}
