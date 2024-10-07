package com.spark.movie.model;

public class Video {
    public Video(String source, String url, String localPath) {
        this.source = source;
        this.url = url;
        this.localPath = localPath;
    }
    String source;
    String url;

    String localPath;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
