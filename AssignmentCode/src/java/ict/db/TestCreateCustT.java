/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

/**
 *
 * @author user
 */
import ict.db.CustomerDB;
public class TestCreateCustT {

    public static void main(String[] arg) {
        String url = "jdbc:mysql://localhost:3306/4511ass";
        String username = "root";
        String password = "";
        CustomerDB custDb = new CustomerDB(url, username, password);
        custDb.createCustTable();
    }
}
