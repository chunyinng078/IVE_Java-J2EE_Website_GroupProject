/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.UserBean;
import ict.db.UserDB;

/**
 *
 * @author User
 */
public class TestUser {

    public static void main(String[] arg) {
        String url = "jdbc:mysql://localhost:3306/4511ass";
        String username = "root";
        String password = "";
        UserDB userDB = new UserDB(url, username, password);

        userDB.createUserTable();
        userDB.addRecord(null, "peter123", "123", "Peter","customer",87654321,"true");
        UserBean result = userDB.queryUserByID("1");
        System.out.println(result);
    }

}
