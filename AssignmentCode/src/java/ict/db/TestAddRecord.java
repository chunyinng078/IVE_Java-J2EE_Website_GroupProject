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

public class TestAddRecord {

    public static void main(String[] arg) {
        String url = "jdbc:mysql://localhost:3306/4511ass";
        String username = "root";
        String password = "";
        CustomerDB custDb = new CustomerDB(url, username, password);
        custDb.addRecord("1", "Peter", "12345688", 18);
        custDb.addRecord("2", "Nancy", "12345688", 21);
    }
}
