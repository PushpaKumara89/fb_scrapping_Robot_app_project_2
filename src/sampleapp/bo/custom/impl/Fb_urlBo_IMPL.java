package sampleapp.bo.custom.impl;

import sampleapp.bo.custom.Fb_urlBo;
import sampleapp.dao.DaoFactory;
import sampleapp.dao.custom.Fb_UrlDao;
import sampleapp.dto.PageDTO;
import sampleapp.dto.PostDTO;
import sampleapp.entty.Page;

import java.sql.SQLException;
import java.util.ArrayList;

public class Fb_urlBo_IMPL implements Fb_urlBo {
    Fb_UrlDao dao = (Fb_UrlDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.FB_URLS);

    @Override
    public boolean save_URL(PageDTO dto) throws ClassNotFoundException, SQLException, InterruptedException {
        return dao.save(new Page(dto.getUrl(),dto.getPage_name(),dto.getCover_image(),dto.getUser_saved(),dto.isActivate(), dto.getActivated_Time()));
    }

    @Override
    public boolean update_URL(PageDTO dto) throws ClassNotFoundException, SQLException {
        return dao.update(new Page(dto.getId(),dto.getUrl(),dto.getPage_name(),dto.getCover_image(), dto.getUser_saved(),dto.isActivate(), dto.getActivated_Time()));
    }

    @Override
    public boolean delete_URL(Integer id) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public PageDTO get_URL(Integer id) throws ClassNotFoundException, SQLException {
        Page page = dao.get(id);
        if (page!=null){
            return new PageDTO(
                    page.getId(),
                    page.getUrl(),
                    page.getPage_name(),
                    page.getCover_image(),
                    page.getUser_saved(),
                    page.isActivate(),
                    page.getActivated_Time()
            );
        }
        return null;
    }

    @Override
    public ArrayList<PageDTO> getAll_URLS(String activate) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<PageDTO> getAll_URLS_activated(boolean b) throws ClassNotFoundException, SQLException {
        ArrayList<PageDTO> list = new ArrayList<>();
        ArrayList<Page> all_urls = dao.getAll_URLS_activated(b);
        for (Page u: all_urls) {
            list.add(new PageDTO(u.getId(),
                    u.getUrl(),
                    u.getPage_name(),
                    u.getCover_image(),
                    u.getUser_saved(),
                    u.isActivate(),
                    u.getActivated_Time()
            ));
        }
        return list;
    }

    @Override
    public PageDTO get_URL(String url) throws ClassNotFoundException, SQLException {
        Page u = dao.get_URL(url);
        if (!(u==null)){
            return new PageDTO(u.getId(),
                    u.getUrl(),
                    u.getPage_name(),
                    u.getCover_image(),
                    u.getUser_saved(),
                    u.isActivate(),
                    u.getActivated_Time()
            );
        }
        return null;
    }
}
