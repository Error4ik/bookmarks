package ru.voronin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.voronin.domain.Bookmark;
import ru.voronin.domain.Image;
import ru.voronin.repository.BookmarkRepository;
import ru.voronin.util.CropTheFile;
import ru.voronin.util.GettingSnapshotOfTheSite;
import ru.voronin.util.WriteFileToDisc;

import java.io.File;

/**
 * Bookmark service.
 *
 * @author Alexey Voronin.
 * @since 24.04.2018.
 */
@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private GettingSnapshotOfTheSite gettingSnapshotOfTheSite;

    @Autowired
    private CropTheFile cropTheFile;

    @Autowired
    private WriteFileToDisc writeFileToDisc;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    public Bookmark save(final Bookmark bookmark) {
        return this.bookmarkRepository.save(bookmark);
    }

    public Bookmark prepareToSave(final Bookmark bookmark) {
        final String userName = this.securityService.findLoggedUser();
        bookmark.setUserId(this.userService.findUserByEmail(userName).getId());
        File file = this.writeFileToDisc.write(
                this.cropTheFile.crop(
                        this.gettingSnapshotOfTheSite.getSnapshot(
                                bookmark.getUrl()))
                , userName);
        Image image = new Image();
        image.setUrl(file.getAbsolutePath());
        image.setName(file.getName());
        this.imageService.save(image);
        bookmark.setImage(image);
        return bookmark;
    }
}
