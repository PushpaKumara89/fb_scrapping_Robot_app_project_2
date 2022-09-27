package sampleapp.dao.custom;

import sampleapp.dao.CurdDao;
import sampleapp.entty.Page;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Fb_UrlDao extends CurdDao<Page,Integer> {
    public ArrayList<Page> getAll_URLS_activated(boolean b) throws ClassNotFoundException, SQLException;
    public Page get_URL(String url) throws ClassNotFoundException, SQLException;
}
