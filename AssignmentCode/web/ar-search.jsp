<%-- 
    Document   : template
    Created on : Apr 6, 2022, 10:11:56 PM
    Author     : User
--%>

<%@page import="ict.bean.bookingBean"%>
<%@page import="ict.bean.GymBeanWithID"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    if (session.getAttribute("userInfo") == null || !(session.getAttribute("role").equals("management"))) {
        String redirectURL = "pleaseLogin.jsp";
        response.sendRedirect(redirectURL);
    }


%>
<%
    ArrayList<GymBeanWithID> gym = (ArrayList<GymBeanWithID>) request.getAttribute("gym");
    ArrayList<bookingBean> allRecords = (ArrayList<bookingBean>) request.getAttribute("allRecords");
    


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
            }
        </style>
                <script>
            function downloadCSV(csv, filename) {
                var csvFile;
                var downloadLink;

                //CREATE THE FILE 
                csvFile = new Blob([csv], {type: "text/csv"});

                // CREATE DOWNLOAD LINK
                downloadLink = document.createElement("a");

                // DEFINE FILE NAME
                downloadLink.download = filename;

                // LINK TO FILE
                downloadLink.href = window.URL.createObjectURL(csvFile);

                // HIDE LINK
                downloadLink.style.display = "none";

                // ADD LINK
                document.body.appendChild(downloadLink);

                // CLICK DOWNLOAD
                downloadLink.click();
            }
            function exportTableToCSV(filename) {
                var csv = [];
                var rows = document.querySelectorAll("table tr");

                for (var i = 0; i < rows.length; i++) {
                    var row = [], cols = rows[i].querySelectorAll("td, th");

                    for (var j = 0; j < cols.length; j++)
                        row.push(cols[j].innerText);

                    csv.push(row.join(","));
                }

                // START TO DOWN THE FILE
                downloadCSV(csv.join("\n"), filename);
            }
        </script>
        <title>Analytic / Report </title>
    </head>
    <body style="min-height: 100%">
        <jsp:include page="heading.jsp" />
        <jsp:include page="sm-menu.jsp" />

    <center style="background-color: #FBEEC1; top:  0; min-height:85vh"   >
        <h1 style="margin: 0; font-size: 50px;">Search records of Personal Trainer /Gym Center 📖 </h1>

        <br>
        <%
                    out.println("<table hidden>");
            out.println("<tr>");
            out.println("<th>Booking ID</th>  <th> Date</th><th> Type</th><th> Type ID</th ><th> Cust ID</th ><th> time</th ><th> status</th >");
            out.println("</tr>");
            // loop through the customer array to display each customer record
            for (int i = 0; i < allRecords.size(); i++) {
                bookingBean c = allRecords.get(i);
                out.println("<tr>");

                out.println("<td>" + c.getId()+ "</td>");
                out.println("<td>" + c.getDate()+ "</td>");
                out.println("<td>" + c.getType()+ "</td>");
                out.println("<td>" + c.getTypeId() + "</td>");
                out.println("<td>" + c.getCustid()+ "</td>");
                out.println("<td>" + c.getTime()+ "</td>");
                out.println("<td>" + c.getStatus()+ "</td>");
                out.println("</tr>");

            }
            out.println("</table>");
        %>
    
            <button style="font-size:25px" onclick="exportTableToCSV('bookingRecord.csv')">Export overall booking records</button>

        <br><br>
        <form method="post" action="handleBookingSearch">
            <input type="hidden" name="action" value="list">
            <div style="font-size:20px">  
                <input type="hidden" name="type" value="trainer">
                <label for="gymCenter"><h2>Search booking of Personal Trainer 👤:</h2></label>
                Enter trainer ID: <input requird type="text" name='id' placeholder="Enter here" required style="font-size:25px" />
                <br> <br>
                <input type="submit" value="Search🔎" style="font-size:25px">
            </div>
        </form>

        <br><br><br><br>


        <form method="post" action="handleBookingSearch">
            <input type="hidden" name="action" value="list">
            <div style="font-size:20px">  
                <input type="hidden" name="type" value="center">
                <label for="gymCenter"><h2>Search booking of Gym Center :</h2></label>
                <select name="id" id="id" style='font-size: 30px;'>

                    <%
                        for (int i = 0; i < gym.size(); i++) {
                            GymBeanWithID b = gym.get(i);

                    %>
                    <option value="<%=b.getCenterID()%>"><%=b.getCenterName()%></option>
                    <%
                        }
                    %>
                </select>


                <br> <br>
                <input type="submit" value="Search🔎" style="font-size:25px">
            </div>
        </form>
        <br><br>
        <br><br>



        <form style="font-size:30px" action="handleBookingSearch">
            <input type="hidden" name="action" value="income">
            <label for="gymCenter"><h4>Search income:</h4></label>

            <label for="year" style="font-size:30px">Income of year:</label>

            <select name="year" id="year" style="font-size:30px">
                <option value="2025">2025</option>
                <option value="2024">2024</option>

                <option value="2023">2023</option>
                <option value="2022" selected>2022</option>
                <option value="2021">2021</option>
                <option value="2020">2020</option>

                <option value="2019">2019</option>
                <option value="2018">2018</option>
            </select>      

            <label for="month" style="font-size:30px">and month</label>

            <select name="month" id="month" style="font-size:30px">
                <option value="0" selected>none</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4" selected>4</option>  
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>  
                <option value="9">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
            </select>   <br>  
            <br><input type="submit" value="Search" style="font-size:30px">
        </form>




    </center>
    <jsp:include page="footer.jsp"         />
</body>
</html>
