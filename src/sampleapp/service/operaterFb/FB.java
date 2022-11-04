package sampleapp.service.operaterFb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sampleapp.db.SqliteDBConnection;
import sampleapp.dto.PostDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FB {

    //-------------------------------execute selected page type---------------------------------------------------
    private static final By x_login_popup = By.xpath("//div[@class='oajrlxb2 qu0x051f esr5mh6w e9989ue4 r7d6kgcz nhd2j8a9 p7hjln8o kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x i1ao9s8h esuyzwwr f1sip0of abiwlrkh p8dawk7l lzcic4wl bp9cbjyn s45kfl79 emlxlaya bkmhp75w spb7xbtv rt8b4zig n8ej3o3l agehan2d sk4xxmp2 rq0escxv j83agx80 taijpn5t jb3vyjys rz4wbd8a qt6c0cv9 a8nywdso l9j0dhe7 tv7at329 thwo4zme tdjehn4e']");


    public static ArrayList<PostDTO> getWebElements(WebDriver driver) {

        WebElementsFB elementsT1 = new WebElementsFB(
               /* driver.getTitle(),
                driver.findElements(x_post_type1),
                driver.findElements(x_massages_type1),
                driver.findElements(x_imgUrl_type1),
                driver.findElements(x_timeDate_type1)*/
                );
        WebElementsFB elementsT2 = new WebElementsFB(
               /* driver.getTitle(),
                driver.findElements(x_post_type2),
                driver.findElements(x_massages_type2),
                driver.findElements(x_imgUrl_type2),
                driver.findElements(x_timeDate_type2)*/
        );
        ArrayList<PostDTO> postDTOS =null;
        if (elementsT1.is_verified()){
            postDTOS = getList(elementsT1,driver.getTitle());
            System.out.println("type1");

        }if(elementsT2.is_verified()){
            postDTOS = getList(elementsT2,driver.getTitle());
            System.out.println("type2");
        }
        return postDTOS;
    }

    public static List<WebElement> getPostWebElements(WebDriver driver) {
        List<WebElement> ele =null;
        try {
            Connection connection = SqliteDBConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM xpath");
            ResultSet rst = pst.executeQuery();
            while (rst.next()){

                ele = driver.findElements(By.xpath(rst.getString(2)));
                if (!ele.isEmpty()){
                    System.out.println("xpath Id: "+rst.getInt(1));
                    return ele;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ele;


        /*List<WebElement> elements1 = driver.findElements(x_post_type1);
        List<WebElement> elements2 = driver.findElements(x_post_type2);
        List<WebElement> posts=null;
        if (!elements1.isEmpty()){
            posts=elements1;
            System.out.println("type1");
        }if(!elements2.isEmpty()){
            posts=elements2;
            System.out.println("type2");
        }
        return posts;*/
    }

    //---------------------------------get Cra
    private static ArrayList<PostDTO> getList(WebElementsFB element, String title) {
        int elementsSize = 0;
        elementsSize = Math.min(element.getText().size(), 5);
        /*
        elementsSize =element.getText().size()>5 ? 5:element.getText().size();

        if (element.getText().size()>5){
            elementsSize=5;
        }else {
            elementsSize=element.getText().size();
        }*/
        ArrayList<PostDTO> postDTOS = new ArrayList<>();
        for (int i = 0; i < elementsSize; i++) {
            List<WebElement> img = element.getImgUrl().get(i).findElements(By.tagName("img"));
            ArrayList<String> imgs = new ArrayList<>();
            for (int j = 0; j < img.size(); j++) {
                imgs.add(img.get(j).getAttribute("src"));
            }
            String postTime = DataAndTimeFB.getDateFormat(element.getDate().get(i).getText());
            postDTOS.add(new PostDTO(postTime,title,imgs));
        }
        return postDTOS;
    }

    public static void close_Login_popup(WebDriver driver, WebDriverWait driverWait) {
        //implement if ont exits popup..............can be occur error....
        WebElement login_popup = driver.findElement(x_login_popup);

        WebElement until = driverWait.until(ExpectedConditions.visibilityOf(login_popup));
        if (login_popup.isDisplayed()){
            until.click();
        }else {
            System.out.println("not Displayed");
        }

    }
}
