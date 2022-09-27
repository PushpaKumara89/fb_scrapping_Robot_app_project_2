package sampleapp.bo.custom;

import sampleapp.bo.SuperBo;
import sampleapp.dto.PageDTO;
import sampleapp.dto.PostDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Fb_urlBo extends SuperBo {
    public boolean save_URL(PageDTO dto) throws ClassNotFoundException, SQLException, InterruptedException;
    public boolean update_URL(PageDTO dto) throws ClassNotFoundException, SQLException;
    public boolean delete_URL(Integer id) throws ClassNotFoundException, SQLException;
    public PageDTO get_URL(Integer id) throws ClassNotFoundException, SQLException;
    public ArrayList<PageDTO> getAll_URLS(String activate) throws ClassNotFoundException, SQLException;
    public ArrayList<PageDTO> getAll_URLS_activated(boolean b) throws ClassNotFoundException, SQLException;
    public PageDTO get_URL(String url) throws ClassNotFoundException, SQLException;
}
