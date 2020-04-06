/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.MyuserDTO;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import session.MyuserFacadeRemote;

/**
 *
 * @author Tom
 */
@Named(value = "myuserManagedBean")
@RequestScoped
public class MyuserManagedBean {

    @EJB
    private MyuserFacadeRemote myuserFacade;

    public MyuserManagedBean() {
    }

    private String userid;
    private String name;
    private String password;
    private String cPassword; // for confirmed password field
    private String email;
    private String phone;
    private String address;
    private String secQn;
    private String secAns;

    public MyuserFacadeRemote getMyuserFacade() {
        return myuserFacade;
    }

    public void setMyuserFacade(MyuserFacadeRemote myuserFacade) {
        this.myuserFacade = myuserFacade;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSecQn() {
        return secQn;
    }

    public void setSecQn(String secQn) {
        this.secQn = secQn;
    }

    public String getSecAns() {
        return secAns;
    }

    public void setSecAns(String secAns) {
        this.secAns = secAns;
    }

    private boolean checkForm() {
        return isValidUserid(userid) && isValidName(name)
                && isValidPassword(password) && isValidPassword(cPassword)
                && isValidEmail(email) && isValidPhone(phone)
                && isValidAddress(address) && isValidSecQn(secQn)
                && isValidSecAns(secAns) && password.equals(cPassword);
    }

    public void saveToFlash() {
        try {
            Flash fStore = FacesContext.getCurrentInstance().getExternalContext().getFlash();
            fStore.setKeepMessages(true);
            fStore.put("myUserDTO", new MyuserDTO(userid, name, password, email, phone, address, secQn, secAns));
        } catch (Exception ex) {
            System.err.println("Unable to save to flash storage.");
            ex.printStackTrace();
        }
    }

    public void loadFromFlash() {
        try {
            Flash fStore = FacesContext.getCurrentInstance().getExternalContext().getFlash();
            fStore.setKeepMessages(true);
            MyuserDTO storedUser = (MyuserDTO) fStore.get("myUserDTO");
            userid = storedUser.getUserid();
            name = storedUser.getName();
            password = storedUser.getPassword();
            cPassword = "";
            phone = storedUser.getPhone();
            address = storedUser.getAddress();
            secQn = storedUser.getSecQn();
            secAns = storedUser.getSecAns();
        } catch (Exception ex) {
            System.err.println("Unable to load from flash storage.");
            ex.printStackTrace();
        }
    }

    /*
* add a user to the database
* @return "success" if the add operation is successful
* "failure" otherwise
     */
    public String addUser() {
        String result = "failure";
        /* are all data entered valid?
        * and password the same as cPassword (case sensitive)
        * before calling the façade’s createRecord() method */
        if (!checkForm()) {
            System.err.println("Invalid Form");
        } else {
            MyuserDTO myuserDTO = new MyuserDTO(userid, name, password, email, phone, address, secQn, secAns);
            if (myuserFacade.getRecord(userid) != null) {
                if (!myuserFacade.updateRecord(myuserDTO)) {
                    System.err.println("addUser: Error updating record.");
                } else {
                    result = "success";
                }
            } else {
                if (!myuserFacade.createRecord(myuserDTO)) {
                    System.err.println("addUser: Error Creating record.");
                } else {
                    result = "success";
                }
            }
        }
        return result;
    }

    public String findUser() {
        myuserFacade.createRecord(new MyuserDTO("1234", "1234", "1234", "1234", "1234", "1234", "1234", "1234"));
        if (!isValidUserid(userid)) {
            System.out.println("Invalid id");
        } else {
            System.out.println("Valid id");
            MyuserDTO result = myuserFacade.getRecord(userid);
            if (result != null) {
                name = result.getName();
                password = result.getPassword();
                email = result.getEmail();
                phone = result.getPhone();
                address = result.getAddress();
                secQn = result.getSecQn();
                secAns = result.getSecAns();
            }
            saveToFlash();
            return "success";
        }
        return "failure";
    }

    public boolean isValidUserid(String userid) {
        System.err.println("Userid: " + userid);
        return (userid != null);
    }

    public boolean isValidName(String name) {
        return (name != null);
    }

    public boolean isValidPassword(String password) {
        return (password != null);
    }

    public boolean isValidEmail(String email) {
        return (email != null);
    }

    public boolean isValidPhone(String phone) {
        return (phone != null);
    }

    public boolean isValidAddress(String address) {
        return (address != null);
    }

    public boolean isValidSecQn(String secQn) {
        return (secQn != null);
    }

    public boolean isValidSecAns(String secAns) {
        return (secAns != null);
    }
}
