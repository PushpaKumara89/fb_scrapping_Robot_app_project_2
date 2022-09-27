package sampleapp;

import org.openqa.selenium.WebDriver;
import sampleapp.bo.BoFactory;
import sampleapp.bo.custom.Fb_urlBo;
import sampleapp.dto.PageDTO;
import sampleapp.service.operaterFb.Activate_urls;
import sampleapp.service.operaterFb.FaceBookPagePostAutomate;
import sampleapp.service.operaterFb.SeleniumWebConfig;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class AppInitializer {
    static Fb_urlBo bo = (Fb_urlBo) BoFactory.getInstance().getBo(BoFactory.BoType.FB_URLS);
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = SeleniumWebConfig.getInstance().driver();
        driver.navigate().to("https://www.google.com");
        SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd  hh:mm:ss a");
        Runnable runTimeCount = ()->{
            for (int i = 24; i > 0; i--) {
                tenMinutes();
                String timeString = format.format(Calendar.getInstance().getTime());
                System.out.println(timeString+ "  Remaining Times: "+i +" hrs");
                try {Thread.sleep(3600000);} catch (InterruptedException e) {}

            }
        };

        Runnable run = () -> {
            ArrayList<PageDTO> activated_urls = getActivatedUrls(true);
            if (activated_urls.isEmpty()){
                activate_url();
            }

            for (int i = 0; i < activated_urls.size(); i++) {
                try {
                    FaceBookPagePostAutomate.newPostSave(activated_urls.get(i));
                    System.gc();
                } catch (InterruptedException | IOException e) {}
            }
        };
        for (int i = 0;true ; i++) {
            new Thread(run).start();
            new Thread(runTimeCount).start();
            Thread.sleep(86400000);
        }
    }

    private static void tenMinutes() {
        Runnable ten = () -> {
            for (int i = 60; i > 0; i-=10) {
                if(i!=60){
                    activate_url();
                }
                System.out.println("minutes "+i);
                try {Thread.sleep(600000);} catch (InterruptedException e) {}
            }
        };
        new Thread(ten).start();
    }

    private static void activate_url() {
        Runnable activate_url = () -> {
            ArrayList<PageDTO> notActivate = getActivatedUrls(false);
            for (int i = 0; i < notActivate.size(); i++) {
                try {
                    PageDTO activate = Activate_urls.activate(notActivate.get(i));
                    System.out.println(activate);
                    bo.update_URL(activate);
                    System.out.println(true+" -------activated----->");
                    FaceBookPagePostAutomate.newPostSave(activate);

                } catch (IOException | InterruptedException | ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(activate_url).start();
    }

    private static ArrayList<PageDTO> getActivatedUrls(boolean b) {
        ArrayList<PageDTO> activated_urls = null;
        try {
            activated_urls = bo.getAll_URLS_activated(b);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return activated_urls;
    }
}
