package com.mahmoudjoe333.miwok.logic;

import com.mahmoudjoe333.miwok.R;

public class word {
    private String eng,miwok;
    private Integer img;
    private Integer song;

    public word(String eng, String miwok, Integer img, Integer song) {
        this.eng = eng;
        this.miwok = miwok;
        this.img = img;
        this.song = song;

    }

    public word(String eng, String miwok) {
        this.eng = eng;
        this.miwok = miwok;
        this.img = null;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getMiwok() {
        return miwok;
    }

    public void setMiwok(String miwok) {
        this.miwok = miwok;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public Integer getSong() {
        return song;
    }

    public void setSong(Integer song) {
        this.song = song;
    }
}
