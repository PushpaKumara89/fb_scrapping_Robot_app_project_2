package sampleapp.service.operaterFb;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sampleapp.dto.PostDTO;

import java.util.ArrayList;
import java.util.List;

public class FB {
    //-------------------------------declared type1 elements------------------------------------------------------
    //private static final By x_post_type1 = By.xpath("//div[@class='j83agx80 cbu4d94t']");
    private static final By x_post_type1 = By.xpath("//div[@class='g4tp4svg om3e55n1']");
    private static final By x_massages_type1 = By.xpath("//div[@data-ad-preview='message']");
    private static final By x_imgUrl_type1 = By.xpath("//div[@class='rq0escxv l9j0dhe7 du4w35lb hybvsw6c io0zqebd m5lcvass fbipl8qg nwvqtn77 k4urcfbm ni8dbmo4 stjgntxs sbcfpzgs']/div/div/div/div/div[@class='l9j0dhe7']");
    private static final By x_timeDate_type1 = By.xpath("//span[@class='d2edcug0 hpfvmrgz qv66sw1b c1et5uql lr9zc1uh a8c37x1j fe6kdd0r mau55g9w c8b282yb keod5gw0 nxhoafnm aigsh9s9 d9wwppkn mdeji52x e9vueds3 j5wam9gi b1v8xokw m9osqain hzawbc8m']/span/span/span/a/span");
    private static final By x_videoUrl1 = By.xpath("//div[@class='k4urcfbm hwddc3l5 datstx6m']");
    private static final By x_see_more_type1 = By.xpath("//div[@class='m8h3af8h l7ghb35v kjdc1dyq kmwttqpk gh25dzvf n3t5jt4f']/div/div[@class='qi72231t nu7423ey n3hqoq4p r86q59rh b3qcqh3k fq87ekyn bdao358l fsf7x5fv rse6dlih s5oniofx m8h3af8h l7ghb35v kjdc1dyq kmwttqpk srn514ro oxkhqvkx rl78xhln nch0832m cr00lzj9 rn8ck1ys s3jn8y49 icdlwmnq cxfqmxzd pbevjfx6 innypi6y']");


    //-------------------------------declared type2 elements------------------------------------------------------
    private static final By x_post_type2 = By.xpath("//div[@class='_5pcr userContentWrapper']");
    private static final By x_massages_type2 = By.xpath("//div[@class='_5pbx userContent _3576']");
    private static final By x_imgUrl_type2 = By.xpath("//div[@class='mtm']/div");
    private static final By x_timeDate_type2 = By.xpath("//div[@class='_5pcp _5lel _2jyu _232_']/span[@class='z_c3pyo1brp']");
    private static final By x_videoUrl2 = By.xpath("//div[@class='mtm']/div");
    private static final By x_see_more_type2 = By.xpath("//a[@class='see_more_link']");

    //-------------------------------execute selected page type---------------------------------------------------
    private static final By x_login_popup = By.xpath("//div[@class='oajrlxb2 qu0x051f esr5mh6w e9989ue4 r7d6kgcz nhd2j8a9 p7hjln8o kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x i1ao9s8h esuyzwwr f1sip0of abiwlrkh p8dawk7l lzcic4wl bp9cbjyn s45kfl79 emlxlaya bkmhp75w spb7xbtv rt8b4zig n8ej3o3l agehan2d sk4xxmp2 rq0escxv j83agx80 taijpn5t jb3vyjys rz4wbd8a qt6c0cv9 a8nywdso l9j0dhe7 tv7at329 thwo4zme tdjehn4e']");


    public static ArrayList<PostDTO> getWebElements(WebDriver driver) {

        WebElementsFB elementsT1 = new WebElementsFB(
                driver.getTitle(),
                driver.findElements(x_post_type1),
                driver.findElements(x_massages_type1),
                driver.findElements(x_imgUrl_type1),
                driver.findElements(x_timeDate_type1)
                );
        WebElementsFB elementsT2 = new WebElementsFB(
                driver.getTitle(),
                driver.findElements(x_post_type2),
                driver.findElements(x_massages_type2),
                driver.findElements(x_imgUrl_type2),
                driver.findElements(x_timeDate_type2)
        );
        ArrayList<PostDTO> postDTOS =null;
        if (elementsT1.is_verified()){
            List<WebElement> see_more_type1 = driver.findElements(x_see_more_type1);
            clickSee_More(see_more_type1,driver);
            postDTOS = getList(elementsT1,driver.getTitle());
            System.out.println("type1");

        }if(elementsT2.is_verified()){
            List<WebElement> see_more_type2 = driver.findElements(x_see_more_type2);
            clickSee_More(see_more_type2,driver);
            postDTOS = getList(elementsT2,driver.getTitle());
            System.out.println("type2");
        }
        return postDTOS;
    }

    public static List<WebElement> getPostWebElements(WebDriver driver) {
        List<WebElement> elements1 = driver.findElements(x_post_type1);
        List<WebElement> elements2 = driver.findElements(x_post_type2);
        List<WebElement> posts=null;
        if (!elements1.isEmpty()){
            List<WebElement> see_more_type2 = driver.findElements(x_see_more_type1);
            clickSee_More(see_more_type2,driver);
            posts=elements1;
            System.out.println("type1");
        }if(!elements2.isEmpty()){
            List<WebElement> see_more_type2 = driver.findElements(x_see_more_type2);
            clickSee_More(see_more_type2,driver);
            posts=elements2;
            System.out.println("type2");
        }
        return posts;
    }

    //----------------------------------------see more button click by javascript---------------------------------------
    private static void clickSee_More(List<WebElement> elements, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < elements.size(); i++) {
            js.executeScript("arguments[0].click();",elements.get(i));
        }
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
