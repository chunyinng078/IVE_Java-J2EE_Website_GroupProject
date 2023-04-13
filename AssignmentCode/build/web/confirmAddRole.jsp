<%-- 
    Document   : addUserRole
    Created on : Apr 18, 2022, 6:48:07 AM
    Author     : User
--%>
<%
    String id = (String)request.getAttribute("id");
    String name = (String)request.getAttribute("name");
    String description =(String) request.getAttribute("description");
    String type = (String)request.getAttribute("type");
    String enable = (String)request.getAttribute("enable");
    String actionType = (String)request.getAttribute("actionType");
    String beforeName = (String)request.getAttribute("beforeName");

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Role confirm</title>
    </head>
    <body style="min-height: 100%">
        <jsp:include page="heading.jsp" />
    <center style="background-color: #FBEEC1; top:  0; height:85vh; font-size: 20px"   >

        <br><h1>The role information are as follow:</h1><br>
        <p style=" font-size: 30px">Name: <%=name%></p>
        <p style=" font-size: 30px">Description: <%=description%></p>
        <p style=" font-size: 30px">Type: <%=type%></p>
        <p style=" font-size: 30px">Enable: <%=enable%></p>
        <form  method='post' action='handleRole'>
            <input type='hidden' name='action' value='<%=actionType%>'>

            <input type='hidden' name='id' value='<%=id%>'>

            <input type='hidden' name='name' value='<%=name%>'>
            <input type='hidden' name='beforeName' value='<%=beforeName%>'>

            <input type='hidden' name='description' value='<%=description%>'>
            <input type='hidden' name='type' value='<%=type%>'>

            <input type='hidden' name='enable' value='<%=enable%>'>
            <input type='submit' value='Confirm' style=" font-size: 25px">
        </form><br>
        <form><input type='button' value='Back' onclick='history.back()' style=" font-size: 25px">
        </form>
    </center>

    <jsp:include page="footer.jsp"         />

</body>
</html>
