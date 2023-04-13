/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.UserBean;
import ict.bean.roleBean;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lcshum
 */
public class UserDB {

    private String url = "";
    private String username = "";
    private String password = "";

    public UserDB(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection(url, username, password);
    }

    public void createDB(String createDB) {
        Connection cnnct = null;
        Statement stmnt = null;

        try {
            cnnct = getConnection();  // the connection 
            stmnt = cnnct.createStatement();  // create statement

            String sql
                    = "CREATE DATABASE " + createDB;
            stmnt.execute(sql);

            stmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void createUserTable() {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();  // the connection 
            stmnt = cnnct.createStatement();  // create statement
            String sql
                    = "CREATE TABLE IF NOT EXISTS user ("
                    + "  userid int(20) NOT NULL,\n"
                    + "  username varchar(20) NOT NULL,\n"
                    + "  password varchar(20) NOT NULL,\n"
                    + "  name varchar(20) NOT NULL,\n"
                    + "  role varchar(20) NOT NULL,\n"
                    + "  tel int(8) NOT NULL,\n"
                    + "  enable varchar(20) NOT NULL,\n"
                    + "PRIMARY KEY (userid)"
                    + ")";
            stmnt.execute(sql);
            stmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean addRecord(String userid, String username, String password, String name, String role, int tel, String enable) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT  INTO  user  (`username`, `password`, `name`, `role`, `tel`, `enable`) VALUES  (?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, username);
            pStmnt.setString(2, password);
            pStmnt.setString(3, name);
            pStmnt.setString(4, role);
            pStmnt.setInt(5, tel);
            pStmnt.setString(6, enable);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean addTrainer(String name) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT  INTO  trainer  (`name`,`states`,`hrRate`,`description`,`imageID`) VALUES  (?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, name);
            pStmnt.setString(2, "disable");
            pStmnt.setString(3, "");
            pStmnt.setString(4, "");
            pStmnt.setInt(5, 0);

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            System.out.println(isSuccess);
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public UserBean queryUserByID(String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        UserBean ub = null;
        try {
            //1.  get Connection
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  user WHERE userID=?";
            //2.  get the prepare Statement
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setString(1, id);
            ResultSet rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                ub = new UserBean();
                // set the record detail to the customer bean
                ub = new UserBean();
                ub.setUserid(rs.getString(1));
                ub.setUsername(rs.getString(2));
                ub.setPassword(rs.getString(3));
                ub.setName(rs.getString(4));
                ub.setRole(rs.getString(5));
                ub.setTel(rs.getInt(6));
                ub.setEnable(rs.getString(7));
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ub;
    }

    public ArrayList queryUser() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  user";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                UserBean ub = new UserBean();
                ub.setUserid(rs.getString(1));
                ub.setUsername(rs.getString(2));
                ub.setPassword(rs.getString(3));
                ub.setName(rs.getString(4));
                ub.setRole(rs.getString(5));
                ub.setTel(rs.getInt(6));
                ub.setEnable(rs.getString(7));
                list.add(ub);
            }
            return list;
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
        return null;
    }

    public ArrayList queryUserByName(String name) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  user WHERE NAME LIKE ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, "%" + name + "%");
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                UserBean ub = new UserBean();
                ub.setUserid(rs.getString(1));
                ub.setUsername(rs.getString(2));
                ub.setPassword(rs.getString(3));
                ub.setName(rs.getString(4));
                ub.setRole(rs.getString(5));
                ub.setTel(rs.getInt(6));
                ub.setEnable(rs.getString(7));
                list.add(ub);
            }
            return list;
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
        return null;
    }

    public ArrayList queryUserByTel(String tel) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  user WHERE TEL=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, tel);
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                UserBean ub = new UserBean();
                ub.setUserid(rs.getString(1));
                ub.setUsername(rs.getString(2));
                ub.setPassword(rs.getString(3));
                ub.setName(rs.getString(4));
                ub.setRole(rs.getString(5));
                ub.setTel(rs.getInt(6));
                ub.setEnable(rs.getString(7));
                list.add(ub);
            }
            return list;
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
        return null;
    }

    public boolean delRecord(String userid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int num = 0;
        try {
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM user WHERE userid=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, userid);

            num = pStmnt.executeUpdate();

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

        return (num == 1) ? true : false;
    }

    public boolean editRecord(UserBean ub) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int num = 0;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE user SET username=? ,password=? ,name=? ,role=? ,tel=?,enable=? WHERE userid=?";

            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, ub.getUsername());
            pStmnt.setString(2, ub.getPassword());
            pStmnt.setString(3, ub.getName());
            pStmnt.setString(4, ub.getRole());
            pStmnt.setInt(5, ub.getTel());
            pStmnt.setString(6, ub.getEnable());
            pStmnt.setString(7, ub.getUserid());

            //Statement s = cnnct.createStatement();
            num = pStmnt.executeUpdate();

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
        return (num == 1) ? true : false;
    }

    public void dropUserTable() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "DROP TABLE user";
            Statement s = cnnct.createStatement();
            s.execute(preQueryStatement);

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

    public boolean checkUserName(UserBean ub1) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean usernameFound = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "select * from user where username = ?and userid != ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, ub1.getUsername());
            pStmnt.setString(2, ub1.getUserid());
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                UserBean ub = new UserBean();
                list.add(ub);
            }

            if (list.size() != 0) {
                usernameFound = true;
            }
            return usernameFound;
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

    public ArrayList queryUserRole() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT distinct name FROM `role` ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();
            String role;
            ArrayList list = new ArrayList();

            while (rs.next()) {
                role = rs.getString(1);
                list.add(role);
            }

            return list;
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
        return null;
    }

    public boolean addRole(String role) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT  INTO  role  (`name`) VALUES  (?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, role);

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean roleUsing(String role) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean roleFound = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "select * from user where role = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, role);

            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                UserBean ub = new UserBean();
                list.add(ub);
            }

            if (list.size() != 0) {
                roleFound = true;
            }
            return roleFound;
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

    public String searchUserRole(String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT role FROM  user WHERE userid=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            String role = "";
            while (rs.next()) {
                role = rs.getString(1);
            }
            return role;
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
        return null;
    }

    public String searchUserRoleType(String role) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT type FROM role WHERE name=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, role);
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            String type = "";
            while (rs.next()) {
                type = rs.getString(1);
            }
            return type;
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
        return null;
    }

    public boolean searchUserBooking(String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean havebooking = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking WHERE custid=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            String type = "";
            int count = 0;

            while (rs.next()) {
                count++;
            }
            if (count != 0) {
                havebooking = true;
            }

            return havebooking;
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
        return true;
    }

    public boolean isValidUser(String user, String pwd) throws SQLException, IOException {
        boolean isValid = false;
        com.mysql.jdbc.Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {

            cnnct = (com.mysql.jdbc.Connection) getConnection();
            String preQueryStatement = "SELECT * FROM user WHERE username=? and password=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, user);
            pStmnt.setString(2, pwd);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                isValid = true;
            }

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isValid;

    }

    public boolean isUserEnable(String username) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            //1.  get Connection
            cnnct = getConnection();
            String preQueryStatement = "SELECT enable FROM  user WHERE username=?";
            //2.  get the prepare Statement
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setString(1, username);
            ResultSet rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();
            String enable = "";
            if (rs.next()) {
                enable = rs.getString(1);

            }
            boolean isEnable;
            if (enable.equalsIgnoreCase("true")) {
                isEnable = true;
            } else {
                isEnable = false;

            }
            pStmnt.close();
            cnnct.close();

            return isEnable;

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public String getUserRole(String username) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            //1.  get Connection
            cnnct = getConnection();
            String preQueryStatement = "SELECT role FROM  user WHERE username=?";
            //2.  get the prepare Statement
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setString(1, username);
            ResultSet rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();
            String role = "";
            if (rs.next()) {
                role = rs.getString(1);

            }

            pStmnt.close();
            cnnct.close();

            return role;

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean isRoleEnable(String role) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            //1.  get Connection
            cnnct = getConnection();
            String preQueryStatement = "SELECT enable FROM  role WHERE name=?";
            //2.  get the prepare Statement
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setString(1, role);
            ResultSet rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();
            String enable = "";
            if (rs.next()) {
                enable = rs.getString(1);
            }
            boolean isEnable;
            if (enable.equalsIgnoreCase("true")) {
                isEnable = true;
            } else {
                isEnable = false;

            }
            pStmnt.close();
            cnnct.close();

            return isEnable;

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public String queryUserIDByName(String name) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT userid FROM  user WHERE username = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, name);
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();
            String id = "";
            while (rs.next()) {

                id = rs.getString(1);

            }
            return id;
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
        return null;
    }
}
