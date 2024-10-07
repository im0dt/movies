package com.spark.movie.pics;

public class PicsProcess {
    public PicsProcess(){

    }
    public PicsProcess(String status){
        data = new PicsProcessData();
        data.setStatus(status);
    }
    private PicsProcessData data;

    public PicsProcessData getData() {
        return data;
    }

    public void setData(PicsProcessData data) {
        this.data = data;
    }
}
