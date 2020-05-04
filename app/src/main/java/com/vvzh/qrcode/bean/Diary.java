package com.vvzh.qrcode.bean;

public class Diary {
    private int id;
    private String scan_result;

    public Diary() {
    }

    public Diary(int id, String scan_result) {
        this.id = id;
        this.scan_result = scan_result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScan_result() {
        return scan_result;
    }

    public void setScan_result(String scan_result) {
        this.scan_result = scan_result;
    }
}
