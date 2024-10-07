package com.spark.movie.pics;

public class PicsRequest {
    String url;
    String mode;
    boolean ignoreInlineImages;
    public PicsRequest(){}
    public PicsRequest(String url){
        this.url = url;
        this.mode = "advanced";
        this.ignoreInlineImages = true;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean getIgnoreInlineImages() {
        return ignoreInlineImages;
    }

    public void setIgnoreInlineImages(boolean ignoreInlineImages) {
        this.ignoreInlineImages = ignoreInlineImages;
    }
}
