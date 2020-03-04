/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edjbc;

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

    
}
