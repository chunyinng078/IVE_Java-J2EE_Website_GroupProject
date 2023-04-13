<%-- 
    Document   : confirmDeleteRole
    Created on : Apr 18, 2022, 4:39:04 AM
    Author     : User
--%>
<%

    String role = (String) request.getAttribute("role");
    String type = (String) request.getAttribute("type");

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            body{
                font-family: 'Play', sans-serif;
            }
        </style>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Play&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmation</title>
    </head>
    <body style="min-height: 100%">
        <jsp:include page="heading.jsp" />
    <center style="background-color: #FBEEC1; top:  0; height:85vh"   >
        <br><br><br><br>
        <h1 style="margin: 0; font-size: 50px;">Delete Role</h1><br><br>
        <form  method='get' action='handleRole' style="font-size: 30px;">
            You sure you want to delete the the following role?<br>
            <br>
            Role: <%=role%> <br>
            Role type: <%=type%> <br>
            <br>
            <input type='hidden' name='action' value='deleteRole'>
            <input type='hidden' name='role' value='<%=role%>'>
            <input type='hidden' name='type' value='<%=type%>'>
            <input type='button' value='Back' onclick='history.back()' style="font-size: 30px;"/> 
            <input type='submit' value='Confirm'  style="font-size: 30px;">

        </form>
    </center >
    <jsp:include page="footer.jsp"         />
</body>
</html>
