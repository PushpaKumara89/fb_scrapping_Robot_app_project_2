package sampleapp.dto;

public class ImageUrlDetails {
    private String url;

    public ImageUrlDetails() {
    }

    public ImageUrlDetails(String url) {
        this.setUrl(url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
