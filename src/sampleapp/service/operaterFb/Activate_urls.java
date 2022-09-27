package sampleapp.service.operaterFb;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import sampleapp.dto.PageDTO;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Activate_urls {
    public static PageDTO activate(PageDTO pageDTO) throws IOException, InterruptedException {
        WebDriver driver = SeleniumWebConfig.getInstance().driver();
        driver.manage().window().setSize(new Dimension(750,550));
        driver.get(pageDTO.getUrl());

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 0);");
        pageDTO.setCover_image(screenShot(driver));
        pageDTO.setPage_name(driver.getTitle());
        System.out.println(driver.getTitle());
        pageDTO.setActivated_Time(new Date().toString());
        pageDTO.setActivate(true);

        Thread.sleep(1000);
        return pageDTO;
    }

    private static String screenShot(WebDriver driver) throws IOException, InterruptedException {
        Date currentDate = new Date();
        String randomCode = RandomStringUtils.randomAlphabetic(8);
        String screenShotName = currentDate.toString().replace(" ", "-").replace(":", "-")+"-"+randomCode;
        System.out.println(screenShotName);
        Thread.sleep(3000);

        File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShotFile, new File(".//screenshot/cover_img/screen"+screenShotName+".png"));
        return "screen"+screenShotName+".png";
    }
}
