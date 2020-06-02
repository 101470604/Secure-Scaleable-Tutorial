/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Tom
 */
public class EmpDetailsDTO {

    String empid;
    String name;
    String phone;
    String address;
    String email;
    String bnkAccId;
    Double salary;

    public EmpDetailsDTO(String empid, String name, String phone, String address, String email, String bnkAccId, Double salary) {
        this.empid = empid;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.bnkAccId = bnkAccId;
        this.salary = salary;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
