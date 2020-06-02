/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.EmpDetailsDTO;
import entity.EmpUpdateDTO;
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
@Named(value = "myEmpManagedBean")
@ConversationScoped
public class MyEmpManagedBean implements Serializable {

    @EJB
    private EmployeeManagementRemote employeeManagement;

    @Inject
    private Conversation conversation;

    private String empId;
    private String password;
    private String newPassword;
    private String confirmPassword;
    private String name;
    private String phone;
    private String address;
    private String email;
    private String bnkAccId;
    private Double salary;

    public MyEmpManagedBean() {
        empId = null;
        name = null;
        phone = null;
        address = null;
        email = null;
        password = null;
        newPassword = null;
        confirmPassword = null;
        bnkAccId = null;
        salary = 0.0;
    }

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
    public void startConversation() {
        conversation.begin();
    }

    public void endConversation() {
        conversation.end();
    }

    @RolesAllowed("ED-APP-USERS")
    public String employeeLogin() {

        if (isNull(empId) || conversation == null) {
            return "debug";
        }

        startConversation();

        EmpDetailsDTO myEmployee = employeeManagement.employeeLogin(empId, password);
        System.err.println("Name:" + myEmployee.getName());
        System.err.println("Phone:" + myEmployee.getPhone());
        if (!isNull(myEmployee)) {
            this.empId = myEmployee.getEmpid();
            this.name = myEmployee.getName();
            this.email = myEmployee.getEmail();
            this.address = myEmployee.getAddress();
            this.phone = myEmployee.getPhone();
            this.bnkAccId = myEmployee.getBnkAccId();
            this.salary = myEmployee.getSalary();
            this.password = "";
            this.confirmPassword = "";
            this.newPassword = "";
            return "Success";
        }

        return "Failure";

    }

    @RolesAllowed("ED-APP-USERS")
    public String updateDetails() {
        EmpUpdateDTO updateDTO = new EmpUpdateDTO(
                empId, 
                name,
                phone,
                address,
                email,
                password,
                newPassword,
                bnkAccId);
        
        return employeeManagement.updateDetails(updateDTO);
    }

    @RolesAllowed("ED-APP-USERS")
    public String logout() {
        try {
            empId = null;
            name = null;
            phone = null;
            address = null;
            email = null;
            password = null;
            newPassword = null;
            confirmPassword = null;
            bnkAccId = null;
            salary = 0.0;
            endConversation();
            return "Logout";
        } catch (Exception ex) {
            return "Debug";
        }
    }
}
