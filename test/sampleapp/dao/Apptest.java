package sampleapp.dao;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import sampleapp.bo.BoFactory;
import sampleapp.bo.SuperBo;
import sampleapp.bo.custom.FbPostBo;
import sampleapp.bo.custom.impl.FbPostBoIMPL;
import sampleapp.bo.custom.impl.Fb_urlBo_IMPL;
import sampleapp.dao.custom.impl.Fb_url_IMPL;
import sampleapp.dto.PageDTO;
import sampleapp.service.operaterFb.Activate_urls;
import sampleapp.service.operaterFb.FaceBookPagePostAutomate;
import sampleapp.service.operaterFb.SeleniumWebConfig;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Apptest {
    public static void main(String[] args) throws IOException, InterruptedException, SQLException, ClassNotFoundException {
        //FaceBookPagePostAutomate.newPostSave(new Fb_urlBo_IMPL().get_URL(5));
        /*WebDriver driver = SeleniumWebConfig.getInstance().driver();
        driver.get("https://www.facebook.com/Sampathbankplc");
        Thread.sleep(400);*/
        PageDTO url = new Fb_urlBo_IMPL().get_URL(1);
        url.setCover_image("screenWed-Aug-31-12-34-05-IST-2022-aQIUMVao.png");
        //PageDTO activate = Activate_urls.activate(url);
        new Fb_urlBo_IMPL().update_URL(url);

    }
}
