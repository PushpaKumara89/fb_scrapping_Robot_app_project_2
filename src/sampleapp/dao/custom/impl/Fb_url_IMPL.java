package sampleapp.dao.custom.impl;

import sampleapp.dao.CurdUtil;
import sampleapp.dao.Status;
import sampleapp.dao.custom.Fb_UrlDao;
import sampleapp.entty.Page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Fb_url_IMPL implements Fb_UrlDao {
    @Override
    public boolean save(Page f) throws ClassNotFoundException, SQLException, InterruptedException {
        System.out.println(f);
        Status status = CurdUtil.execute("INSERT INTO page values(?, ?, ?, ?, ?, ?, ?)", null, f.getUrl(), f.getPage_name(), f.getCover_image(), f.getUser_saved(), f.isActivate(), f.getActivated_Time());
        return status.isSave();
    }

    @Override
    public boolean delete(Integer id) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(Page f) throws ClassNotFoundException, SQLException {
        Status status = CurdUtil.execute("UPDATE page set page_name=?, cover_image=?, activate=?, activated_Time=? WHERE id=?", f.getPage_name(), f.getCover_image(), f.isActivate(), f.getActivated_Time(), f.getId());
        return status.isSave();
    }

    @Override
    public Page get(Integer id) throws ClassNotFoundException, SQLException {
        ResultSet rst = CurdUtil.execute("SELECT * FROM page WHERE id = ?", id);
        if (rst.next()){
            return new Page(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getBoolean(6),
                    rst.getString(7)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Page> getAll(String txt) throws ClassNotFoundException, SQLException {
        return null;
    }


    @Override
    public ArrayList<Page> getAll_URLS_activated(boolean b) throws ClassNotFoundException, SQLException {
        ResultSet rst = CurdUtil.execute("SELECT * FROM page WHERE activate = ?", b);
        ArrayList<Page> list = new ArrayList<>();
        while (rst.next()){
            list.add(new Page(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getBoolean(6),
                    rst.getString(7)
            ));
        }
        return list;
    }

    @Override
    public Page get_URL(String url) throws ClassNotFoundException, SQLException {
        ResultSet rst= CurdUtil.execute("SELECT * FROM page WHERE url = ?", url);
        if (rst.next()){
            return new Page(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getBoolean(6),
                    rst.getString(7)
            );
        }
        return null;
    }
}
