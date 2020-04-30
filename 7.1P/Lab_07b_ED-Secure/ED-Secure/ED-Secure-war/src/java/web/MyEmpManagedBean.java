/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.Serializable;
import static java.util.Objects.isNull;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.inject.Inject;
import session.EmployeeManagementRemote;

/**
 *
 * @author Tom
 */
@Named(value = "MyEmpManagedBean")
@ConversationScoped
public class MyEmpManagedBean implements Serializable{

    @EJB
    private EmployeeManagementRemote employeeManagement;

    @Inject
    private Conversation conversation;
    
    private String empId;
    private Double salary;
    private String password;
    private String newPassword;
    private String confirmPassword;
    private String name;
    private String phone;
    private String address;
    private String email;
    private String bnkAccId;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBnkAccId() {
        return bnkAccId;
    }

    public void setBnkAccId(String bnkAccId) {
        this.bnkAccId = bnkAccId;
    }
    
  
    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public MyEmpManagedBean() {
    }
    
    public void startConversation() {
        conversation.begin();
    }

    public void endConversation() {
        conversation.end();
    }
    
    @RolesAllowed("ED-APP-USERS")
    public String loginEmployee(){
        startConversation();
        
        if (isNull(empId) || conversation == null) {
            return "debug";
        }

        return "Success";
    }
    
}
