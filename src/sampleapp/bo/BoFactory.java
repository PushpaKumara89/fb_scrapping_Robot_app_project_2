package sampleapp.bo;

import sampleapp.bo.custom.impl.FbPostBoIMPL;
import sampleapp.bo.custom.impl.Fb_urlBo_IMPL;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){}

    public enum BoType{
        FB_POSTS,FB_URLS// implement here new......................
    }

    public static BoFactory getInstance(){
        return boFactory = (boFactory==null) ? new BoFactory() : boFactory;
    }
    public SuperBo getBo(BoType type){
        switch (type){
            case FB_POSTS: return new FbPostBoIMPL();
            case FB_URLS: return new Fb_urlBo_IMPL();
            default: return null;
        }
    }
}
