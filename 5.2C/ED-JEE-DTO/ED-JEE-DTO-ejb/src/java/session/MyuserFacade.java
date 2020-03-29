/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Myuser;
import entity.MyuserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tom
 */
@Stateless
public class MyuserFacade implements MyuserFacadeRemote {

    @PersistenceContext(unitName = "ED-JEE-DTO-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    private void create(Myuser myuser) {
        em.persist(myuser);
    }

    private void edit(Myuser myuser) {
        em.merge(myuser);
    }

    private void remove(Myuser myuser) {
        em.remove(em.merge(myuser));
    }

    private Myuser find(Object id) {
        return em.find(Myuser.class, id);
    }

    @Override
    public Boolean createRecord(MyuserDTO myuserDTO) {
        if (find(myuserDTO.getUserid()) != null) {
// user whose userid can be found
            return false;
        }
// user whose userid could not be found
        try {
            Myuser myuser = this.myDTO2DAO(myuserDTO);
            this.create(myuser); // add to database
            return true;
        } catch (Exception ex) {
            return false; // something is wrong, should not be here though
        }
    }

    private Myuser myDTO2DAO(MyuserDTO myuserDTO) {
        Myuser myuser = new Myuser();
        myuser.setUserid(myuserDTO.getUserid());
        myuser.setName(myuserDTO.getName());
        myuser.setPassword(myuserDTO.getPassword());
        myuser.setEmail(myuserDTO.getEmail());
        myuser.setPhone(myuserDTO.getPhone());
        myuser.setAddress(myuserDTO.getAddress());
        myuser.setSecqn(myuserDTO.getSecQn());
        myuser.setSecans(myuserDTO.getSecAns());
        return myuser;
    }

    private MyuserDTO myDAO2DTO(Myuser myuser) {
        try {
            return new MyuserDTO(
                    myuser.getUserid(),
                    myuser.getName(),
                    myuser.getPassword(),
                    myuser.getEmail(),
                    myuser.getPhone(),
                    myuser.getAddress(),
                    myuser.getSecqn(),
                    myuser.getSecans()
            );
        } catch (NullPointerException ex) {
            System.err.println("Null user object passed to MyuserFacade.myDAO2DTO");
            return new MyuserDTO("", "", "", "", "", "", "", "");
        }
    }

    /**
     * Returns an existing Myuser object
     *
     * @param userId Index of the Myuser object to be retrieved
     */
    @Override
    public MyuserDTO getRecord(String userId) {
        Myuser myuser = find(userId);

        if (myuser != null) {
            System.out.println("" + myuser.getUserid());
            return myDAO2DTO(myuser);
        } else {
            System.out.println("user is null");
            return null;
        }
    }

    @Override
    public Boolean updateRecord(MyuserDTO myuserDTO) {
        Myuser myuser = find(myuserDTO.getUserid());

        if (myuser != null) {
            myuser.setName(myuserDTO.getName());
            myuser.setPassword(myuserDTO.getPassword());
            myuser.setEmail(myuserDTO.getEmail());
            myuser.setPhone(myuserDTO.getPhone());
            myuser.setAddress(myuserDTO.getAddress());
            myuser.setSecqn(myuserDTO.getSecQn());
            myuser.setSecans(myuserDTO.getSecAns());
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteRecord(String userId) {
        Myuser myuser = find(userId);

        if (myuser != null) {
            remove(myuser);
            return true;
        }

        return false;
    }

    @Override
    public ArrayList<MyuserDTO> getRecordsByAddress(String address) {
            
        List<Myuser> queryResult = em.createQuery(
                "Select u from Myuser u Where u.address= :uAddress")
                .setParameter("uAddress", address)
                .getResultList();
        
        ArrayList<MyuserDTO> result = new ArrayList();
        for (int i = 0; i < queryResult.size(); i++)
        {
            result.add(myDAO2DTO(queryResult.get(i)));
        }

        return result;
    }

}
