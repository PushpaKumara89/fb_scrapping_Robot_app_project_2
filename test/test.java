
import sampleapp.dto.PageDTO;
import sampleapp.service.operaterFb.FaceBookPagePostAutomate;

import java.io.IOException;
import java.sql.*;

public class test {
    public static void main(String[] args) throws InterruptedException, SQLException, ClassNotFoundException {
        try {
            FaceBookPagePostAutomate.newPostSave(new PageDTO("https://www.facebook.com/aswadduma","xx","gf", "rr", true,"5757"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*
86400000
33 mins
  ·
//div[@class='i09qtzwb n7fi1qx3 datstx6m pmk7jnqg j9ispegn kr520xx4 k4urcfbm']/div
  https://www.facebook.com/aswadduma
success
23
true
25 May at 17:18
  ·
success
24
true
24 May at 12:13
  ·
success
25
true

"25 December 2021\n" +
                "  ·";

10 February
  ·

*/
