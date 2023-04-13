/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.bean.UserBean;
import ict.bean.roleBean;
import ict.db.UserDB;
import ict.db.roleDB;
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
@WebServlet(name = "HandleRole", urlPatterns = {"/handleRole"})
public class HandleRole extends HttpServlet {

    private roleDB db;

    @Override
    public void init() {
        //1.  obtain the context-param, dbUser, dbPassword and dbUrl which defined in web.xml
        //2.  create a new db object  with the parameter
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        db = new roleDB(dbUrl, dbUser, dbPassword);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if ("getRole".equalsIgnoreCase(action)) {

            ArrayList<roleBean> roles = db.queryRole();
            request.setAttribute("roles", roles);

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/role-Management.jsp");
            rd.forward(request, response);

        } else if ("deleteRole".equalsIgnoreCase(action)) {
            // get parameter, id, from the request
            String role = request.getParameter("role");
            if (role != null) {
                // call delete record method in the database
                boolean roleUsing = db.roleUsing(role);
                PrintWriter out = response.getWriter();
                out.println(roleUsing);
                out.println(role);

                if (roleUsing) {
                    response.sendRedirect("roleInUse.jsp");

                } else {
                    String delResult = db.delRole(role) == true ? "success" : "failed";
                    //redirect the result to list action 
                    response.sendRedirect("handleRole?action=getRole&delResult=" + delResult);
                }

            }
        } else if ("confirm".equalsIgnoreCase(action)) {

            response.setContentType("text/html");

            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String type = request.getParameter("type");
            String enable = request.getParameter("enable");
            String actionType = request.getParameter("actionType");
            String beforeName = request.getParameter("beforeName");

            request.setAttribute("id", id);
            request.setAttribute("name", name);
            request.setAttribute("description", description);
            request.setAttribute("type", type);
            request.setAttribute("enable", enable);
            request.setAttribute("actionType", actionType);
            request.setAttribute("beforeName", beforeName);

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/confirmAddRole.jsp");
            rd.forward(request, response);

        } else if ("add".equalsIgnoreCase(action)) {

            response.setContentType("text/html");

            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String type = request.getParameter("type");
            String enable = request.getParameter("enable");

            ArrayList nameList = db.queryRoleName();
            request.setAttribute("roleList", nameList);

            ArrayList result = db.queryRoleName();
            boolean roleRepeat = false;
            for (int i = 0; i < result.size(); i++) {
                String tmp = result.get(i) + "";
                if (tmp.equalsIgnoreCase(name)) {
                    roleRepeat = true;
                }
            }
            PrintWriter out = response.getWriter();
            out.println(roleRepeat);
            out.println(result);
            out.println(name);
            out.println(description);
            out.println(type);
            out.println(enable);

            if (roleRepeat) {
                response.sendRedirect("roleFound.jsp");
            } else {
                boolean addRoleSuccess = db.addRole(null, name, description, type, enable);
                String update = "";
                if (addRoleSuccess) {
                    update = "true";
                } else {
                    update = "false";
                }
                response.sendRedirect("handleRole?action=getRole&addResult=" + update);
            }
        } else if ("confirmDelete".equalsIgnoreCase(action)) {

            response.setContentType("text/html");

            String role = request.getParameter("role");
            request.setAttribute("role", role);
            String type = request.getParameter("roleType");
            request.setAttribute("type", type);

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/confirmDeleteRole.jsp");
            rd.forward(request, response);

        } else if ("getEditRole".equalsIgnoreCase(action)) {
            // take user's information
            // obtain the parameter id;
            String name = request.getParameter("role");
            if (name != null) {

                // call  query db to get retrieve for a customer with the given id
                roleBean role = db.queryRoleByName(name);
                ArrayList roleList = db.queryRoleType2();
                // set the customer as attribute in request scope
                request.setAttribute("roleList", roleList);
                request.setAttribute("r", role);
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/editRole.jsp");
                rd.forward(request, response);
            }
        } else if ("getNewRole".equalsIgnoreCase(action)) {
            // take user's information
            // obtain the parameter id;
            String name = request.getParameter("name");
            // call  query db to get retrieve for a customer with the given id
            roleBean role = db.queryRoleByName(name);
            ArrayList roleType = db.queryRoleType2();
            // set the customer as attribute in request scope
            request.setAttribute("r", role);
            request.setAttribute("roleList", roleType);

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/editRole.jsp");
            rd.forward(request, response);

        } else if ("edit".equalsIgnoreCase(action)) {

            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String beforeName = request.getParameter("beforeName");
            String description = request.getParameter("description");
            String type = request.getParameter("type");
            String enable = request.getParameter("enable");

            System.out.println(id + " " + name + " " + beforeName + " " + description + " " + type + " " + enable);

            PrintWriter out = response.getWriter();
            out.println(name + " and " + beforeName);

            boolean nameChangable = db.roleRoleNameCanChange(name, beforeName);

            if (nameChangable) {
                roleBean rb = new roleBean(id, name, description, type, enable);
                boolean result = db.editRecord(rb);
                boolean result2 = db.updateUserRoleName(name, beforeName);

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/handleRole?action=getRole&edit=" + result);
                rd.forward(request, response);
            } else {
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/roleFound.jsp");
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
