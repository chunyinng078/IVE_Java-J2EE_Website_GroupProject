<%-- 
    Document   : addNewTrainer
    Created on : Apr 12, 2022, 10:53:24 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <%
        if (session.getAttribute("userInfo") == null || !(session.getAttribute("role").equals("customer"))) {
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
        <jsp:include page="customer_menu.jsp" />
    <center style="background-color: #FBEEC1; top:  0; height:85vh">
        <%
            String userId = session.getAttribute("id").toString();
            String type = request.getParameter("type");
            String id = request.getParameter("id");
            if ("trainer".equals(type)) {
                out.print("<form method='post' action='customerHandleRecord'>");
                out.print("<input hidden='hidden' type='text' name='action' value='addTrainerRequest'>");
                out.print("<input hidden='hidden' type='text' name='userId' value='"+userId+"'>");
                out.print("Trainer ID<br><input type='text' name='id' value='"+id+"' readonly><br>");
                out.print("Training Date<br><input type='date' name='date'><br>");
                out.print("Training Time<br><input type='number' name='time' min='11' max='22'><br>");
                out.print("<button type='submit'>submit</button>");
                out.print("</form>");
            }else{
                out.print("<form method='post' action='customerHandleRecord'>");
                out.print("<input hidden='hidden' type='text' name='action' value='addCenterRequest'>");
                out.print("<input hidden='hidden' type='text' name='userId' value='"+userId+"'>");
                out.print("Center ID<br><input type='text' name='id' value='"+id+"' readonly><br>");
                out.print("Training Date<br><input type='date' name='date'><br>");
                out.print("Training Time<br><input type='number' name='time' min='11' max='22'><br>");
                out.print("<button type='submit'>submit</button>");
                out.print("</form>");
            }

        %>
    </center>
    <jsp:include page="footer.jsp"/>
</body>
</html>
