package com.project.onlineshopping;

/**
 * Created by Maria on 14-May-17.
 */

public class Product {
    private int pid;
    private String pname;
    private String pprice;
    private String discount;
    private byte[] pimage;

    public Product(int pid, String pname , String pprice , String discount , byte[] pimage) {
        this.pid = pid ;
        this.pname = pname ;
        this.pprice = pprice ;
        this.discount = discount ;
        this.pimage = pimage ;
    }


    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPprice() {
        return pprice;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice;
    }

    public byte[] getPimage() {
        return pimage;
    }

    public void setPimage(byte[] pimage) {
        this.pimage = pimage;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

}


