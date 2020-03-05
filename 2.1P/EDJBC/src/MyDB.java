/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Statement;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Tom
 */
public class MyDB {

    public void createMyuserTable() {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.execute("CREATE TABLE MYUSER ( "
                    + "UserId CHAR(6) CONSTRAINT PK_CUSTOMER PRIMARY KEY, "
                    + " Name CHAR(30), Password CHAR(6), Email CHAR(30), "
                    + " Phone CHAR(10), Address CHAR(60), "
                    + " SecQn CHAR(60), SecAns CHAR(60))");
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }

    public static Connection getConnection() throws SQLException,
            IOException {
        System.setProperty("jdbc.drivers", "org.apache.derby.jdbc.ClientDriver");
        String url = "jdbc:derby://localhost/sun-appserv-samples;create = true";
        String username = "APP";
        String password = "APP";
        return DriverManager.getConnection(url, username, password);
    }

    public void dropMyuserTable() {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.execute("DROP TABLE MYUSER");
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
            }
            ex = ex.getNextException();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }

    public void addRecords(ArrayList<MyUser> myUsers) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO MYUSER VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            for (MyUser myuser : myUsers) {
                pStmnt.setString(1, myuser.getUserid());
                pStmnt.setString(2, myuser.getName());
                pStmnt.setString(3, myuser.getPassword());
                pStmnt.setString(4, myuser.getEmail());
                pStmnt.setString(5, myuser.getPhone());
                pStmnt.setString(6, myuser.getAddress());
                pStmnt.setString(7, myuser.getSecQn());
                pStmnt.setString(8, myuser.getSecAns());
                int rowCount = pStmnt.executeUpdate();
                if (rowCount == 0) {
                    throw new SQLException("Cannot insert records!");
                }
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }
     
    public boolean createRecord(MyUser myuser) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = getConnection();
            // Check if the User exists and return false if it does
            String selectStatement = "SELECT * FROM MYUSER WHERE USERID ='" + myuser.getUserid() + "'";
            pStmnt = cnnct.prepareStatement(selectStatement);
            ResultSet rslt = pStmnt.executeQuery();
            if (rslt.next()) {
                return false;
            } else {
                // Reset var for next query
                pStmnt = null;
                String query = "INSERT INTO MYUSER VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                pStmnt = cnnct.prepareStatement(query);

                pStmnt.setString(1, myuser.getUserid());
                pStmnt.setString(2, myuser.getName());
                pStmnt.setString(3, myuser.getPassword());
                pStmnt.setString(4, myuser.getEmail());
                pStmnt.setString(5, myuser.getPhone());
                pStmnt.setString(6, myuser.getAddress());
                pStmnt.setString(7, myuser.getSecQn());
                pStmnt.setString(8, myuser.getSecAns());
                int rowCount = pStmnt.executeUpdate();
                if (rowCount == 0) {
                    throw new SQLException("Cannot insert records!");
                }
            }

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();

                } catch (SQLException sqlEx) {
                }
            }
        }
        // If this code is reached the user was inserted successfully 
        return true;
    }

    public MyUser getRecord(String userId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        MyUser result = null;
        try {
            cnnct = getConnection();
            
            String selectStatement = "SELECT * FROM MYUSER WHERE USERID ='" + userId + "'";
            pStmnt = cnnct.prepareStatement(selectStatement);
            ResultSet rslt = pStmnt.executeQuery();

            while (rslt.next()) {
                result = new MyUser(rslt.getString("USERID"),
                        rslt.getString("NAME"),
                        rslt.getString("PASSWORD"),
                        rslt.getString("EMAIL"),
                        rslt.getString("PHONE"),
                        rslt.getString("ADDRESS"),
                        rslt.getString("SECQN"),
                        rslt.getString("SECANS")
                );
            }

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();

                } catch (SQLException sqlEx) {
                }
            }
        }
        return result;
    }

    public boolean updateRecord(MyUser myuser) {
         Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = getConnection();
            // Check if the User exists and return false if it does
            String selectStatement = "SELECT * FROM MYUSER WHERE USERID ='" + myuser.getUserid() + "'";
            pStmnt = cnnct.prepareStatement(selectStatement);
            ResultSet rslt = pStmnt.executeQuery();
            if (!rslt.next()) {
                return false;
            } else {
                // Reset var for next query
                pStmnt = null;
                String query = "UPDATE MYUSER SET NAME = ?, "
                        + "PASSWORD = ?,"
                        + "EMAIL    = ?,"
                        + "PHONE    = ?,"
                        + "ADDRESS  = ?,"
                        + "SECQN    = ?,"
                        + "SECANS   = ?"
                        + "WHERE USERID = ?";
                pStmnt = cnnct.prepareStatement(query);

                pStmnt.setString(1, myuser.getName());
                pStmnt.setString(2, myuser.getPassword());
                pStmnt.setString(3, myuser.getEmail());
                pStmnt.setString(4, myuser.getPhone());
                pStmnt.setString(5, myuser.getAddress());
                pStmnt.setString(6, myuser.getSecQn());
                pStmnt.setString(7, myuser.getSecAns());
                pStmnt.setString(8, myuser.getUserid());
                
                int rowCount = pStmnt.executeUpdate();
                if (rowCount == 0) {
                    throw new SQLException("Cannot insert records!");
                }
            }

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();

                } catch (SQLException sqlEx) {
                }
            }
        }
        // If this code is reached the user was Updated successfully 
        return true;
    }

    public boolean deleteRecord(String userId) {
       Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            
            String selectStatement = "DELETE FROM MYUSER WHERE USERID = ?";
            pStmnt = cnnct.prepareStatement(selectStatement);
            pStmnt.setString(1, userId);
            Integer rslt = pStmnt.executeUpdate();

            if (rslt != 0)
            {
                return true;
            }

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();

                } catch (SQLException sqlEx) {
                }
            }
        }
        return false;
    }

}
