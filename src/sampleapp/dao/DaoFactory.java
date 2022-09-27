package sampleapp.dao;

import sampleapp.dao.custom.impl.FbPostDaoIMPL;
import sampleapp.dao.custom.impl.Fb_url_IMPL;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){}

    public enum DaoType{
        FB_POSTS,FB_URLS// implement here new......................
    }

    public static DaoFactory getInstance(){
        return daoFactory = (daoFactory==null) ? new DaoFactory() : daoFactory;
    }
    public SuperDao getDao(DaoType type){
        switch (type){
            case FB_POSTS: return new FbPostDaoIMPL();
            case FB_URLS: return new Fb_url_IMPL();
            default: return null;
        }
    }
}
