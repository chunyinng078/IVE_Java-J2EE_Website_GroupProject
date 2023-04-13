/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.CustomerBean;
import ict.bean.GymBean;
import ict.bean.GymBeanWithID;
import ict.bean.UserBean;
import ict.bean.bookingBean;
import ict.bean.incomeBean;
import ict.bean.roleBean;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lcshum
 */
public class bookingDB {

    private String url = "";
    private String username = "";
    private String password = "";

    public bookingDB(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            //  System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(bookingDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection(url, username, password);
    }

    public ArrayList queryBookingByID(String id, String type) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        bookingBean bb = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  booking WHERE type= ? and typeid = ? and status = 'confirm'";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, type);
            pStmnt.setString(2, id);

            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                bb = new bookingBean();
                bb.setId(rs.getString(1));
                bb.setDate(rs.getString(2));
                bb.setType(rs.getString(3));
                bb.setTypeId(rs.getString(4));
                bb.setCustid(rs.getString(5));
                bb.setTime(rs.getString(6));
                bb.setStatus(rs.getString(7));
                list.add(bb);
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

    public ArrayList queryGYM() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        GymBeanWithID gb = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  gymcenter";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                gb = new GymBeanWithID();
                gb.setCenterID(rs.getString(1));
                gb.setCenterName(rs.getString(2));
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

    public ArrayList queryRecords() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        bookingBean gb = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  booking";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                gb = new bookingBean();
                gb.setId(rs.getString(1));
                gb.setDate(rs.getString(2));
                gb.setType(rs.getString(3));
                gb.setTypeId(rs.getString(4));
                gb.setCustid(rs.getString(5));
                gb.setTime(rs.getString(6));
                gb.setStatus(rs.getString(7));

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

    public boolean haveBooking(String id, String type) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean haveBooking = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "select * from booking where type = ? and typeid = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, type);
            pStmnt.setString(2, id);

            ResultSet rs = pStmnt.executeQuery();

            int count = 0;
            while (rs.next()) {
                count++;
            }

            if (count != 0) {
                haveBooking = true;
            }
            return haveBooking;
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

    public int countBookingMonth(int year, int month, String type, String typeid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int count = 0;

        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT count(*) FROM booking WHERE MONTH(date) = ? and YEAR(date) = ? and type = ? and typeid = ? and status = 'confirm'";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, month);
            pStmnt.setInt(2, year);
            pStmnt.setString(3, type);
            pStmnt.setString(4, typeid);

            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                count = rs.getInt(1);
            }

            return count;
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
        return count;
    }

    public int countBookingYear(int year, String type, String typeid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int count = 0;

        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT count(*) FROM booking WHERE YEAR(date) = ? and type = ? and typeid = ? and status = 'confirm'";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, year);
            pStmnt.setString(2, type);
            pStmnt.setString(3, typeid);

            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                count = rs.getInt(1);
            }

            return count;
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
        return count;
    }

    public ArrayList queryTrainerMonthIncome(String year, String month) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        incomeBean gb = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT distinct trainer.id,name, COUNT(booking.id)*trainer.hrRate as income "
                    + "FROM booking "
                    + "INNER JOIN trainer "
                    + "ON booking.typeid = trainer.id "
                    + "WHERE booking.type='trainer' and booking.typeid = trainer.id "
                    + "and YEAR(booking.date) = ? and MONTH(booking.date) = ? and booking.status = 'confirm' "
                    + "GROUP by  trainer.id";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, year);
            pStmnt.setString(2, month);

            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                gb = new incomeBean();
                gb.setId(rs.getString(1));
                gb.setName(rs.getString(2));
                gb.setIncome(rs.getString(3));
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

    public ArrayList queryTrainerYearIncome(String year) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        incomeBean gb = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT distinct trainer.id,name, COUNT(booking.id)*trainer.hrRate as income\n"
                    + "FROM booking "
                    + "INNER JOIN trainer "
                    + "ON booking.typeid = trainer.id "
                    + "WHERE booking.type='trainer' and booking.typeid = trainer.id and booking.status = 'confirm' "
                    + "and YEAR(booking.date) = ? "
                    + "GROUP by  trainer.id";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, year);

            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                gb = new incomeBean();
                gb.setId(rs.getString(1));
                gb.setName(rs.getString(2));
                gb.setIncome(rs.getString(3));
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

    public ArrayList queryCenterMonthIncome(String year, String month) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        incomeBean gb = null;
        try {
            cnnct = getConnection();
            String preQueryStatement
                    = "SELECT distinct gymcenter.id, gymcenter.centerName , COUNT(booking.id)*gymcenter.hrRate as income "
                    + "FROM booking "
                    + "INNER JOIN gymcenter "
                    + "ON booking.typeid = gymcenter.id "
                    + "WHERE booking.type='center' and booking.typeid = gymcenter.id and booking.status = 'confirm' "
                    + "and YEAR(booking.date) = ? and MONTH(booking.date) = ?"
                    + "GROUP by  gymcenter.id";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, year);
            pStmnt.setString(2, month);

            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                gb = new incomeBean();
                gb.setId(rs.getString(1));
                gb.setName(rs.getString(2));
                gb.setIncome(rs.getString(3));
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

    public ArrayList queryCenterYearIncome(String year) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        incomeBean gb = null;
        try {
            cnnct = getConnection();
            String preQueryStatement
                    = "SELECT distinct gymcenter.id, gymcenter.centerName , COUNT(booking.id)*gymcenter.hrRate as income "
                    + "FROM booking "
                    + "INNER JOIN gymcenter "
                    + "ON booking.typeid = gymcenter.id "
                    + "WHERE booking.type='center' and booking.typeid = gymcenter.id and booking.status = 'confirm' "
                    + "and YEAR(booking.date) = ? "
                    + "GROUP by  gymcenter.id";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, year);

            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                gb = new incomeBean();
                gb.setId(rs.getString(1));
                gb.setName(rs.getString(2));
                gb.setIncome(rs.getString(3));
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
}
