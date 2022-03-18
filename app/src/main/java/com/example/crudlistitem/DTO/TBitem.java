package com.example.crudlistitem.DTO;

public class TBitem {
    int id_item;
    String ten_item;
    String season;

    public static final String TB_NAME = "tb_item";
    public static final String COL_NAME_ID = "id_item";
    public static final String COL_NAME_TEN_ITEM = "ten_item";
    public static final String COL_NAME_SEASON = "season";

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public String getTen_item() {
        return ten_item;
    }

    public void setTen_item(String ten_item) {
        this.ten_item = ten_item;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }


}
