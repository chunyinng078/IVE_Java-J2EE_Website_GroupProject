<%-- 
    Document   : template
    Created on : Apr 6, 2022, 10:11:56 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.UserBean"%>

<%
    if (session.getAttribute("userInfo") == null || !(session.getAttribute("role").equals("management"))) {
        String redirectURL = "pleaseLogin.jsp";
        response.sendRedirect(redirectURL);
    }


%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Play&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            body{
                font-family: 'Play', sans-serif;
            }
        </style>
        <title>Account Management </title>
    </head>
    <body style="min-height: 100%">
        <jsp:include page="heading.jsp" />
        <jsp:include page="sm-menu.jsp" />

    <center style="background-color: #FBEEC1; top:  0; min-height:85vh"   >


        <h2 style="margin: 0; font-size: 30px;">Account Management ⚙️</h2>
        <br>

        <form style="font-size:20px" action="handleUser">

            <h3>Create user 📋 :</h3>
            <input type="submit" value="Create 📝" style="font-size:20px">
            <input type="hidden" name="action" value="getNewUser">
        </form>
        <br>
        <form style="font-size:20px;" action="handleUser" >
            <h3>Search for User(s) ✏️ :</h3>
            <input type="hidden" name="action" value="search"/>

            <input type="text" name="name" placeholder="Enter name of user here" style="font-size:20px"> <br><br>

            <input type="submit" value="Search/Refresh🔎" style="font-size:15px">
        </form>
        <h1>Users</h1>
        <%                ArrayList<UserBean> users = (ArrayList<UserBean>) request.getAttribute("users");

            String editResult = request.getParameter("editResult");

            if (editResult != null) {
                if (editResult.equalsIgnoreCase("true")) {
                    out.println("<h2>Update successfully</h2>");
                } else {
                    out.println("<h2>Update failed</h2>");
                }

            }

            String addRecord = request.getParameter("addRecord");

            if (addRecord != null) {
                if (addRecord.equalsIgnoreCase("true")) {
                    out.println("<h2>Record added</h2>");
                } else {
                    out.println("<h2>Record fail to add</h2>");
                }

            }
            String delResult = request.getParameter("delResult");

            if (delResult != null) {
                if (delResult.equalsIgnoreCase("true")) {
                    out.println("<h2>Record delete successfully</h2>");
                } else {
                    out.println("<h2>Delete failed</h2>");
                }

            }

            out.println("<table border='1'               >");
            out.println("<tr>");
            out.println("<th>UserId</th>  <th> Username</th><th> Password</th><th> Name</th ><th> Role</th ><th> Tel</th ><th> Enable</th >");
            out.println("</tr>");
            // loop through the customer array to display each customer record
            for (int i = 0; i < users.size(); i++) {
                UserBean c = users.get(i);
                out.println("<tr>");

                out.println("<td>" + c.getUserid() + "</td>");
                out.println("<td>" + c.getUsername() + "</td>");
                out.println("<td>" + c.getPassword() + "</td>");
                out.println("<td>" + c.getName() + "</td>");
                out.println("<td>" + c.getRole() + "</td>");
                out.println("<td>" + c.getTel() + "</td>");
                out.println("<td>" + c.getEnable() + "</td>");
                //out.println("<td><a href=\"handleUser?action=delete&id=" + c.getUserid() + "\">delete</a></td>");
                out.println("<td><a href=\"handleUserEdit?action=confirmDelete&id=" + c.getUserid() + "&username=" + c.getUsername() + "&role=" + c.getRole() + "\">delete</a></td>");

                out.println("<td><a href=\"handleUser?action=getEditUser&id=" + c.getUserid() + "\">edit</a></td>");
                out.println("</tr>");

            }
            out.println("</table>");
        %>
    </center>
    <jsp:include page="footer.jsp"         />
</body>
</html>
