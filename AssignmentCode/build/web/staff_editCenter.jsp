<%-- 
    Document   : personalTrainerList
    Created on : Apr 12, 2022, 10:40:53 AM
    Author     : user
--%>

<%@page import="ict.bean.GymBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.TrainerBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="g" scope="request" class="ict.bean.GymBean"/>
<%
    if (session.getAttribute("userInfo") == null || !(session.getAttribute("role").equals("staff")||(session.getAttribute("role").equals("trainer")))) {
        String redirectURL = "pleaseLogin.jsp";
        response.sendRedirect(redirectURL);
    }
%>
<%
    String id = String.valueOf(g.getId());
    String centerName = g.getCenterName();
    String States = g.getStates();
    String HrRate = String.valueOf(g.getHrRate());
    String Description = g.getDescription();
//    String ImageID = String.valueOf(t.getImageID());
%>
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
                min-height: 100%;
            }
            h1{
                margin: 0;
                font-size: 50px;
            }
            div,input,select,button{
                font-size:30px
            }
            table{
                font-size:20px
            }

        </style>
        <style>
            .switch {
                position: relative;
                display: inline-block;
                width: 60px;
                height: 34px;
            }

            .switch input {
                opacity: 0;
                width: 0;
                height: 0;
            }

            .slider {
                position: absolute;
                cursor: pointer;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background-color: #ccc;
                -webkit-transition: .4s;
                transition: .4s;
            }

            .slider:before {
                position: absolute;
                content: "";
                height: 26px;
                width: 26px;
                left: 4px;
                bottom: 4px;
                background-color: white;
                -webkit-transition: .4s;
                transition: .4s;
            }

            input:checked + .slider {
                background-color: #2196F3;
            }

            input:focus + .slider {
                box-shadow: 0 0 1px #2196F3;
            }

            input:checked + .slider:before {
                -webkit-transform: translateX(26px);
                -ms-transform: translateX(26px);
                transform: translateX(26px);
            }

            /* Rounded sliders */
            .slider.round {
                border-radius: 34px;
            }

            .slider.round:before {
                border-radius: 50%;
            }
        </style>
        <title>Trainer List</title>
    </head>
    <body>
        <jsp:include page="heading.jsp" />
        <jsp:include page="staff_menu.jsp" />
    <center style="background-color: #FBEEC1; top:  0; min-height:85vh" >
        <h1>Show List of Personal Trainer /Gym Center 📖 </h1>
        <form method="get" action="handleRecord">
            <div style="font-size:30px">
                <label for="gymCenter"><h2>Search👤:</h2></label>
                <input type="hidden" name="action"  value="editCenter" />
                Center ID<br><input type='text' name='id' value="<%=id%>" readonly><br>
                Center name<br><input type='text' name='centerName' value="<%=centerName%>"><br>
                
                Center description:<br>
                <textarea rows='5' cols='50' name='description' ><%=Description%></textarea><br>
                Hourly Rate <br><input type='number' name='hrRate' min='100' placeholder='please insert number' value="<%=HrRate%>"><br>
                <input type="hidden" name="imageID"  value="1" />
                Enable/disable
                <%
                    if("enable".equals(States)){
                        out.print("<input type='checkbox' name='states' checked value='states'/>");
                    }else{
                        out.print("<input type='checkbox' name='states' value='states'/>");
                    }
                %>
                <!--<label class='switch'><input type='checkbox' name='states' checked value='states'><span class='slider round'></span></label>-->
                <br>
                <input type="submit" value="submit"/> <br>
            </div>
        </form>
        <br><br><br><br><br><br><br><br><br><br><br>

    </center>
    <jsp:include page="footer.jsp"/>
</body>
</html>
