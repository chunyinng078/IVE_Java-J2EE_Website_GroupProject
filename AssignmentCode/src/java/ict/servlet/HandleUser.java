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
@WebServlet(name = "HandleUser", urlPatterns = {"/handleUser"})
public class HandleUser extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");

        if ("list".equalsIgnoreCase(action)) {
            // call the query db to get retrieve for all customer 
            ArrayList<UserBean> users = db.queryUser();
            // set the result into the attribute	 
            request.setAttribute("users", users);

            // redirect the result to the listCustomers.jsp
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/management.jsp");
            rd.forward(request, response);

        } else if ("delete".equalsIgnoreCase(action)) {
            // get parameter, id, from the request
            String id = request.getParameter("id");
            PrintWriter out = response.getWriter();

            if (id != null) {
                // call delete record method in the database
                String role = db.searchUserRole(id);
                String roleType = db.searchUserRoleType(role);
                boolean isOK = true;
                if (roleType.equalsIgnoreCase("trainer")) {

                } else if (roleType.equalsIgnoreCase("customer")) {
                    boolean haveBooking = db.searchUserBooking(id);
                    if (haveBooking) {
                        isOK = false;
                        response.sendRedirect("bookingFound.jsp");
                    }
                    out.println(roleType + " , " + haveBooking);

                }
                if (isOK) {
                    String delResult = db.delRecord(id) == true ? "success" : "failed";

                    request.setAttribute("delResult", delResult);
                    // redirect the result to list action 
                    response.sendRedirect("handleUser?action=list&delResult=true");
                }

            }
        } else if ("getEditUser".equalsIgnoreCase(action)) {
            // take user's information
            // obtain the parameter id;
            String id = request.getParameter("id");
            if (id != null) {
                // call  query db to get retrieve for a customer with the given id
                UserBean user = db.queryUserByID(id);
                // set the customer as attribute in request scope
                ArrayList roleList = db.queryUserRole();
                request.setAttribute("u", user);
                request.setAttribute("roleList", roleList);

                // forward the result to the editCustomer.jsp       
                //response.sendRedirect("editCustomer?action=edit");
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/editUser.jsp");
                rd.forward(request, response);
            }
        } else if ("getNewUser".equalsIgnoreCase(action)) {
            // give role list for management create account
            ArrayList roleList = db.queryUserRole();
            request.setAttribute("roleList", roleList);

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/editUser.jsp");
            rd.forward(request, response);

        } else if ("search".equalsIgnoreCase(action)) {
            //search user
            String name = request.getParameter("name");
            if (name != null) {
                ArrayList<UserBean> users;
                // call  queryByName from db 
                users = db.queryUserByName(name);
                // to retrieve for customers with the given name
                // set the result into the attribute in request
                request.setAttribute("users", users);
                // forward the result to the listCustoemrs.jsp
                RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/management.jsp");
                rd.forward(request, response);
            }
        } else {
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
