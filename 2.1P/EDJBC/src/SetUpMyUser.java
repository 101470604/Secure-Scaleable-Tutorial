/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author Tom32
 */
public class SetUpMyUser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MyDB myDB = new MyDB();
        /*
         *   Drop Table for clean start
         */
       // myDB.dropMyuserTable();
        myDB.createMyuserTable();
        
        
        ArrayList<MyUser> aList = prepareMyUserData();
        myDB.addRecords(aList);
        
        
        MyUser myUser1 = new MyUser("000001", "MEME MGEE", "123456",
                "epicmeme@swin.edu.au", "9876543210", "Swinburne EN510f",
                "What is my name?", "Peter");
        
        boolean result = myDB.deleteRecord(myUser1.getUserid());
        
        if (result)
            System.out.println("UPDATE SUCCESSFUL");
        else 
            System.out.println("UPDATE FAILED");
       
    }

    public static ArrayList<MyUser> prepareMyUserData() {
        ArrayList<MyUser> myList = new ArrayList<MyUser>();
        MyUser myUser1 = new MyUser("000001", "Peter Smith", "123456",
                "psmith@swin.edu.au", "9876543210", "Swinburne EN510f",
                "What is my name?", "Peter");
        MyUser myUser2 = new MyUser("000002", "James T. Kirk", "234567",
                "jkirk@swin.edu.au", "8765432109", "Swinburne EN511a",
                "What is my name?", "James");
        MyUser myUser3 = new MyUser("000003", "Sheldon Cooper", "345678",
                "scooper@swin.edu.au", "7654321098", "Swinburne EN512a",
                "What is my last name?", "Cooper");

        MyUser myUser4 = new MyUser("000004", "Clark Kent", "456789",
                "ckent@swin.edu.au", "6543210987", "Swinburne EN513a",
                "What is my last name?", "Kent");
        MyUser myUser5 = new MyUser("000005", "Harry Potter", "567890",
                "hpotter@swin.edu.au", "6543210987", "Swinburne EN514a",
                "What is my last name?", "Potter");
        myList.add(myUser1);
        myList.add(myUser2);
        myList.add(myUser3);
        myList.add(myUser4);
        myList.add(myUser5);
        return myList;
    }
}
