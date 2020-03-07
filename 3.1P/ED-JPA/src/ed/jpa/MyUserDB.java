/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.jpa;

import entity.MyUser;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tom
 */
public class MyUserDB {

    private EntityManager em = null;

    public MyUserDB() {
// using default persistence unit defined in the persistence.xml file
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ed-EntityPU");
        em = emf.createEntityManager();
    }

    public EntityManager getEntityManger() {
        return em;
    }

    public MyUser findMyUser(String userid) {
        return em.find(MyUser.class, userid);
    }

    public boolean createMyUser(MyUser myUser) throws Exception {
        try {
            if (findMyUser(myUser.getUserid()) != null) {
// myUser exists already
                return false;
            }
// myUser does not exist in database
            em.getTransaction().begin();
            em.persist(myUser); // to add an object to database
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean createRecord(MyUserDTO myUserDTO) {
        MyUser myUser = this.myDTO2DAO(myUserDTO);
        boolean result = false;
        try {
            result = this.createMyUser(myUser);
        } catch (Exception ex) {
        }
        return result;
    }

    private MyUser myDTO2DAO(MyUserDTO myDTO) {
        MyUser myUser = new MyUser();
        myUser.setUserid(myDTO.getUserid());
        myUser.setName(myDTO.getName());
        myUser.setPassword(myDTO.getPassword());
        myUser.setEmail(myDTO.getEmail());
        myUser.setPhone(myDTO.getPhone());
        myUser.setAddress(myDTO.getAddress());
        myUser.setSecqn(myDTO.getSecQn());
        myUser.setSecans(myDTO.getSecAns());
        return myUser;
    }
}
