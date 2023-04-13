<%-- 
    Document   : userFound
    Created on : Apr 18, 2022, 6:42:04 AM
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
        <title>Role Found</title>
        <style>
            body{
                font-family: 'Play', sans-serif;
            }
        </style>
    </head>

    <body style="min-height: 100%">
        <jsp:include page="heading.jsp" />
    <center style="background-color: #FBEEC1; top:  0; height:85vh"   >
        <br>
        <br>
        <br>

        <h1 style="margin: 0; font-size: 50px;">Role already exist!</h1>
        <br><br><br>
        <h1>Please use other role name</h1>
        <input type="button" onclick="history.back(-2)" value="Back" style="font-size: 50px;">
    </center>
    <jsp:include page="footer.jsp"         />


</body>
</html>
