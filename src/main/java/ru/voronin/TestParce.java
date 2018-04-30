package ru.voronin;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * TODO: comment.
 *
 * @author Alexey Voronin.
 * @since 16.04.2018.
 */
public class TestParce {

    private static final Logger LOG = Logger.getLogger(TestParce.class);

    public static void main(String[] args) {
        parseToSite();
    }

    public static void parseToSite() {
//        LOG.info("Search started.");
//        boolean stopSearch = false;
//        try {
//            Document doc = Jsoup.connect("https://fishki.net/2572877-25-nastojawih-vnedorozhnikov-kotorye-smogut-vyehaty-otkuda-ugodno.html")
//                    .userAgent("Chrome").get();
//            Elements elements = doc.getElementsByTag("img");
//
//            for (Element element : elements) {
//
//                String b = element.attr("data-src");
//
//                System.out.println(b);
//            }
//        } catch (IOException e) {
//            LOG.error(e.getMessage(), e);
//        }
//
//        LOG.info("Search Finished.");

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.setProperty("webdriver.chrome.driver", "C:/Users/Error4ik/Desktop/test/chromedriver/chromedriver_win32/chromedriver.exe");

                WebDriver driver = new ChromeDriver();

                driver.get("https://pasmi.ru/archive/207484/");

                File dir = new File("C:/upload/test/555/");
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File screenshot = ((TakesScreenshot) driver).
                        getScreenshotAs(OutputType.FILE);

                String path = dir + "/" + screenshot.getName();
                System.out.println(screenshot.getName());
                System.out.println(screenshot.length());
                System.out.println(screenshot.exists());
                try {
                    BufferedImage imageIO = ImageIO.read(screenshot);
                    System.out.println(imageIO.getWidth());;
                    System.out.println(imageIO.getHeight());;
                } catch (IOException e) {
                    e.printStackTrace();
                }



                try {
                    FileUtils.copyFile(screenshot, new File(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                driver.quit();
            }
        }).start();
    }
}
