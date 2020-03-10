/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.jpa;

/**
 *
 * @author Tom
 */
public class MyuserApp {

    private MyuserDB mydb;

    public MyuserApp() {
        mydb = new MyuserDB();
    }

    public static void main(String[] args) {
        MyuserApp client = new MyuserApp();
// assuming inputs from keyboard or any GUI
        MyuserDTO myuserDTO = new MyuserDTO("000001", "Peter Smith", "123456",
                "psmith@swin.edu.au", "9876543210", "Swinburne EN510f",
                "What is my name?", "Peter");
        boolean result = client.createRecord(myuserDTO);
        client.showCreateResult(result, myuserDTO);
// assuming inputs from keyboard or any GUI
        MyuserDTO myuserDTO2 = new MyuserDTO("000006", "David Lee", "654321",
                "dlee@swin.edu.au", "0123456789", "Swinburne EN510g",
                "What is my name?", "David");
        result = client.createRecord(myuserDTO2);
        client.showCreateResult(result, myuserDTO2);
        
        
        // GET RECORD TESTS
        // Returns correct record
        MyuserDTO getRecordResult = client.getRecord("000001");
        
        if (myuserDTO.getUserid().equals(getRecordResult.getUserid()))
        {
            System.err.println("SUCESS: getRecord returns correct DTO");
        }
        else{
            System.err.println("FAIL: getRecord returns wrong DTO");
        }
        
        // Returns null if record does not exist
        try {
            MyuserDTO getRecordNull = client.getRecord("000010");
        } 
        catch(NullPointerException ex){
            System.err.println("SUCESS: getRecord returns null when no user exists");
        }
        
        // RETURNS TRUE IF RECORD IS DELETED
        boolean deleteResult = client.deleteRecord("000001");
        boolean objectDeleted = false;
        
        try {
            client.getRecord("000001");
        }
        catch (NullPointerException ex){
            objectDeleted = true;
        }
        
        if(deleteResult && objectDeleted){
            System.err.println("SUCESS: MyUser deleted");
        } else
        {
            System.err.println("FAILURE: Object not deleted");
        }
        
        // RETURNS FALSE ON INVLALID DELETE
        deleteResult = client.deleteRecord("123456");

        if(deleteResult){
            System.err.println("FAILURE: Invalid delete returns true");
        } else
        {
            System.err.println("SUCESS: Valid delete returns false");
        }
        
        MyuserDTO updatedDTO = new MyuserDTO("000006", "David Update", "12345",
                "update@swin.edu.au", "98765432", "Update EN510g",
                "What is my update?", "Update");
        
        boolean updateSucceded = client.updateRecord(updatedDTO);
        MyuserDTO updatedResult = client.getRecord(updatedDTO.getUserid());
        
        if (updateSucceded &&
                updatedDTO.getUserid().equals(updatedResult.getUserid()) &&
                updatedDTO.getName().equals(updatedResult.getName()) &&
                updatedDTO.getPassword().equals(updatedResult.getPassword()) &&
                updatedDTO.getPhone().equals(updatedResult.getPhone()) &&
                updatedDTO.getAddress().equals(updatedResult.getAddress()) &&
                updatedDTO.getEmail().equals(updatedResult.getEmail()) &&
                updatedDTO.getSecAns().equals(updatedResult.getSecAns()) &&
                updatedDTO.getSecQn().equals(updatedResult.getSecQn())
                ) {
            System.err.println("SUCESS: Update Succeeded");
        }
        else
        {
             System.err.println("FAILURE: Update Failed");
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

    public boolean createRecord(MyuserDTO myuserDTO) {
        return mydb.createRecord(myuserDTO);
    }
    
    public MyuserDTO getRecord(String userid)
    {
        return mydb.getRecord(userid);
    }
    
        public boolean deleteRecord(String userid)
    {
        return mydb.deleteRecord(userid);
    }
        
        public boolean updateRecord(MyuserDTO myuserDTO) {
        return mydb.updateRecord(myuserDTO);
    }
}
