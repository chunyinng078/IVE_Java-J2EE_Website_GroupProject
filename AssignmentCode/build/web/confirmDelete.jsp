<%-- 
    Document   : confrimDelete
    Created on : Apr 17, 2022, 8:13:02 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

    String id = (String) request.getAttribute("id");
    String username = (String) request.getAttribute("username");
    String role = (String) request.getAttribute("role");

%>
<html>
    <head>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Play&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmation</title>
        <style>
            body{
                font-family: 'Play', sans-serif;
            }
        </style>
    </head>
    <body style="min-height: 100%">
        <jsp:include page="heading.jsp" />
    <center style="background-color: #FBEEC1; top:  0; height:85vh"   >
        <br><br><h1 style='font-size: 50px;'>Delete Confirm</h1>


        <form  method='get' action='handleUser' style='font-size: 30px;'>
            You sure you want to delete the the following account?<br>

            ID: <%=id%> <br><br>
            Username: <%=username%> <br><br>
            User Role: <%=role%> <br><br>
            <input type='button' value='Back' onclick='history.back()' style='font-size: 30px;'/> 
            <input type='hidden' name='action' value='delete'>
            <input type='hidden' name='id' value='<%=id%>'>
            <input type='submit' value='Confirm' style='font-size: 30px;'>
        </form>
    </center>
    <jsp:include page="footer.jsp"         />

</body>
</html>
