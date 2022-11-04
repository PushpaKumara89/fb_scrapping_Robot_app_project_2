package sampleapp.service.operaterFb;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import sampleapp.bo.BoFactory;
import sampleapp.bo.custom.FbPostBo;
import sampleapp.dto.PageDTO;
import sampleapp.dto.PostDTO;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.*;
import java.time.Duration;
import java.util.*;
import java.util.Date;

public class FaceBookPagePostAutomate {
    private static Set<PostDTO> previousPostDTO =null;
    private static FbPostBo bo = (FbPostBo) BoFactory.getInstance().getBo(BoFactory.BoType.FB_POSTS);

    public static Set<PostDTO> getLoadPost(String txt, int from, int to) throws SQLException, ClassNotFoundException {
        previousPostDTO=new LinkedHashSet<>();
        ArrayList<PostDTO> allPost = bo.getAllForPaginate(txt,from,to);
        for (int i = 0; i < allPost.size(); i++) {
            PostDTO dto=allPost.get(i);
            previousPostDTO.add(dto);
        }
        return previousPostDTO;
    }
    public static int getPostCount(String txt) throws SQLException, ClassNotFoundException {
        return bo.getPostCount(txt);
    }
    //------------------------------------------------------------------------------------------------------------------

    public static boolean newPostSave(PageDTO page) throws InterruptedException, IOException {
        //===============================================================================================================
        final WebDriver driver = SeleniumWebConfig.getInstance().driver();
        driver.manage().window().setSize(new Dimension(750,3000));//screen preparing
        driver.get(page.getUrl());//.......https://www.facebook.com/aswadduma.............https://www.facebook.com/Sampathbankplc.................https://www.facebook.com/augustorycorp
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < 8; i++) {
            Thread.sleep(300);
            js.executeScript("window.scrollBy(0, 900);");
        }
        System.out.println(driver.getCurrentUrl());
        By see_more = By.xpath("//*[text()='See more']");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)).getScriptTimeout();

        List<WebElement> see_more_elements = driver.findElements(see_more);

        if (!see_more_elements.isEmpty()){
            for (int i = 0; i < see_more_elements.size(); i++) {
                js.executeScript("arguments[0].click();", see_more_elements.get(i));
            }
        }

        /*FB.close_Login_popup(driver, driverWait);*/
        Runnable run = ()->{
            for (int i = 0; i <=100 ; i++) {
                System.out.print("=");
                try {Thread.sleep(65);} catch (InterruptedException ignored) {}
            }
            System.out.println("Completed");
        };

        driverWait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
                .executeScript("return document.readyState")
                .equals("complete"));//waiting 30s for page rendering...................................................

        Thread t = new Thread(run);t.start();

        Thread.sleep(1000);
        List<WebElement> postWebElements = FB.getPostWebElements(driver);

        try{
            System.out.println("count of post : "+postWebElements.size());
        }catch (NullPointerException n){
            System.out.println("elements Not Found");
            return false;
        }
        int elementsSize = 0;
        elementsSize = Math.min(postWebElements.size(), 5);
        ArrayList<String> img_url = new ArrayList<>();
        for (int i = 0; i < elementsSize; i++) {
            Thread.sleep(1500);
            js.executeScript("arguments[0].scrollIntoView(true);",postWebElements.get(i));
            js.executeScript("window.scrollBy(0, -150);");
            img_url.add(getPostScreen_shot(postWebElements.get(i)));
        }
        PostDTO dto = new PostDTO(page.getId(),new Date().toString(), driver.getTitle(), img_url);
        try {
            bo.savePost(dto);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        Set<String> win = driver.getWindowHandles();
        ArrayList<String> list = new ArrayList<>(win);
        Thread.sleep(3000);
        return false;
    }

    private static String getPostScreen_shot(WebElement webElement) throws IOException {
        Date currentDate = new Date();
        String randomCode = RandomStringUtils.randomAlphabetic(8);
        String screenShotName = currentDate.toString().replace(" ", "-").replace(":", "-")+"-"+randomCode;
        System.out.println(screenShotName);
        File screenshotAs = webElement.getScreenshotAs(OutputType.FILE);
        File file = new File(".//screenshot/post_img/screen"+screenShotName+".png");
        FileUtils.copyFile(screenshotAs,file);
        return "screen"+screenShotName+".png";
    }
}
