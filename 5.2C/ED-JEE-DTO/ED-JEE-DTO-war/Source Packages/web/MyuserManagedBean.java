/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.MyuserDTO;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
        return this.userid;
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

    public boolean checkForm() {
        return isValidUserid(this.userid)
                && isValidName(name)
                && isValidPassword(password)
                && isValidPassword(cPassword)
                && isValidEmail(email)
                && isValidPhone(phone)
                && isValidAddress(address)
                && isValidSecQn(secQn)
                && isValidSecAns(secAns)
                && password.equals(cPassword);
    }

    /*
* add a user to the database
* @return "success" if the add operation is successful
* "failure" otherwise
     */
    public String addUser() {
        // Prevents an invalid form submission from erasing data
        saveToFlash();

        if (checkForm()) {
            if (notifyUser()) {
                MyuserDTO myuserDTO = new MyuserDTO(userid, name, password, email, phone, address, secQn, secAns);
                if (myuserFacade.getRecord(userid) != null) {
                    if (myuserFacade.updateRecord(myuserDTO)) {
                        return "success";
                    }
                } else {
                    System.err.println("New user");
                    if (myuserFacade.createRecord(myuserDTO)) {
                        return "success";
                    }
                }
            }
        }
        return "failure";
    }
    // Search for the user & store in flash scope for retrieval

    public String findUser() {
        if (isValidUserid(userid)) {
            MyuserDTO myUserDTO = myuserFacade.getRecord(userid);

            if (myUserDTO == null) {
                myUserDTO = new MyuserDTO(userid, "", "", "", "", "", "", "");
            }

            Flash fScope = FacesContext.getCurrentInstance().getExternalContext().getFlash();
            fScope.setKeepMessages(true);
            fScope.put("myuserDTO", myUserDTO);

            return "success";
        }
        return "failure";
    }

    public void loadFromFlash() {
        Flash fStore = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        MyuserDTO storedUser = (MyuserDTO) fStore.get("myuserDTO");

        if (storedUser != null) {
            //(userid, name, password, email, phone, address, secQn, secAns)
            userid = storedUser.getUserid();
            name = storedUser.getName();
            password = storedUser.getPassword();
            email = storedUser.getEmail();
            phone = storedUser.getPhone();
            address = storedUser.getAddress();
            secQn = storedUser.getSecQn();
            secAns = storedUser.getSecAns();
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("findUser.xhtml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveToFlash() {
        Flash fScope = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        fScope.setKeepMessages(true);
        fScope.put("myuserDTO", new MyuserDTO(userid, name, password, email, phone, address, secQn, secAns));
    }

    private boolean notifyUser() {
        String smtpServer = "smtp-mail.outlook.com";
        String from = "101470604@student.swin.edu.au";
        String to = email;
        String subject = "User info changed";
        String body = "Hi " + name + ",\nYour user information has been updated in our records"
                + "\nIf this was not performed by you please contact us at xyz@student.swin,edu.au,"
                + "\nRegard, 5.1P Development Team\n";
        String emailUser = from;
        String password = "";
        try {
            Properties props = System.getProperties();
            // -- Attaching to default Session, or we could start a new one --
            props.put("mail.smtp.host", smtpServer);
            props.put("mail.smtp.port", 587);
            props.put("mail.smtp.auth", true);
            props.put("mail.smtp.starttls.enable", true);
            // -- prepare a password authenticator --
            MyAuthenticator myPA = new MyAuthenticator(emailUser, password); // see MyAuthenticator class
            // get a session
            Session session = Session.getInstance(props, myPA);
            // -- Create a new message --
            Message msg = new MimeMessage(session);

            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            // -- Set the subject and body text --
            msg.setSubject(subject);
            msg.setText(body);
            // -- Set some other header information --
            msg.setHeader("X-Mailer", "Outlook");
            msg.setSentDate(new Date());
            // -- Send the message --
            Transport.send(msg);
            Transport.send(msg, emailUser, password);
            System.out.println("Message sent OK.");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private class MyAuthenticator extends Authenticator {

        PasswordAuthentication mypa;

        public MyAuthenticator(String username, String password) {
            mypa = new PasswordAuthentication(username, password);
        }

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return mypa;
        }
    }

    public boolean isValidUserid(String userid) {
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
