package com.spark.movie.pics;

public class PicsImage {
    String id;
    String url;
    String name;
    Integer size;
    String type;
    Integer width;
    Integer height;
    String basename;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getBasename() {
        return basename;
    }

    public void setBasename(String basename) {
        this.basename = basename;
    }
    public String getbaseImegeCode(){
        return basename.split("@")[0];
    }
    //    {
//        "id": "f87058ee-d238-4380-8492-3f47ec2dab78",
//            "url": "https://m.media-amazon.com/images/G/01/imdb/images-ANDW73HA/favicon_desktop_32x32._CB1582158068_.png",
//            "name": "favicon_desktop_32x32._CB1582158068_",
//            "size": 497,
//            "type": "png",
//            "width": 32,
//            "height": 32,
//            "basename": "favicon_desktop_32x32._CB1582158068_.png"
//    }
}
