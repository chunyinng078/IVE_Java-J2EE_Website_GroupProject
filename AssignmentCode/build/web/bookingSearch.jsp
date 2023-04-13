<%-- 
    Document   : template
    Created on : Apr 6, 2022, 10:11:56 PM
    Author     : User
--%>

<%@page import="ict.bean.GymBeanWithID"%>
<%@page import="ict.bean.bookingBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.UserBean"%>
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
        <title>Booking Records</title>
    </head>
    <body style="min-height: 100%">
        <jsp:include page="heading.jsp" />               
    <center style="background-color: #FBEEC1; top:  0; height:85vh;font-size:25px"   >


        <h2 style="margin: 0; font-size: 30px;">Booking Records</h2>
        <br>

        <%
            ArrayList<bookingBean> booking = (ArrayList<bookingBean>) request.getAttribute("booking");
            ArrayList<GymBeanWithID> gym = (ArrayList<GymBeanWithID>) request.getAttribute("gym");

            String yBooking = (String) request.getAttribute("yb");
            String mBooking = (String) request.getAttribute("mb");
            Double yr = (Double) request.getAttribute("yr");
            Double mr = (Double) request.getAttribute("mr");
            String type = (String) request.getAttribute("type");
            String id = (String) request.getAttribute("id");
            String title = "";
            String centerName = "";

            if (type != null) {

                if (type.equalsIgnoreCase("center")) {
                    for (int i = 0; i < gym.size(); i++) {
                        if (id.equalsIgnoreCase(gym.get(i).getCenterID())) {
                            centerName = gym.get(i).getCenterName();
                        }
                    }
                    title = "Booking of  Center :" + centerName;

                } else {
                    title = "Booking of Trainer ID: " + id;
                }

            }
        %>

        Booking of this month: <%=mBooking%><br>
        Booking rate of this month: <%=mr%>
        <br>
        <br>
        Booking of this year:  <%=yBooking%><br>
        Booking rate of this year:  <%=yr%>
        <br>



        <%

            out.println("<h1>" + title + "</h1>");

            out.println("<table border='1'               >");
            out.println("<tr>");
            out.println("<th>Booking ID</th>  <th>Date</th><th> Customer ID</th><th> Time</th ><th> Status</th >");
            out.println("</tr>");
            // loop through the customer array to display each customer record
            for (int i = 0; i < booking.size(); i++) {
                bookingBean b = booking.get(i);
                out.println("<tr>");

                out.println("<td>" + b.getId() + "</td>");
                out.println("<td>" + b.getDate() + "</td>");
                out.println("<td>" + b.getCustid() + "</td>");
                out.println("<td>" + b.getTime() + "</td>");
                out.println("<td>" + b.getStatus() + "</td>");
                //out.println("<td><a href=\"handleUser?action=delete&id=" + c.getUserid() + "\">delete</a></td>");
                // out.println("<td><a href=\"handleUserEdit?action=confirmDelete&id=" + c.getUserid() + "&username=" + c.getUsername() + "&role=" + c.getRole() + "\">delete</a></td>");

                // out.println("<td><a href=\"handleUser?action=getEditUser&id=" + c.getUserid() + "\">edit</a></td>");
                out.println("</tr>");

            }
            out.println("</table>");
        %>
        <br>
        <from > <input type="button" onclick='history.back()' value="Back" style="font-size:25px"></from>
    </center>

    <jsp:include page="footer.jsp"         />
</body>
</html>
