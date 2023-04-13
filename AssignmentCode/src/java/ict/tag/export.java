/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.tag;

import java.io.*;
import java.sql.*;

/**
 *
 * @author User
 */
public class export {

    public void doExport() {

        new File("c:\\csv\\").mkdirs();

        String filename = "c:\\csv\\bookingRecord.csv";
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "4511ass";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        Statement stmt;
        try {
            FileWriter fw = new FileWriter(filename);
            fw.append("Booking ID");
            fw.append(',');
            fw.append("Date");
            fw.append(',');
            fw.append("Type");
            fw.append(',');
            fw.append("Type ID");
            fw.append(',');
            fw.append("Cust ID");
            fw.append(',');
            fw.append("time");
            fw.append(',');
            fw.append("Status");
            fw.append('\n');

            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
            String query = "select * from booking";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                fw.append(rs.getString(1));
                fw.append(',');
                fw.append(rs.getString(2));
                fw.append(',');
                fw.append(rs.getString(3));
                fw.append(',');
                fw.append(rs.getString(4));
                fw.append(',');
                fw.append(rs.getString(5));
                fw.append(',');
                fw.append(rs.getString(6));
                fw.append(',');
                fw.append(rs.getString(7));
                fw.append('\n');
            }

            fw.flush();
            fw.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
