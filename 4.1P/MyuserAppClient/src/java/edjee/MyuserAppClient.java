/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edjee;

import entity.MyuserDTO;
import java.util.ArrayList;
import javax.ejb.EJB;
import session.MyuserFacadeRemote;

/**
 *
 * @author Tom
 */
public class MyuserAppClient {

    @EJB
    private static MyuserFacadeRemote myuserFacade;

    public MyuserAppClient() {
    }

    public static void main(String[] args) {
        MyuserAppClient client = new MyuserAppClient();
// assuming inputs from keyboard or any GUI
        MyuserDTO myuserDTO = new MyuserDTO("000001", "Peter Smith", "123456",
                "psmith@swin.edu.au", "9876543210", "Swinburne EN510f",
                "What is my name?", "Peter");
        boolean result = client.createRecord(myuserDTO);
        client.showCreateResult(result, myuserDTO);
// assuming inputs from keyboard or any GUI
        MyuserDTO myuserDTO2 = new MyuserDTO("000007", "David Lee", "654321",
                "dlee@swin.edu.au", "0123456789", "Swinburne EN510g",
                "What is my name?", "David");
        result = client.createRecord(myuserDTO2);
        client.showCreateResult(result, myuserDTO2);

        /**
         * Get record test *
         */
        System.out.println("\nGET RECORD TESTS:");
        MyuserDTO resultDTO = client.getRecord(myuserDTO.getUserid());

        if (resultDTO.equals(myuserDTO)) {
            System.out.println("Success: Existing user retrieved");
        } else {
            System.out.println("FAILURE: Unable to retrieve existing user");
        }

        resultDTO = client.getRecord("123456");
        if (resultDTO == null) {
            System.out.println("Success: Null returned when user does not exist");

        } else {
            System.out.println("Failure: user returned instead of null");
        }

        /**
         * Update Record Tests
         */
        // Returns false when record does not exist
        System.out.println("\nUPDATE TESTS:");

        boolean userExists = client.updateRecord(new MyuserDTO("12345", "", "", "", "", "", "", ""));
        if (!userExists) {
            System.out.println("Success: Update returns false when user does not exist");
        } else {
            System.out.println("Failure: Update returns true when user does not exist");
        }

        MyuserDTO updateDTO = new MyuserDTO("000001", "test", "test", "test", "test", "test", "test", "test");
        userExists = client.updateRecord(updateDTO);
        resultDTO = client.getRecord(updateDTO.getUserid());

        if (userExists && updateDTO.equals(resultDTO)) {
            System.out.println("Success: Values updated in database");
        } else {
            System.out.println("Failure: Values unable to be updated in database");
        }

        /**
         * DELETE TESTS
         */
        System.out.println("\nDELETE TESTS");

        boolean userDeleted = client.deleteRecord(myuserDTO.getUserid());
        resultDTO = client.getRecord(myuserDTO.getUserid());

        if (userDeleted && resultDTO == null) {
            System.out.println("Success: User successfully deleted");
        } else {
            System.out.println("Failure: unable to delete user");
        }

        userDeleted = client.deleteRecord(myuserDTO.getUserid());

        if (!userDeleted) {
            System.out.println("Success: Delete returns false if user does not exist");
        } else {
            System.out.println("Failure: Delete returns true when user does not exist");
        }

        ArrayList<MyuserDTO> testList = new ArrayList();
        String testAddress = "3 Test Address St";
        // new MyuserDTO(userid, name, password, email, phone, address, secQn, secAns)
        testList.add(new MyuserDTO("000010","", "","","",testAddress,"",""));
        testList.add(new MyuserDTO("000011","", "","","",testAddress,"",""));
        testList.add(new MyuserDTO("000012","", "","","",testAddress,"",""));
        testList.add(new MyuserDTO("000013","", "","","",testAddress,"",""));
        testList.add(new MyuserDTO("000014","", "","","",testAddress,"",""));
        
        for (int i = 0; i < testList.size(); i++){
            client.createRecord(testList.get(i));
        }
        
        ArrayList<MyuserDTO> resultList = client.getRecordsByAddress(testAddress);
        
        if (testList.equals(resultList))
        {
            System.out.println("Success: Retrieved records by address");
        } else {
            System.out.println("Failure: unable to retrieve records by address");
        }
    }

    public void showCreateResult(boolean result, MyuserDTO myuserDTO) {
        if (result) {
            System.out.println("Record with primary key " + myuserDTO.getUserid()
                    + " has been created in the database table.");
        } else {
            System.out.println("Record with primary key " + myuserDTO.getUserid()
                    + " could not be created in the database table!");
        }
    }

    public Boolean createRecord(MyuserDTO myuserDTO) {
        return myuserFacade.createRecord(myuserDTO);
    }

    public MyuserDTO getRecord(String userId) {
        return myuserFacade.getRecord(userId);
    }

    public boolean updateRecord(MyuserDTO myuserDTO) {
        return myuserFacade.updateRecord(myuserDTO);
    }

    public boolean deleteRecord(String userId) {
        return myuserFacade.deleteRecord(userId);
    }
    
    public ArrayList<MyuserDTO> getRecordsByAddress(String address) {
        return myuserFacade.getRecordsByAddress(address);
    }
}
