package sampleapp.service.operaterFb;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Scanner;

public class SeleniumWebConfig {
    private static SeleniumWebConfig seleniumWebConfig;
    private WebDriver driver;
    private Scanner key = new Scanner(System.in);


    private SeleniumWebConfig() {
        System.setProperty("webdriver.chrome.driver", "D:\\pushpakumara\\Wixis\\backup\\fb_scrapping_web\\fb_scrapping_screen_shot\\SeleniumPro\\lib\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();//Chrome browser hide ..................
        options.addArguments("--headless");
        options.addArguments("disable-notifications");
        driver = new ChromeDriver(options);
    }
    public static SeleniumWebConfig getInstance(){
        return seleniumWebConfig = seleniumWebConfig!=null ? seleniumWebConfig : new SeleniumWebConfig();
    }
    public WebDriver driver(){
        return driver;
    }
}
