package ru.voronin.util;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Util class for get snapshot to site.
 *
 * @author Alexey Voronin.
 * @since 26.04.2018.
 */
@Component
@PropertySource("classpath:application.properties")
public class GettingSnapshotOfTheSite {

    @Value("${web.driver.name}")
    private String webDriver;

    @Value("${path.to.chrome.driver}")
    private String pathToChromeDriver;

    @Value("${driver.windows.dimension.width}")
    private int width;

    public File getSnapshot(final String url) {
        String urlToSite = url;
        if (!urlToSite.startsWith("http")) {
            urlToSite = "http://" + urlToSite;
        }
        System.setProperty(webDriver, pathToChromeDriver);
        WebDriver driver = new ChromeDriver();

        Dimension dimension = driver.manage().window().getSize();
        Dimension dim = new Dimension(width, dimension.getHeight());
        driver.manage().window().setSize(dim);

        try {
            driver.get(urlToSite);
        } catch (org.openqa.selenium.TimeoutException te) {
            ((JavascriptExecutor) driver).executeScript("window.stop();");
        } catch (UnhandledAlertException uae) {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }

        File snapshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        driver.quit();

        return snapshot;
    }
}
