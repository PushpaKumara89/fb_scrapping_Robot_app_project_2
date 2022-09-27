package sampleapp.dao;



import sampleapp.bo.BoFactory;
import sampleapp.bo.custom.Fb_urlBo;
import sampleapp.dto.PageDTO;

import java.sql.SQLException;

class CurdUtilTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        Fb_urlBo impl = (Fb_urlBo) BoFactory.getInstance().getBo(BoFactory.BoType.FB_URLS);
        /*String[] ar ={
                "https://www.facebook.com/HNBPLC",
                "https://www.facebook.com/Sampathbankplc",
                "https://www.facebook.com/IPSE.lk",
                "https://www.facebook.com/KFCSriLanka",
                "https://www.facebook.com/aswadduma"};
        for (int i = 0; i < ar.length; i++) {
            System.out.println(impl.save_URL(new PageDTO(ar[i],"","jpg","Gamman",false,"12.451")));
        }*/
        System.out.println(impl.save_URL(new PageDTO("https://www.facebook.com/flysrilankan/","","jpg","Gamman",false,"12.451")));
        //System.out.println(impl.get_URL("https://www.facebook.com/HNBPLC"));
       /* System.out.println(impl.update_URL(new PageDTO(1,"https://www.facebook.com/HNBPLC","HNB11111111","jpg","Gamman",true,"12.451")));
        System.out.println(impl.get_URL("https://www.facebook.com/HNBPLC"));*/
        System.gc();
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
