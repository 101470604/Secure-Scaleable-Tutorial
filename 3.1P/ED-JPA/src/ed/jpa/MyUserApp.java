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
public class MyUserApp {

    private MyUserDB mydb;

    public MyUserApp() {
        mydb = new MyUserDB();
    }

    public static void main(String[] args) {
        MyUserApp client = new MyUserApp();
// assuming inputs from keyboard or any GUI
        MyUserDTO myUserDTO = new MyUserDTO("000001", "Peter Smith", "123456",
                "psmith@swin.edu.au", "9876543210", "Swinburne EN510f",
                "What is my name?", "Peter");
        boolean result = client.createRecord(myUserDTO);
        client.showCreateResult(result, myUserDTO);
// assuming inputs from keyboard or any GUI
        MyUserDTO myUserDTO2 = new MyUserDTO("000006", "David Lee", "654321",
                "dlee@swin.edu.au", "0123456789", "Swinburne EN510g",
                "What is my name?", "David");
        result = client.createRecord(myUserDTO2);
        client.showCreateResult(result, myUserDTO2);
    }

    public void showCreateResult(boolean result, MyUserDTO myUserDTO) {
        if (result) {
            System.out.println("Record with primary key " + myUserDTO.getUserid()
                    + " has been created in the database table.");
        } else {
            System.out.println("Record with primary key " + myUserDTO.getUserid()
                    + " could not be created in the database table!");
        }
    }

    public boolean createRecord(MyUserDTO myUserDTO) {
        return mydb.createRecord(myUserDTO);
    }
}
