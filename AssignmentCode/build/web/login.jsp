<%-- 
    Document   : login
    Created on : Apr 6, 2022, 8:26:47 PM
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

        <%
            String addRecord = request.getParameter("acCreated");


        %>
        <title>Login</title>
    </head>
    <body style="min-height: 100%">
        <jsp:include page="heading.jsp" />
    <center style="background-color: #FBEEC1; top:  0; height:85vh"   >


        <form method="post" action="main" style="top:0">

            <br><br><br><br><br>
            <h1 style="margin: 0; font-size: 50px;">Login</h1>
            <%                        if (addRecord != null) {
                    if (addRecord.equalsIgnoreCase("true")) {
                        out.println("<h2>Account created!</h2>");
                    } else {
                        out.println("<h2>Account creation failed!</h2>");
                    }

                }
            %>
            <br>
            <input type="hidden" name="action" value="authenticate"/>
            <table style="border: 2px solid; border-radius: 50% 20% / 10% 40%;width:500px; height:500px" >
                <tr>
                    <th><p align="center"><b style="font-size: 30px">ID:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </b><input style="font-size: 25px" type="text" name="username" id="username"  maxLength="20" size="20" required/></th>

                </tr>
                <tr>
                    <th><p align="center" style="font-size: 30px"><b>Password:&nbsp;&nbsp;</b>
                        <input style="font-size: 25px" type="password" id="password" name="password" maxLength="20" size="20" required/></th>
                </tr>

                <td colspan="2"><p align="center"><input style="font-size: 30px;border-radius:30px" type="submit" value="Login"/></p>

                </td>




            </table>
            <form>

            </form>
            <br>
            <form action="handleUser">
                <input type="hidden" name="action" value="getNewUser"/>
                <input type="hidden" name="creator" value="customer"/>

                <input type="submit" value="Create Account">

            </form>
        </form>

    </center>

    <jsp:include page="footer.jsp"         />

</body>

</html>
--