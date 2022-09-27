package sampleapp.entty;

import java.util.ArrayList;
import java.util.Objects;

public class PostFB {
    private long id;
    private long page_id;
    private String scraping_time;
    private String post_by;
    private ArrayList<String> imgURL;

    public PostFB() {
    }

    public PostFB(long page_id, String scraping_time, String post_by, ArrayList<String> imgURL) {
        this.page_id = page_id;
        this.scraping_time = scraping_time;
        this.post_by = post_by;
        this.imgURL = imgURL;
    }

    public PostFB(long id, long page_id, String scraping_time, String post_by, ArrayList<String> imgURL) {
        this.setId(id);
        this.setPage_id(page_id);
        this.setScraping_time(scraping_time);
        this.setPost_by(post_by);
        this.setImgURL(imgURL);
    }

    @Override
    public String toString() {
        return "PostFB{" +
                "id=" + id +
                ", page_id=" + page_id +
                ", scraping_time='" + scraping_time + '\'' +
                ", post_by='" + post_by + '\'' +
                ", imgURL=" + imgURL +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostFB)) return false;
        PostFB postFB = (PostFB) o;
        return getPage_id() == postFB.getPage_id() && getScraping_time().equals(postFB.getScraping_time()) && getPost_by().equals(postFB.getPost_by());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPage_id(), getScraping_time(), getPost_by());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPage_id() {
        return page_id;
    }

    public void setPage_id(long page_id) {
        this.page_id = page_id;
    }

    public String getScraping_time() {
        return scraping_time;
    }

    public void setScraping_time(String scraping_time) {
        this.scraping_time = scraping_time;
    }

    public String getPost_by() {
        return post_by;
    }

    public void setPost_by(String post_by) {
        this.post_by = post_by;
    }

    public ArrayList<String> getImgURL() {
        return imgURL;
    }

    public void setImgURL(ArrayList<String> imgURL) {
        this.imgURL = imgURL;
    }
}
