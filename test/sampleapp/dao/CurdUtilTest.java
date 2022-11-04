package sampleapp.dao;



import org.openqa.selenium.WebDriver;
import sampleapp.bo.BoFactory;
import sampleapp.bo.custom.Fb_urlBo;
import sampleapp.dto.PageDTO;
import sampleapp.service.operaterFb.SeleniumWebConfig;

import java.sql.SQLException;

class CurdUtilTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        WebDriver driver = SeleniumWebConfig.getInstance().driver();
        driver.get("https://www.facebook.com/Sampathbankplc");
    }
}
/*
"https://www.facebook.com/HNBPLC",
"https://www.facebook.com/Sampathbankplc",
"https://www.facebook.com/DolawaBoxDelivery",
"https://www.facebook.com/augustorycorp",
"https://www.facebook.com/IPSE.lk",
"https://www.facebook.com/hameedfoods",
"https://www.facebook.com/capitolavenueproperties",
"https://www.facebook.com/aswadduma"
};*/
