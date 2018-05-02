package ru.voronin.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Util class write file to disk.
 *
 * @author Alexey Voronin.
 * @since 26.04.2018.
 */
@Component
public class ActionsWithFile {

    private final String fileSeparator = System.getProperty("file.separator");

    @Value("${upload.folder}")
    private String saveToPath;

    @Value("${file.extension.to.save}")
    private String fileExtension;

    public File write(final BufferedImage image, final String userName) {
        File dir = new File(saveToPath + userName + fileSeparator);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir + fileSeparator + System.currentTimeMillis() + "." + fileExtension);
        try {
            ImageIO.write(image, fileExtension, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    public void deleteFileFromDisk(final String pathToFile) {
        new File(pathToFile).delete();
    }
}
