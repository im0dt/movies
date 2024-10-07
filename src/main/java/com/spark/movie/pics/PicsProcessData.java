package com.spark.movie.pics;

import java.util.Date;

public class PicsProcessData {

    private String id;
    private String status;
    private String url;
    private Date created_at;
    private String project_id;
    private PicsImage[] images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public PicsImage[] getImages() {
        return images;
    }

    public void setImages(PicsImage[] images) {
        this.images = images;
    }
}
