/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.bean.UserBean;
import ict.db.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "HandleUserEdit", urlPatterns = {"/handleUserEdit"})
public class HandleUserEdit extends HttpServlet {

    private UserDB db;

    @Override
    public void init() {
        //1.  obtain the context-param, dbUser, dbPassword and dbUrl which defined in web.xml
        //2.  create a new db object  with the parameter
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        db = new UserDB(dbUrl, dbUser, dbPassword);

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equalsIgnoreCase(action)) {
            String id = request.getParameter("id");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String role = request.getParameter("role");
            int tel = Integer.parseInt(request.getParameter("tel"));
            String enable = request.getParameter("enable");
            PrintWriter out = response.getWriter();
            String creator = request.getParameter("creator");



            UserBean ub = new UserBean(id, username, password, name, role, tel, enable);
            boolean userNameFound = db.checkUserName(ub);
            response.setContentType("text/html");

            if (userNameFound) {
                response.sendRedirect("userFound.jsp");

            } else {
                //boolean addTrainer  = db.addTrainer(name);
                boolean result = db.addRecord(id, username, password, name, role, tel, enable);

                ArrayList<UserBean> users = db.queryUser();
                String addRecord = result == true ? "true" : "false";
                // set the result into the attribute	 
                request.setAttribute("users", users);

                if (creator == null) {
                    creator = "";
                }
                if (creator.equals("customer")) {
                    response.sendRedirect("login.jsp?acCreated=true");

                } else {
                    response.sendRedirect("handleUser?action=list&addRecord=" + addRecord);

                }
            }

        } else if ("edit".equalsIgnoreCase(action)) {
            response.setContentType("text/html");

            PrintWriter out = response.getWriter();

            String id = request.getParameter("id");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String role = request.getParameter("role");
            int tel = Integer.parseInt(request.getParameter("tel"));
            String enable = request.getParameter("enable");
            String creator = request.getParameter("creator");

            UserBean ub = new UserBean(id, username, password, name, role, tel, enable);

            boolean userNameFound = db.checkUserName(ub);

            if (userNameFound) {
                out.print("Username already exist, please choose other username.");
                out.println("<form  method='post'><input type='button' value='Back' onclick='history.back()'/></form>");
            } else {
                boolean result = db.editRecord(ub);

                if (result) {
                    request.setAttribute("result", "success");

                } else {
                    request.setAttribute("result", "fail");
                }

                response.sendRedirect("handleUser?action=list&editResult=" + result);

            }

        } else if ("confirm".equalsIgnoreCase(action)) {
            response.setContentType("text/html");
            // obtain the parameter name;

            String actionType = request.getParameter("actionType");
            String id = request.getParameter("id");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String role = request.getParameter("role");
            int tel = Integer.parseInt(request.getParameter("tel"));
            String enable = request.getParameter("enable");
            String confirmForm = "";
            //edit
            if (id != null) {
                PrintWriter out = response.getWriter();

                if (actionType.equalsIgnoreCase("edit")) {
                    confirmForm += "<h2>The record will be updated as follow:<h2><br>";
                    confirmForm += "        <form  method='post' action='handleUserEdit'>\n";
                    confirmForm += "<div style='font-size: 30px;'>UserID: " + id + "<br>";
                    confirmForm += "<br>Username: " + username + "<br>";
                    confirmForm += "<br>Password: " + password + "<br>";
                    confirmForm += "<br>Name: " + name + "<br>";
                    confirmForm += "<br>Role: " + role + "<br>";
                    confirmForm += "<br>Tel: " + tel + "<br>";
                    confirmForm += "<br>Enable: " + enable + "<br><br></div>";
                    confirmForm += "            <input type='hidden' name='action'  value='" + actionType + "' />\n"
                            + "			<input type='hidden' name='id'  value='" + id + "' />\n"
                            + "			<input type='hidden' name='username'  value='" + username + "' />\n"
                            + "			<input type='hidden' name='password'  value='" + password + "' />\n"
                            + "			<input type='hidden' name='name'  value='" + name + "' />\n"
                            + "			<input type='hidden' name='role'  value='" + role + "' />\n"
                            + "			<input type='hidden' name='tel'  value='" + tel + "' />\n"
                            + "			<input type='hidden' name='enable'  value='" + enable + "' />\n"
                            + "         <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br> <input type='submit' style='font-size: 30px;' value='Confirm'/>\n"
                            + "        </form>";

                    confirmForm += "<br><form  method='get'><input style='font-size: 30px;' type='button' value='Back' onclick='history.back()'/></form>";

                }
            } else { //add
                //customer create account
                String creator = request.getParameter("creator");
                if (creator == null) {
                    creator = "";
                }
                if (creator.equalsIgnoreCase("customer")) {
                    enable = "true";
                    role = "customer";

                    PrintWriter out = response.getWriter();
                    confirmForm += "<h2>The account will be created as follow:<h2><br>";

                    confirmForm += "        <form  method='post' action='handleUserEdit'>\n";
                    confirmForm += "<div style='font-size: 30px;'><br>Username: " + username + "<br>";
                    confirmForm += "<br>Password: " + password + "<br>";
                    confirmForm += "<br>Name: " + name + "<br>";
                    confirmForm += "<br>Role: " + role + "<br>";
                    confirmForm += "<br>Tel: " + tel + "<br><br></div>";
                    confirmForm += "            <input type='hidden' name='action'  value='" + actionType + "' />\n"
                            + "			<input type='hidden' name='id'  value='" + id + "' />\n"
                            + "			<input type='hidden' name='username'  value='" + username + "' />\n"
                            + "			<input type='hidden' name='password'  value='" + password + "' />\n"
                            + "			<input type='hidden' name='name'  value='" + name + "' />\n"
                            + "			<input type='hidden' name='role'  value='" + role + "' />\n"
                            + "			<input type='hidden' name='tel'  value='" + tel + "' />\n"
                            + "			<input type='hidden' name='enable'  value='" + enable + "' />\n"
                            + "			<input type='hidden' name='creator'  value='" + creator + "' />\n"
                            + "         <br><br><br><br><br><br><br><br><br><br><br><br> <input type='submit' style='font-size: 30px;' value='Confirm'/>\n"
                            + "        </form>";

                    confirmForm += "<br><form  method='get'><input type='button' style='font-size: 30px;' value='Back' onclick='history.back()'/></form>";
                } else {  //management create account

                    PrintWriter out = response.getWriter();
                    confirmForm += "<h2>The account will be created as follow:<h2><br>";

                    confirmForm += "        <form  method='post' action='handleUserEdit'>\n";
                    confirmForm += "<div style='font-size: 30px;'><br>Username: " + username + "<br>";
                    confirmForm += "<br>Password: " + password + "<br>";
                    confirmForm += "<br>Name: " + name + "<br>";
                    confirmForm += "<br>Role: " + role + "<br>";
                    confirmForm += "<br>Tel: " + tel + "<br>";
                    confirmForm += "<br>Enable: " + enable + "<br><br></div>";
                    confirmForm += "            <input type='hidden' name='action'  value='" + actionType + "' />\n"
                            + "			<input type='hidden' name='id'  value='" + id + "' />\n"
                            + "			<input type='hidden' name='username'  value='" + username + "' />\n"
                            + "			<input type='hidden' name='password'  value='" + password + "' />\n"
                            + "			<input type='hidden' name='name'  value='" + name + "' />\n"
                            + "			<input type='hidden' name='role'  value='" + role + "' />\n"
                            + "			<input type='hidden' name='tel'  value='" + tel + "' />\n"
                            + "			<input type='hidden' name='enable'  value='" + enable + "' />\n"
                            + "         <br><br><br><br><br><br><br><br><br><br><br><br><br><input type='submit' style='font-size: 30px;' value='Confirm'/>\n"
                            + "        </form>";

                    confirmForm += ("<br><form  method='get'><input type='button' style='font-size: 30px;' value='Back' onclick='history.back()'/></form>");
                }

            }
            request.setAttribute("confirmForm", confirmForm);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/confirmation.jsp");
            rd.forward(request, response);
        } else if ("confirmDelete".equalsIgnoreCase(action)) {

            response.setContentType("text/html");

            String id = request.getParameter("id");
            String username = request.getParameter("username");
            String role = request.getParameter("role");

            request.setAttribute("id", id);
            request.setAttribute("username", username);
            request.setAttribute("role", role);

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/confirmDelete.jsp");
            rd.forward(request, response);

            //PrintWriter out = response.getWriter();
            //out.println("You sure you want to delete the the following account?<br>");
            //out.println("\nID: " + id + "<br>");
            //out.println("\nUsername: " + username + "<br>");
            //out.println("\nRole: " + role + "<br>");
            // out.println("<form  method='get' action='handleUser'> <input type='button' value='Back' onclick='history.back()'/> <input type='hidden' name='action' value='delete'><input type='hidden' name='id' value='" + id + "'><input type='submit' value='Confirm'></form>");
            //out.println("<a href='handleUser?action=delete&id="+id+"'>confirm</a>");
        }  else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
        }
    }
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
