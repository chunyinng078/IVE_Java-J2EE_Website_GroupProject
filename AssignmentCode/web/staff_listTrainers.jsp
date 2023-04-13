<%-- 
    Document   : personalTrainerList
    Created on : Apr 12, 2022, 10:40:53 AM
    Author     : user
--%>

<%@page import="ict.bean.GymBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.TrainerBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    if (session.getAttribute("userInfo") == null || !(session.getAttribute("role").equals("staff")||(session.getAttribute("role").equals("trainer")))) {
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
        <jsp:include page="staff_menu.jsp" />
    <center style="background-color: #FBEEC1; top:  0; min-height:85vh" >
        <h1>Show List of Personal Trainer /Gym Center 📖 </h1>
        <form method="get" action="handleRecord">
            <div style="font-size:30px">
                <label for="gymCenter"><h2>Search👤:</h2></label>
                <input hidden="hidden" type="text" name="action" value="search">
                Enter name: <input type="text" placeholder="Enter here" style="font-size:30px" name="name" />
                <br><br>
                Information Type
                <select name="InformationType" id="InformationType" style="font-size:30px">
                    <%
                        String InformationType = request.getParameter("InformationType");
                        if (InformationType.equals("trainer")) {
                            out.print("<option value='trainer' selected>Trainer</option>");
                            out.print("<option value='gym' >Gym Center</option>");
                        } else {
                            out.print("<option value='trainer' >Trainer</option>");
                            out.print("<option value='gym' selected>Gym Center</option>");
                        }
                    %>
                </select>
                <input type="submit" value="Search🔎" style="font-size:30px">
            </div>
        </form>
        <br><br><br><br><br><br><br><br><br><br><br>
        <%
            String action = request.getParameter("action");
            String role = session.getAttribute("role").toString();
            if ("listTrainers".equals(action)) {
                ArrayList<TrainerBean> trainers = (ArrayList<TrainerBean>) request.getAttribute("trainers");
                out.println("<table border='1'>");
                out.println("<tr>");
                if (role.equals("trainer")) {
                    out.println("<th>Trainer ID</th><th>Trainer Name</th><th>States</th><th>Hour Rate</th><th>Description</th><th>SportType</th><th>Image</th>");
                    out.println("</tr>");
                    // loop through the customer array to display each customer record
                    for (int i = 0; i < trainers.size(); i++) {
                        TrainerBean t = trainers.get(i);
                        out.println("<tr>");
                        out.println("<td>" + t.getId() + "</td>");
                        out.println("<td>" + t.getName() + "</td>");
                        out.println("<td>" + t.getStates() + "</td>");
                        out.println("<td>" + t.getHrRate() + "</td>");
                        out.println("<td>" + t.getDescription() + "</td>");
                        out.println("<td>" + t.getSportType() + "</td>");
                        out.println("<td><img src='img/trainer_" + t.getId() + ".jpg' width='200px'/></td>");
                        out.println("</tr>");

                    }
                    out.println("</table>");
                } else {
                    out.println("<th>Trainer ID</th><th>Trainer Name</th><th>States</th><th>Hour Rate</th><th>Description</th><th>SportType</th><th>Image</th><th>edit</th><th>delete</th>");
                    out.println("</tr>");
                    // loop through the customer array to display each customer record
                    for (int i = 0; i < trainers.size(); i++) {
                        TrainerBean t = trainers.get(i);
                        out.println("<tr>");
                        out.println("<td>" + t.getId() + "</td>");
                        out.println("<td>" + t.getName() + "</td>");
                        out.println("<td>" + t.getStates() + "</td>");
                        out.println("<td>" + t.getHrRate() + "</td>");
                        out.println("<td>" + t.getDescription() + "</td>");
                        out.println("<td>" + t.getSportType() + "</td>");
                        out.println("<td><img src='img/trainer_" + t.getId() + ".jpg' width='200px'/></td>");
                        out.println("<td><a href='handleRecord?action=delete&type=trainer&id=" + t.getId() + "'>delete</a></td>");
                        out.println("<td><a href='handleRecord?action=getEditTrainer&id=" + t.getId() + "'>edit</a></td>");
                        out.println("</tr>");

                    }
                    out.println("</table>");
                }

            } else if ("listGym".equals(action)) {
                ArrayList<GymBean> gym = (ArrayList<GymBean>) request.getAttribute("gym");
                out.println("<table border='1'>");
                out.println("<tr>");
                if (role.equals("trainer")) {
                    out.println("<th>Gym Center ID</th><th>Gym Center Name</th><th>States</th><th>Hour Rate</th><th>Description</th><th>Image</th>>");
                    out.println("</tr>");
                    // loop through the customer array to display each customer record
                    for (int i = 0; i < gym.size(); i++) {
                        GymBean g = gym.get(i);
                        out.println("<tr>");
                        out.println("<td>" + g.getId() + "</td>");
                        out.println("<td>" + g.getCenterName() + "</td>");
                        out.println("<td>" + g.getStates() + "</td>");
                        out.println("<td>" + g.getHrRate() + "</td>");
                        out.println("<td>" + g.getDescription() + "</td>");
                        out.println("<td><img src='img/gym_" + g.getId() + ".jpg' width='200px'/></td>");
                        out.println("</tr>");

                    }
                    out.println("</table>");
                } else {
                    out.println("<th>Gym Center ID</th><th>Gym Center Name</th><th>States</th><th>Hour Rate</th><th>Description</th><th>Image</th><th>delete</th><th>edit</th>");
                    out.println("</tr>");
                    // loop through the customer array to display each customer record
                    for (int i = 0; i < gym.size(); i++) {
                        GymBean g = gym.get(i);
                        out.println("<tr>");
                        out.println("<td>" + g.getId() + "</td>");
                        out.println("<td>" + g.getCenterName() + "</td>");
                        out.println("<td>" + g.getStates() + "</td>");
                        out.println("<td>" + g.getHrRate() + "</td>");
                        out.println("<td>" + g.getDescription() + "</td>");
                        out.println("<td><img src='img/gym_" + g.getId() + ".jpg' width='200px'/></td>");
                        out.println("<td><a href='handleRecord?action=delete&type=center&id=" + g.getId() + "'>delete</a></td>");
                        out.println("<td><a href='handleRecord?action=getEditCenter&id=" + g.getId() + "'>edit</a></td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                }
            }
        %>
<!--        <br><a href="staff_addNew.jsp?type=trainer"><button>Add personal trainer</button></a> <a href="staff_addNew.jsp?type=center"><button>Add Gym Center</button></a><br><br>
        <a href="handleRecord?action=searchBooking&BookingType=trainer"><button>Confirm/decline booking request</button></a><br>-->

    </center>
    <jsp:include page="footer.jsp"/>
</body>
</html>
