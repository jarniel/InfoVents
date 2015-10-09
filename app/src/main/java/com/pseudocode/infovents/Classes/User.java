package com.pseudocode.infovents.Classes;

/**
 * Created by Baymax on 08/10/2015.
 */

public class User {
    String id;
    String fname;
    String lname;
    String name;
    String gender;
    String link;
    String prov;
    String avatar;
    String email;


    public User(String id, String fname, String lname, String name, String gender, String link, String  prov, String avatar, String email){
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.name = name;
        this.gender = gender;
        this.link = link;
        this.prov = prov;
        this.avatar = avatar;
        this.email = email;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getFname() {
        return fname;
    }

    public String getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public String getLname() {
        return lname;
    }

    public String getName() {
        return name;
    }

    public String getProv() {
        return prov;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

}
