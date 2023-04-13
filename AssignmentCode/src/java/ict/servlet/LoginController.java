/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.bean.UserBean;
import ict.db.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet(name = "LoginController", urlPatterns = {"/main"})
public class LoginController extends HttpServlet {

    private UserDB db;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        System.out.println("debug: " + dbUser + "; " + dbPassword + "; " + dbUrl + ";");
        db = new UserDB(dbUrl, dbUser, dbPassword);

    }

    private void doAuthenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isValid = db.isValidUser(username, password);

        String targetURL = "";
        if (isValid) {
            boolean isUserEnable;
            boolean isRoleEnable;
            isUserEnable = db.isUserEnable(username);
            String role = db.getUserRole(username);
            isRoleEnable = db.isRoleEnable(role);

            if (isUserEnable && isRoleEnable) {
                String type = db.searchUserRoleType(role);
                HttpSession session = request.getSession(true);
                String id = db.queryUserIDByName(username);
                UserBean bean = new UserBean();
                bean.setUsername(username);
                bean.setUserid(id);
                session.setAttribute("userInfo", bean);
                session.setAttribute("id", id);
                System.out.println("id = " + id);
                System.out.println("username = " + username);

                if (type.equalsIgnoreCase("management")) {
                    targetURL = "/handleBookingSearch?action=search";
                    session.setAttribute("role", "management");

                } else if (type.equalsIgnoreCase("staff")) {
                    targetURL = "/handleRecord?action=search&name=&InformationType=trainer";
                    session.setAttribute("role", "staff");

                } else if (type.equalsIgnoreCase("trainer")) {
                    targetURL = "/handleRecord?action=search&name=&InformationType=trainer";
                    session.setAttribute("role", "trainer");

                } else if (type.equalsIgnoreCase("customer")) {
                    targetURL = "/customerHandleRecord?action=bookingReminder&id="+id;
                    session.setAttribute("role", "customer");

                }
            } else {

                targetURL = "/accountNotEnable.jsp";

            }

        } else {
            targetURL = "/loginError.jsp";
        }
        String contextPath = request.getContextPath();
        response.sendRedirect(response.encodeRedirectURL(contextPath + targetURL));

        //RequestDispatcher rd;
        //rd = getServletContext().getRequestDispatcher("/" + targetURL);
        //rd.forward(request, response);
    }

    private boolean isAuthenticated(HttpServletRequest request) {
        boolean result = false;
        HttpSession session = request.getSession();
        if (session.getAttribute("userInfo") != null) {
            result = true;
        }
        return result;
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String targetURL = "login.jsp";
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);

    }

    private void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        session.invalidate();
        session = null;
        //if (session != null) {
        //    session.removeAttribute("userInfo");
        //    session.removeAttribute("id");

        //   session.invalidate();
        //}
        doLogin(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (!isAuthenticated(request)
                && !("authenticate".equals(action))) {
            doLogin(request, response);
            return;
        }
        if ("authenticate".equals(action)) {
            try {
                doAuthenticate(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("doLogout".equals(action)) {
            doLogout(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
