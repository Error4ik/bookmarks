package ru.voronin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.voronin.domain.Bookmark;
import ru.voronin.services.BookmarkService;

/**
 * Bookmark controller.
 *
 * @author Alexey Voronin.
 * @since 24.04.2018.
 */
@RestController
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @RequestMapping("/add-bookmark")
    public ModelAndView addNewBookmark(final Bookmark bookmark) {
        final ModelAndView view = new ModelAndView("redirect:/");
        this.bookmarkService.save(this.bookmarkService.prepareToSave(bookmark));
        return view;
    }
}
