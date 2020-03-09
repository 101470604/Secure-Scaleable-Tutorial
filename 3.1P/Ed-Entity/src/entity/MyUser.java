/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Tom
 */
@Entity
@Table(name = "MYUSER")
@NamedQueries({
    @NamedQuery(name = "MyUser.findAll", query = "SELECT m FROM MyUser m"),
    @NamedQuery(name = "MyUser.findByUserid", query = "SELECT m FROM MyUser m WHERE m.userid = :userid"),
    @NamedQuery(name = "MyUser.findByName", query = "SELECT m FROM MyUser m WHERE m.name = :name"),
    @NamedQuery(name = "MyUser.findByPassword", query = "SELECT m FROM MyUser m WHERE m.password = :password"),
    @NamedQuery(name = "MyUser.findByEmail", query = "SELECT m FROM MyUser m WHERE m.email = :email"),
    @NamedQuery(name = "MyUser.findByPhone", query = "SELECT m FROM MyUser m WHERE m.phone = :phone"),
    @NamedQuery(name = "MyUser.findByAddress", query = "SELECT m FROM MyUser m WHERE m.address = :address"),
    @NamedQuery(name = "MyUser.findBySecqn", query = "SELECT m FROM MyUser m WHERE m.secqn = :secqn"),
    @NamedQuery(name = "MyUser.findBySecans", query = "SELECT m FROM MyUser m WHERE m.secans = :secans")})
public class MyUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "USERID")
    private String userid;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "SECQN")
    private String secqn;
    @Column(name = "SECANS")
    private String secans;

    public MyUser() {
    }

    public MyUser(String userid) {
        this.userid = userid;
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

    public String getSecqn() {
        return secqn;
    }

    public void setSecqn(String secqn) {
        this.secqn = secqn;
    }

    public String getSecans() {
        return secans;
    }

    public void setSecans(String secans) {
        this.secans = secans;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MyUser)) {
            return false;
        }
        MyUser other = (MyUser) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MyUser[ userid=" + userid + " ]";
    }
    
}
