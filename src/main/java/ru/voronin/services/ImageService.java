package ru.voronin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.voronin.domain.Image;
import ru.voronin.repository.ImageRepository;

import java.util.UUID;

/**
 * Image service.
 *
 * @author Alexey Voronin.
 * @since 24.04.2018.
 */
@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image save(final Image image) {
        return this.imageRepository.save(image);
    }

    public Image getImageById(final UUID id) {
        return this.imageRepository.getImageById(id);
    }

    public void deleteImage(final Image image) {
        this.imageRepository.delete(image);
    }

    public Image getById(final UUID id) {
        return this.imageRepository.getOne(id);
    }
}
