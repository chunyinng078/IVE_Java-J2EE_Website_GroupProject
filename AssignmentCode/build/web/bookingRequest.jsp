<%-- 
    Document   : bookingRequst
    Created on : Apr 12, 2022, 11:02:16 AM
    Author     : user
--%>

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
        <title>Booking</title>
    </head>
    <body style="min-height: 100%">
        <jsp:include page="heading.jsp" />
    <center style="background-color: #FBEEC1; top:  0; height:85vh"   >
        <table border="1">
            <tr><th>Personal Trainer</th><th>gym center</th><th>Confirm</th><th>decline</th></tr>
            <tr><td></td><td></td><td></td><td></td></tr>
        </table>
        <h2>the record has been Confirm!</h2>
    </center>
    <jsp:include page="footer.jsp"         />
</html>
