<%-- 
    Document   : confirmation
    Created on : Apr 17, 2022, 8:56:13 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String confirmForm = (String) request.getAttribute("confirmForm");

%>
<!DOCTYPE html>
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
        <h1 style="margin: 0; font-size: 50px;">Confirmation</h1>
        <%=confirmForm%>
    </center>



</body>
<jsp:include page="footer.jsp"         />
</html>
