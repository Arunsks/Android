package com.example.programmer.ecommerce.model;

public class products {

    private String pname,pdescription,pimage,category,price,pid,time,date;

    public products(){


    }

    public products(String pname, String pdescription, String pimage, String category, String price, String pid, String time, String date) {
        this.pname = pname;
        this.pdescription = pdescription;
        this.pimage = pimage;
        this.category = category;
        this.price = price;
        this.pid = pid;
        this.time = time;
        this.date = date;
    }


    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPdescription() {
        return pdescription;
    }

    public void setPdescription(String pdescription) {
        this.pdescription = pdescription;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
