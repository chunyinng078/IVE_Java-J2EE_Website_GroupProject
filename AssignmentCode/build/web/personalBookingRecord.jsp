<%-- 
    Document   : template
    Created on : Apr 6, 2022, 10:11:56 PM
    Author     : User
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
        <title>Personal booking record</title>
    </head>
    <body style="min-height: 100%">
        <jsp:include page="heading.jsp" />
    <center style="background-color: #FBEEC1; top:  0; height:85vh"   >
        <table border="1">
            <tr><th>Booking date</th><th>Personal Trainer</th><th>Gym center</th><th>edit</th></tr>
        </table>
        <form>
            Booking date:<input type="date"><br>
            Personal Trainer:<input type="text"><br>
            Gym center:<input type="text"><br>
            <button>Confirm</button>
        </form>

    </center>
    <jsp:include page="footer.jsp"/>
</body>
</html>
