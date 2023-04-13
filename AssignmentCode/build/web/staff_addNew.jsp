<%-- 
    Document   : addNewTrainer
    Created on : Apr 12, 2022, 10:53:24 AM
    Author     : user
--%>

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
            div,input,select,button,form{
                font-size:30px
            }
            table{
                font-size:20px
            }

        </style>
        <title>Add New Record</title>
    </head>
    <body style="min-height: 100%">
        <jsp:include page="heading.jsp" />
        <jsp:include page="staff_menu.jsp" />
    <center style="background-color: #FBEEC1; top:  0; height:85vh">
        <%
            String type = request.getParameter("type");
            if ("trainer".equals(type)) {
                out.print("<form method='post' action='handleRecord'>");
                out.print("<input hidden='hidden' type='text' name='action' value='addTrainer'>");
                out.print("Trainer name<br><input type='text' name='name'><br>");
                out.print("Trainer description:<br>");
                out.print("<textarea rows='5' cols='50' name='description'></textarea><br>");
                out.print("Hourly Rate <br><input type='number' name='hrRate' min='100' placeholder='please insert number'><br> ");
                out.print("sportType <br><input type='checkbox' name='sportType' value='cardio'>cardio");
                out.print("<input type='checkbox' name='sportType' value='weigh training'>weight training");
                out.print("<input type='checkbox' name='sportType' value='boxing'>boxing<br>");
//                out.print("Image:<br><input type='file' id='img' name='img' accept='image/*'><br>");
                out.print("<button type='submit'>submit</button>");
                out.print("</form>");
            } else {
                out.print("<form method='post' action='handleRecord'>");
                out.print("<input hidden='hidden' type='text' name='action' value='addCenter'>");
                out.print("Gym Center name<br><input type='text' name='name'><br>");
                out.print("Center description:<br>");
                out.print("<textarea rows='5' cols='50' name='description'></textarea><br>");
                out.print("Hourly Rate <br><input type='number' name='hrRate' min='100' placeholder='please insert number'><br>");
//                out.print("Image:<br><input type='file' id='img' name='img' accept='image/*'><br>");
                out.print("<button type='submit'>submit</button>");
                out.print("</form>");
            }

        %>
    </center>
    <jsp:include page="footer.jsp"/>
</body>
</html>
