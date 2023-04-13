<%-- 
    Document   : CustomerShowList
    Created on : Apr 12, 2022, 11:04:31 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking</title>
        <body style="min-height: 100%"> 
        <center style="background-color: #FBEEC1; top:  0; height:85vh"   >
        <jsp:include page="heading.jsp" />
   
        <h1>Booking reminder:</h1>
        <h1>XXXXXXXXXXXX</h1>
        <button>check booking status</button>
        <a href="personalBookingRecord.jsp"><button>Check/update personal booking record</button></a><br>
        search:<input type="text"><br>
        <table border="1">
            <tr><th>Image</th><th>name</th><th>description</th><th>availability status</th><th>gym center</th><th>booking</th></tr>
            <tr><td></td><td></td><td></td><td></td><td></td><td></td></tr>
        </table>
    </center>
    <jsp:include page="footer.jsp"/>
    </body>
</html>
