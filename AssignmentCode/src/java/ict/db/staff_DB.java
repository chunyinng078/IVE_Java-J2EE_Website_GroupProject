/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.GymBean;
import ict.bean.TrainerBean;
import ict.bean.bookingBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class staff_DB {

    private String url = "";
    private String username = "";
    private String password = "";

    public staff_DB(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            //  System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection(url, username, password);
    }

    public ArrayList queryTrainer() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  trainer";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                TrainerBean tb = new TrainerBean();
                tb.setId(rs.getInt(1));
                tb.setName(rs.getString(2));
                tb.setStates(rs.getString(3));
                tb.setHrRate(rs.getInt(4));
                tb.setDescription(rs.getString(5));
                tb.setSportType(rs.getString(6));
                tb.setImageID(rs.getInt(7));
                list.add(tb);
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

    public ArrayList<TrainerBean> queryTrainerByName(String name) {
        PreparedStatement pStmnt = null;
        Connection cnnct = null;
        TrainerBean tb = null;
        ArrayList<TrainerBean> ListTb = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM trainer WHERE UPPER(name) LIKE UPPER(?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, "%" + name + "%");
//            pStmnt.setString(1, "%" + "asd" + "%");
            System.out.print("pStmnt = " + pStmnt);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                tb = new TrainerBean(rs.getInt("id"), rs.getString("name"), rs.getString("states"), rs.getInt("hrRate"), rs.getString("description"), rs.getString("sportType"), rs.getInt("imageID"));
                ListTb.add(tb);
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
        return ListTb;
    }

    public ArrayList queryGym() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  gymcenter";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                GymBean gb = new GymBean();
                gb.setId(rs.getInt(1));
                gb.setCenterName(rs.getString(2));
                gb.setDescription(rs.getString(3));
                gb.setStates(rs.getString(4));
                gb.setHrRate(rs.getInt(5));
                gb.setImageID(rs.getInt(6));
                list.add(gb);
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

    public ArrayList<GymBean> queryGymByName(String name) {
        PreparedStatement pStmnt = null;
        Connection cnnct = null;
        GymBean Gb = null;
        ArrayList<GymBean> ListGb = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM gymcenter WHERE UPPER(centerName) LIKE UPPER(?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, "%" + name + "%");
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                Gb = new GymBean(rs.getInt("id"), rs.getString("centerName"), rs.getString("description"), rs.getString("states"), rs.getInt("hrRate"), rs.getInt("imageID"));
                ListGb.add(Gb);
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
        return ListGb;
    }

    public boolean delRecord(String value, String type) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int num = 0;
        try {
            cnnct = getConnection();
            if ("trainer".equals(type)) {
                String preQueryStatement = "DELETE FROM trainer WHERE id=?";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, value);

                num = pStmnt.executeUpdate();
            } else {
                String preQueryStatement = "DELETE FROM gymcenter WHERE id=?";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, value);
                num = pStmnt.executeUpdate();
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

        return (num == 1) ? true : false;
    }

    public boolean addTrainerRecord(String TrainerName, String description, int hrRate, String result) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO trainer (id, name, states, hrRate, description, sportType, imageID) VALUES (?, ?, ?, ?, ?, ?, ?);";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setNull(1, Types.INTEGER);
            pStmnt.setString(2, TrainerName);
            pStmnt.setString(3, "enable");
            pStmnt.setInt(4, hrRate);
            pStmnt.setString(5, description);
            pStmnt.setString(6, result);
            pStmnt.setInt(7, 1);
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

    public boolean addCenterRecord(String CenterName, String description, int hrRate) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO gymcenter (id, centerName,description,states, hrRate, imageID) VALUES (?, ?, ?, ?, ?, ?);";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setNull(1, Types.INTEGER);
            pStmnt.setString(2, CenterName);
            pStmnt.setString(4, "enable");
            pStmnt.setInt(5, hrRate);
            pStmnt.setString(3, description);
            pStmnt.setInt(6, 1);
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

    public TrainerBean queryTrainerByID(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        TrainerBean tb = null;
        try {
            //1.  get Connection
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  trainer WHERE ID=?";
            //2.  get the prepare Statement
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setInt(1, id);
            ResultSet rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                // set the record detail to the customer bean
                tb = new TrainerBean();
                tb.setId(rs.getInt(1));
                tb.setName(rs.getString(2));
                tb.setStates(rs.getString(3));
                tb.setHrRate(rs.getInt(4));
                System.out.print(rs.getInt(4));
                tb.setDescription(rs.getString(5));
                tb.setSportType(rs.getString(6));
                tb.setImageID(rs.getInt(7));
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
        return tb;
    }

    public boolean editTrainerRecord(TrainerBean tb) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int num = 0;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE trainer SET name =? ,hrRate =?,description =?, sportType =?,states = ?  WHERE ID =? ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, tb.getName());
            pStmnt.setInt(2, tb.getHrRate());
            pStmnt.setString(3, tb.getDescription());
            pStmnt.setString(4, tb.getSportType());
            pStmnt.setString(5, tb.getStates());
            pStmnt.setInt(6, tb.getId());
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

    public GymBean queryCenterByID(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        GymBean gb = null;
        try {
            //1.  get Connection
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  gymcenter WHERE ID=?";
            //2.  get the prepare Statement
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setInt(1, id);
            ResultSet rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                // set the record detail to the customer bean
                gb = new GymBean();
                gb.setId(rs.getInt(1));
                gb.setCenterName(rs.getString(2));
                gb.setDescription(rs.getString(3));
                gb.setStates(rs.getString(4));
                gb.setHrRate(rs.getInt(5));
                gb.setImageID(rs.getInt(6));
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
        return gb;
    }

    public boolean editCenterRecord(GymBean gb) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int num = 0;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE gymcenter SET centerName =?, description=?,states=?,hrRate=? WHERE id=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, gb.getCenterName());
            pStmnt.setInt(4, gb.getHrRate());
            pStmnt.setString(2, gb.getDescription());
            pStmnt.setString(3, gb.getStates());
            pStmnt.setInt(5, gb.getId());
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

    public ArrayList<bookingBean> queryBookingByType(String type) {
        PreparedStatement pStmnt = null;
        Connection cnnct = null;
        bookingBean Bb = null;
        ArrayList<bookingBean> ListBb = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking WHERE type = ? and status = 'waiting'";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, type);
//            pStmnt.setString(1, "%" + "asd" + "%");
            System.out.print("pStmnt = " + pStmnt);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                Bb = new bookingBean(rs.getString("id"), rs.getString("date"), rs.getString("type"), rs.getString("typeID"), rs.getString("custID"), rs.getString("time"), rs.getString("status"));
                ListBb.add(Bb);
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
        return ListBb;
    }

    public ArrayList<bookingBean> queryBookingByTrainerId(String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<bookingBean> ListBb = new ArrayList();
        bookingBean Bb = null;
        try {
            String TrainerId = changeTrainerByUserID(id);
            //1.  get Connection
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking WHERE type = 'trainer' and status = 'waiting' and typeid = ?";
            //2.  get the prepare Statement
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setString(1, TrainerId);

            ResultSet rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                Bb = new bookingBean(rs.getString("id"), rs.getString("date"), rs.getString("type"), rs.getString("typeID"), rs.getString("custID"), rs.getString("time"), rs.getString("status"));
                ListBb.add(Bb);
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
        return ListBb;
    }

    public String changeTrainerByUserID(String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        String result = "";
        bookingBean Bb = null;
        try {
            //1.  get Connection
            cnnct = getConnection();
            String preQueryStatement = "SELECT trainer.id FROM trainer INNER JOIN user WHERE user.name=trainer.name and user.userid=?";
            //2.  get the prepare Statement
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setString(1, id);
            ResultSet rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                // set the record detail to the customer bean
                result = rs.getString(1);
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
        return result;
    }

    public bookingBean queryBookingByID(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        bookingBean Bb = null;
        try {
            //1.  get Connection
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  booking WHERE ID=?";
            //2.  get the prepare Statement
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setInt(1, id);
            ResultSet rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                // set the record detail to the customer bean
                Bb = new bookingBean();
                Bb.setId(rs.getString(1));
                Bb.setDate(rs.getString(2));
                Bb.setType(rs.getString(3));
                Bb.setTypeId(rs.getString(4));
                Bb.setCustid(rs.getString(5));
                Bb.setTime(rs.getString(6));
                Bb.setStatus(rs.getString(7));
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
        return Bb;
    }

    public boolean editBookingRecord(bookingBean Bb) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int num = 0;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE booking SET status=? WHERE id=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            System.out.print("status is " + Bb.getStatus());
            System.out.print("status is " + Bb.getId());
            pStmnt.setString(1, Bb.getStatus());
            pStmnt.setString(2, Bb.getId());
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

    public boolean editBookingRecord1(bookingBean Bb) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int num = 0;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE booking SET status=?,date=?,time=? WHERE id=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, "waiting");
            pStmnt.setString(2, Bb.getDate());
            pStmnt.setString(3, Bb.getTime());
            pStmnt.setString(4, Bb.getId());
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

    public ArrayList<TrainerBean> queryTrainerBykeyword(String keyword) {
        PreparedStatement pStmnt = null;
        Connection cnnct = null;
        TrainerBean Tb = null;
        ArrayList<TrainerBean> ListTb = new ArrayList();
        try {
            cnnct = getConnection();
            String[] keywords = keyword.split("\\,");
            for (String w : keywords) {
                String preQueryStatement = "SELECT * FROM trainer WHERE UPPER(sportType) LIKE UPPER(?)";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, "%" + w + "%");
                ResultSet rs = null;
                rs = pStmnt.executeQuery();

                while (rs.next()) {
                    boolean have = false;
                    for (int i = 0; i < ListTb.size(); i++) {
                        if (ListTb.get(i).getId() == rs.getInt("id")) {
                            have = true;
                        }
                    }
                    if (have) {
                        continue;
                    } else {
                        Tb = new TrainerBean(rs.getInt("id"), rs.getString("name"), rs.getString("states"), rs.getInt("hrRate"), rs.getString("description"), rs.getString("sportType"), rs.getInt("imageID"));
                        ListTb.add(Tb);
                    }
                }
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
        return ListTb;
    }

    public boolean addTrainerRequest(String date, String time, String typeid, String userId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        System.out.print("userId is " + userId);
//        userId="1";
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO booking (id, date, type, typeid,custid, time, status) VALUES (?,?, ?, ?, ?, ?, ?);";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setNull(1, Types.INTEGER);
            pStmnt.setString(2, date);
            pStmnt.setString(3, "trainer");
            pStmnt.setString(4, typeid);
            pStmnt.setString(5, userId);
            pStmnt.setString(6, time + ":00:00");
            pStmnt.setString(7, "waiting");
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

    public boolean addCenterRequest(String date, String time, String id, String userId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        System.out.print("userId is " + userId);
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO booking (id, date, type, typeid,custid, time, status) VALUES (?,?, ?, ?, ?, ?, ?);";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setNull(1, Types.INTEGER);
            pStmnt.setString(2, date);
            pStmnt.setString(3, "trainer");
            pStmnt.setString(4, id);
            pStmnt.setString(5, userId);
            pStmnt.setString(6, time + ":00:00");
            pStmnt.setString(7, "waiting");
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

    public int countData(String date, String time) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int number = 0;
        bookingBean Bb;
        ArrayList<bookingBean> ListBb = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking where date= ? and time= ? and type=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, date);
            pStmnt.setString(2, time + ":00:00");
            pStmnt.setString(3, "trainer");
            ResultSet rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                Bb = new bookingBean();
                ListBb.add(Bb);
            }
//            System.out.println(rs.getString("number")+"is number");
            number = ListBb.size() * 2;
            preQueryStatement = "SELECT * FROM booking where date= ? and time= ? and type=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, date);
            pStmnt.setString(2, time + ":00:00");
            pStmnt.setString(3, "center");
            rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();
            ListBb = new ArrayList();
            while (rs.next()) {
                Bb = new bookingBean();
                ListBb.add(Bb);
            }
//            System.out.println(rs.getString("number")+"is number");
            number += ListBb.size();
            System.out.println("number is" + number);

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public ArrayList<bookingBean> queryBookingByCustID(String id) {
        PreparedStatement pStmnt = null;
        Connection cnnct = null;
        bookingBean Bb = null;
        ArrayList ListBb = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking WHERE custid = ? and status = 'confirm'";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                Bb = new bookingBean(rs.getString("id"), rs.getString("date"), rs.getString("type"), rs.getString("typeID"), rs.getString("custID"), rs.getString("time"), rs.getString("status"));
                ListBb.add(Bb);
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
        return ListBb;
    }

    public ArrayList<bookingBean> queryBookingByCustID1(String id) {
        PreparedStatement pStmnt = null;
        Connection cnnct = null;
        bookingBean Bb = null;
        ArrayList ListBb = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking WHERE custid = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                Bb = new bookingBean(rs.getString("id"), rs.getString("date"), rs.getString("type"), rs.getString("typeID"), rs.getString("custID"), rs.getString("time"), rs.getString("status"));
                ListBb.add(Bb);
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
        return ListBb;
    }

    public bookingBean queryBookingByDateTime(String id) {
        PreparedStatement pStmnt = null;
        Connection cnnct = null;
        bookingBean Bb = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking WHERE custid = ? and status = 'confirm' and date>CURRENT_DATE or (date=CURRENT_DATE and time>CURRENT_TIME) ORDER BY date, time ASC";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            System.out.println("rs is " + rs);
            if (rs.next()) {
                Bb = new bookingBean(rs.getString("id"), rs.getString("date"), rs.getString("type"), rs.getString("typeID"), rs.getString("custID"), rs.getString("time"), rs.getString("status"));
            } else {
                return null;
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
        return Bb;
    }

}
