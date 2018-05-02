package ru.voronin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.voronin.domain.Bookmark;
import ru.voronin.domain.Image;
import ru.voronin.repository.BookmarkRepository;
import ru.voronin.util.ActionsWithFile;
import ru.voronin.util.CropTheFile;
import ru.voronin.util.GettingSnapshotOfTheSite;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Bookmark service.
 *
 * @author Alexey Voronin.
 * @since 24.04.2018.
 */
@Service
@PropertySource("classpath:application.properties")
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private GettingSnapshotOfTheSite gettingSnapshotOfTheSite;

    @Autowired
    private CropTheFile cropTheFile;

    @Autowired
    private ActionsWithFile actionsWithFile;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Value("${default.image}")
    private String pathToDefaultImage;

    public Bookmark save(final Bookmark bookmark) {
        return this.bookmarkRepository.save(bookmark);
    }

    public Bookmark prepareToSave(final Bookmark bookmark) {
        if (!bookmark.getUrl().startsWith("http")) {
            bookmark.setUrl("http://" + bookmark.getUrl());
        }

        ApplicationContext appContext = new ClassPathXmlApplicationContext();
        Resource resource = appContext.getResource(pathToDefaultImage);

        File file = null;
        try {
            file = resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String userName = this.securityService.findLoggedUser();
        bookmark.setUserId(this.userService.findUserByEmail(userName).getId());
        Image image = new Image();
        image.setUrl(file == null ? "error" : file.getAbsolutePath());
        image.setName(userName + System.currentTimeMillis());
        this.imageService.save(image);
        bookmark.setImage(image);

        new Thread(new Runnable() {
            @Override
            public void run() {
                getImage(bookmark, userName);
            }
        }).start();

        return bookmark;
    }

    public Bookmark getBookmarkById(final UUID id) {
        return this.bookmarkRepository.getBookmarkById(id);
    }

    public void deleteBookmarkById(final UUID id) {
        String pathToFile = this.getBookmarkById(id).getImage().getUrl();
        this.bookmarkRepository.deleteById(id);
        this.actionsWithFile.deleteFileFromDisk(pathToFile);
    }

    private void getImage(final Bookmark bookmark, final String userName) {
        File file = this.actionsWithFile.write(
                this.cropTheFile.crop(
                        this.gettingSnapshotOfTheSite.getSnapshot(
                                bookmark.getUrl()))
                , userName);
        bookmark.getImage().setUrl(file.getAbsolutePath());
        bookmark.getImage().setName(file.getName());
        this.imageService.save(bookmark.getImage());
    }
}
