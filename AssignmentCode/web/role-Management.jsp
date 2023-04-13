<%-- 
    Document   : template
    Created on : Apr 6, 2022, 10:11:56 PM
    Author     : User
--%>

<%@page import="ict.bean.roleBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Role Management</title>
    </head>
    <%        String addResult = request.getParameter("addResult");
        String delResult = request.getParameter("delResult");

        String edit = request.getParameter("edit");

        ArrayList<roleBean> roles = (ArrayList<roleBean>) request.getAttribute("roles");

    %>
    <body style="min-height: 100%">
        <jsp:include page="heading.jsp" />
        <jsp:include page="sm-menu.jsp" />

    <center style="background-color: #FBEEC1; top:  0; min-height:85vh"   >
        <h1 style="margin: 0; font-size: 50px;">Role Management</h1>


        <form style="font-size:20px" action="handleRole">

            <h3>Create User Role 🖊️ :</h3>
            <input type="hidden" name="action" value="getNewRole">
            <input type="submit" value="Add User Role 📝" style="font-size:20px">

        </form>

        <form style="font-size:20px" action="">

            <h2>User Role 🧍‍♂️ :</h2>

            <%                if (addResult != null) {
                    if (addResult.equalsIgnoreCase("true")) {
                        out.print("<h3>Role added</h3>");
                    } else {
                        out.print("<h3>Role add failed</h3>");
                    }

                }
                if (edit != null) {
                    if (edit.equalsIgnoreCase("true")) {
                        out.print("<h3>Role edited</h3>");
                    } else {
                        out.print("<h3>Role edit failed</h3>");
                    }

                }

                if (delResult != null) {
                    if (delResult.equalsIgnoreCase("success")) {
                        out.print("<h3>Role deleted</h3>");
                    } else {
                        out.print("<h3>Role delete failed</h3>");
                    }

                }
            %>



            <%                       // loop through the customer array to display each customer record
                out.println("<table border='1'               >");
                out.println("<tr>");
                out.println("<th>id</th>  <th> name</th><th> description</th><th> type</th ><th> enable</th >");
                out.println("</tr>");
                // loop through the customer array to display each customer record
                for (int i = 0; i < roles.size(); i++) {
                    roleBean r = roles.get(i);
                    out.println("<tr>");

                    out.println("<td>" + r.getId() + "</td>");
                    out.println("<td>" + r.getName() + "</td>");
                    out.println("<td>" + r.getDescription() + "</td>");
                    out.println("<td>" + r.getType() + "</td>");
                    out.println("<td>" + r.getEnable() + "</td>");

                    //out.println("<td><a href=\"handleUser?action=delete&id=" + c.getUserid() + "\">delete</a></td>");
                    out.println("<td><a href=\"handleRole?action=confirmDelete&role=" + r.getName() + "&roleType=" + r.getType() + "\">delete</a></td>");

                    out.println("<td><a href=\"handleRole?action=getEditRole&role=" + r.getName() +"&rtype="+r.getType()+ "\">edit</a></td>");
                    out.println("</tr>");

                }
                out.println("</table>");
            %>

        </form>


    </center>
    <jsp:include page="footer.jsp"         />
</body>
</html>
