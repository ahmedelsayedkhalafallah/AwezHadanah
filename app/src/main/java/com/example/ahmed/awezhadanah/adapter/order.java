package com.example.ahmed.awezhadanah.adapter;

public class order {
    private String age,comment,date,ddate,dtime,id,name,phone,time;
    public order(String age,String comment,String date ,String ddate,String dtime ,String id,String name,String phone ,String time)
    {
        this.age = age;
        this.comment = comment;
        this.date = date;
        this.ddate = ddate;
        this.dtime = dtime;
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.time = time;
    }

    order(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDdate() {
        return ddate;
    }

    public void setDdate(String ddate) {
        this.ddate = ddate;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }
}
