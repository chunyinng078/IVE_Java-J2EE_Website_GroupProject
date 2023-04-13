<%-- 
    Document   : personalTrainerList
    Created on : Apr 12, 2022, 10:40:53 AM
    Author     : user
--%>

<%@page import="ict.bean.bookingBean"%>
<%@page import="ict.bean.GymBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.TrainerBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    if (session.getAttribute("userInfo") == null || !(session.getAttribute("role").equals("staff") || (session.getAttribute("role").equals("trainer")))) {
        String redirectURL = "pleaseLogin.jsp";
        response.sendRedirect(redirectURL);
    }
%>
<html>
    <head>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Play&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            body{
                font-family: 'Play', sans-serif;
                min-height: 100%;
            }
            h1{
                margin: 0;
                font-size: 50px;
            }
            div,input,select,button{
                font-size:30px
            }
            table{
                font-size:20px
            }

        </style>
        <style>
            .switch {
                position: relative;
                display: inline-block;
                width: 60px;
                height: 34px;
            }

            .switch input {
                opacity: 0;
                width: 0;
                height: 0;
            }

            .slider {
                position: absolute;
                cursor: pointer;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background-color: #ccc;
                -webkit-transition: .4s;
                transition: .4s;
            }

            .slider:before {
                position: absolute;
                content: "";
                height: 26px;
                width: 26px;
                left: 4px;
                bottom: 4px;
                background-color: white;
                -webkit-transition: .4s;
                transition: .4s;
            }

            input:checked + .slider {
                background-color: #2196F3;
            }

            input:focus + .slider {
                box-shadow: 0 0 1px #2196F3;
            }

            input:checked + .slider:before {
                -webkit-transform: translateX(26px);
                -ms-transform: translateX(26px);
                transform: translateX(26px);
            }

            /* Rounded sliders */
            .slider.round {
                border-radius: 34px;
            }

            .slider.round:before {
                border-radius: 50%;
            }
        </style>
        <title>Trainer List</title>
    </head>
    <body>
        <jsp:include page="heading.jsp" />
        <jsp:include page="staff_menu.jsp"/>
    <center style="background-color: #FBEEC1; top:  0; min-height:85vh" >
        <h1>Show List of Booking Request 📖 </h1>
        <form method="get" action="handleRecord">
            <div style="font-size:30px">
                <%
                    String BookingType = request.getParameter("BookingType");
                    String role = session.getAttribute("role").toString();
                    String userId = session.getAttribute("id").toString();
                    if (role.equals("staff")) {
                        out.print("<label for='gymCenter'><h2>Search👤:</h2></label>");
                        out.print("<input hidden='hidden' type='text' name='action' value='searchBooking'>");
                        out.print("<input hidden='hidden' type='text' name='role' value='" + role + "'>");
                        out.print("Booking Type<select name='BookingType' id='BookingType' style='font-size:30px'>");
                        if (BookingType.equals("trainer")) {
                            out.print("<option value='trainer' selected>Trainer</option>");
                            out.print("<option value='center' >Gym Center</option>");
                        } else {
                            out.print("<option value='trainer' >Trainer</option>");
                            out.print("<option value='center' selected>Gym Center</option>");
                        }
                        out.print(" </select>");
                        out.print("<input type='submit' value='Search🔎' style='font-size:30px'>");
                    }

                %>
            </div>
        </form>
        <br><br><br><br><br><br><br><br><br><br><br>
        <%            ArrayList<bookingBean> booking = (ArrayList<bookingBean>) request.getAttribute("booking");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>Booking ID</th><th>Booking Date</th><th>Time</th><th>Customer ID</th><th>Confirm</th><th>Decline</th>");
            out.println("</tr>");
            // loop through the customer array to display each customer record
            for (int i = 0; i < booking.size(); i++) {
                bookingBean b = booking.get(i);
                out.println("<tr>");
                out.println("<td>" + b.getId() + "</td>");
                out.println("<td>" + b.getDate() + "</td>");
                out.println("<td>" + b.getTime() + "</td>");
                out.println("<td>" + b.getCustid() + "</td>");
                out.println("<td><a href='handleRecord?action=confirmBooking&id=" + b.getId() + "&role=" + role + "&userId="+userId+"'><button>confirm</button></a></td>");
                out.println("<td><a href='handleRecord?action=declineBooking&id=" + b.getId() + "&role=" + role + "&userId="+userId+"'><button>decline</button></a></td>");
                out.println("</tr>");

            }
            out.println("</table>");
        %>

        <!--<br><a href="staff_addNew.jsp?type=trainer"><button>Add personal trainer</button></a> <a href="staff_addNew.jsp?type=center"><button>Add Gym Center</button></a><br><br>
                <a href="staff_bookingRequest.jsp"><button>Confirm/decline booking request</button></a><br>-->


    </center>
    <jsp:include page="footer.jsp"/>
</body>
</html>
