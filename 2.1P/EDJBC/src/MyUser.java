/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tom
 */
public class MyUser {
    private String userid;
private String name;
private String password;
private String email;
private String phone;
private String address;
private String secQn;
private String secAns;

    public MyUser(String userid, String name, String password, String email, String phone, String address, String secQn, String secAns) {
        this.userid = userid;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.secQn = secQn;
        this.secAns = secAns;
    }

    public String getUserid() { 
       return userid;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getSecQn() {
        return secQn;
    }

    public String getSecAns() {
        return secAns;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSecQn(String secQn) {
        this.secQn = secQn;
    }

    public void setSecAns(String secAns) {
        this.secAns = secAns;
    }

    
}
