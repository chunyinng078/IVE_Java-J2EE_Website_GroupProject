<%-- 
    Document   : addNewTrainer
    Created on : Apr 12, 2022, 10:53:24 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="b" scope="request" class="ict.bean.bookingBean"/>
<%
    String id = String.valueOf(b.getId());
    String date = b.getDate();
    String time = b.getTime();
    String Type = b.getType();
    String TypeId = b.getTypeId();
    String time1 = time.substring(0, time.indexOf(":"));
%>
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
        <form method="get" action="customerHandleRecord">
            <div style="font-size:30px">
                <label for="gymCenter"><h2>Search👤:</h2></label>
                <input type="hidden" name="action"  value="EditRecord" />
                <input type="hidden" name="custId"  value="<%=session.getAttribute("id")%>" />
                Booking ID<br><input type='text' name='id' value="<%=id%>" readonly><br>
                Booking Type<br><input type='text' value="<%=Type%> "readonly ><br>
                Booking Type Id<br><input type='text' value="<%=TypeId%>" readonly ><br>
                Booking Date<br><input type='date' name='date' value="<%=date%>" ><br>
                Booking Time<br><input type='number' name='time' value="<%=time1%>" ><br>
                <input type="submit" value="submit"/> <br>
            </div>
        </form>
    </center>
    <jsp:include page="footer.jsp"/>
</body>
</html>
