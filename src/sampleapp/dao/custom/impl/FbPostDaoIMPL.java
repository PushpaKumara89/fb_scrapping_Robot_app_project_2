package sampleapp.dao.custom.impl;

import sampleapp.dao.CurdUtil;
import sampleapp.dao.Status;
import sampleapp.dao.custom.FBPostDao;
import sampleapp.db.DBConnection;
import sampleapp.entty.PostFB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FbPostDaoIMPL implements FBPostDao {

    @Override
    public boolean save(PostFB p) throws ClassNotFoundException, SQLException, InterruptedException {
        System.out.println(p);
        Connection connection = DBConnection.getInstance().getConnection();
        Status status;
            status = CurdUtil.execute("INSERT INTO post values(?,?,?,?)", null, p.getPage_id(), p.getScraping_time(), p.getPost_by());
            Thread.sleep(100);

            if (status.isSave()){
                    int post_id = status.getGeneratedKe();
                    boolean isAdd = addPost_url(post_id, p.getImgURL());
                    return isAdd;
            }
            return false;
    }

    @Override
    public boolean delete(Integer integer) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(PostFB p) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public PostFB get(Integer integer) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<PostFB> getAll(String txt) throws ClassNotFoundException, SQLException {
        ResultSet rst = CurdUtil.execute("SELECT * FROM post WHERE scraping_time LIKE '%"+txt+"%' OR post_by LIKE '%"+txt+"%'");
        return getArrayList(rst);
    }

    @Override
    public ArrayList<PostFB> getAllForPaginate(String txt, int from, int to) throws ClassNotFoundException, SQLException {
        ResultSet rst = CurdUtil.execute("SELECT * FROM post WHERE scraping_time LIKE '%"+txt+"%' OR post_by LIKE '%"+txt+"%' ORDER BY id DESC LIMIT "+from+","+to);
        return getArrayList(rst);
    }

    @Override
    public int getPostCount(String txt) throws ClassNotFoundException, SQLException {
        ResultSet rst=CurdUtil.execute("SELECT COUNT(*) from Post WHERE scraping_time LIKE '%"+txt+"%' OR post_by LIKE '%"+txt+"%'");
        if (rst.next()){
            return rst.getInt(1);
        }
        return 0;
    }

    //------------------------------------------------------------------------------------------------------------------

    private static ArrayList<PostFB> getArrayList(ResultSet rst) throws SQLException, ClassNotFoundException {
        ArrayList<PostFB> list = new ArrayList<>();
        for (int i = 0; rst.next(); i++) {
            ResultSet rst1 = CurdUtil.execute("SELECT img_url FROM post_imgs WHERE post_id=?",rst.getInt(1));
            ArrayList<String> url = new ArrayList<>();
            while (rst1.next()){
                url.add(rst1.getString(1));
            }
            list.add(new PostFB(rst.getInt(1),
                    rst.getInt(2),
                    rst.getString(3),
                    rst.getString(4),
                    url));
        }
        return list;
    }

    private static boolean addPost_url(int post_id, ArrayList<String> imgURL) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < imgURL.size(); i++) {
            Status status = CurdUtil.execute("INSERT INTO post_imgs VALUES(?,?,?)",null,post_id,imgURL.get(i));
        }
        return true;
    }
}
