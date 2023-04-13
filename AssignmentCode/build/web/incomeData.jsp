<%-- 
    Document   : template
    Created on : Apr 6, 2022, 10:11:56 PM
    Author     : User
--%>

<%@page import="ict.bean.incomeBean"%>
<%@page import="ict.bean.roleBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String year = request.getParameter("year");
    String month = request.getParameter("month");

    ArrayList<incomeBean> t = (ArrayList<incomeBean>) request.getAttribute("t");
    ArrayList<incomeBean> c = (ArrayList<incomeBean>) request.getAttribute("c");

    String title = "";
    if (month.equalsIgnoreCase("0")) {
        title = "Income table of Year " + year;
    } else {
        title = "Income table of Year " + year + " and month " + month;

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
            }
        </style>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load('current', {'packages': ['bar']});
            google.charts.setOnLoadCallback(drawStuff);

            function drawStuff() {
                var data = new google.visualization.arrayToDataTable([
                    ['Trainer', 'HKD'],
            <%
                for (int i = 0; i < t.size(); i++) {
                    String output = "ID: " + t.get(i).getId() + " Name: " + t.get(i).getName();
                    String income = t.get(i).getIncome();

                    out.println("['" + output + "'," + income + "],");

                }
            %>
                ]);

                var options = {
                    title: 'Trainer Income',
                    width: 1000,
                    height: 200,
                    legend: {position: 'none'},
                    chart: {title: 'Trainer Income'},
                    bars: 'vertical', // Required for Material Bar Charts.
                    axes: {
                        x: {
                            0: {side: 'top', label: 'Income'} // Top x-axis.
                        }
                    },
                    bar: {groupWidth: "10%"}
                };

                var chart = new google.charts.Bar(document.getElementById('top_x_div'));
                chart.draw(data, options);

                //
                var data = new google.visualization.arrayToDataTable([
                    ['Center', 'HKD'],
            <%
                for (int i = 0; i < c.size(); i++) {
                    String output = "ID: " + c.get(i).getId() + " Name: " + c.get(i).getName();
                    String income = c.get(i).getIncome();

                    out.println("['" + output + "'," + income + "],");

                }
            %>
                ]);

                var options = {
                    title: 'Center Income',
                    width: 1000,
                    height: 200,
                    legend: {position: 'none'},
                    chart: {title: 'Center Income'},
                    bars: 'vertical', // Required for Material Bar Charts.
                    axes: {
                        x: {
                            0: {side: 'top', label: 'Income'} // Top x-axis.
                        }
                    },
                    bar: {groupWidth: "10%"}
                };

                var chart = new google.charts.Bar(document.getElementById('top_x_center2'));
                chart.draw(data, options);
            };
        </script>


        <title>Income Data</title>
    </head>

    <body style="min-height: 100%">
        <jsp:include page="heading.jsp" />
    <center style="background-color: #FBEEC1; top:  0; min-height:85vh"   >
        <h1 style="margin: 0; font-size: 50px;"><%=title%></h1>

        <form style="font-size:20px" action="">

            <h2>Trainer Income :</h2>

            <%
                //out.println("<table border='1'               >");
                //out.println("<tr>");
                //out.println("<th>Trainer ID</th>  <th>Trainer Name</th><th>Trainer Income</th>");
                //out.println("</tr>");
                //for (int i = 0; i < t.size(); i++) {
                //    incomeBean r = t.get(i);
                //    out.println("<tr>");

                //   out.println("<td>" + r.getId() + "</td>");
                //    out.println("<td>" + r.getName() + "</td>");
                //    out.println("<td>" + r.getIncome() + "</td>");
                //    out.println("</tr>");
                //}
                if (t.size() == 0) {
                    out.println("No records");

                } else {
                    out.println("<div id='top_x_div' style='width: 1200px; height: 300px;'></div>");
                }
                out.println("</table>");
            %>

        </form>

        <form style="font-size:20px" action="">

            <h2>Center Income :</h2>
            <%                       // loop through the customer array to display each customer record
                // out.println("<table border='1'               >");
                //  out.println("<tr>");
                //    out.println("<th>Center ID</th>  <th>Center Name</th><th>Center Income</th>");
                //   out.println("</tr>");
                // loop through the customer array to display each customer record
                ///    for (int i = 0; i < c.size(); i++) {
                //        incomeBean r = c.get(i);
                //      out.println("<tr>");

                //     out.println("<td>" + r.getId() + "</td>");
                //     out.println("<td>" + r.getName() + "</td>");
                //      out.println("<td>" + r.getIncome() + "</td>");
                //     out.println("</tr>");
                //  }
                if (c.size() == 0) {
                    out.println("No records");

                } else {
                    out.println("<div id='top_x_center2' style='width: 1200px; height: 300px;'></div>");
                }
                out.println("</table>");

            %>

        </form>
        <from style="font-size:20px"> <input type="button" onclick='history.back()' value="Back" style="font-size:20px"></from>
 <br><br>
    </center>
           
    <jsp:include page="footer.jsp"         />
</body>
</html>
