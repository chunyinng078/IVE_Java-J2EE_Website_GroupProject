<link rel="stylesheet" href="css/menu.css">

<%
    String role = session.getAttribute("role").toString();
    String id = session.getAttribute("id").toString();
    if (role.equals("staff")) {
        out.print("<ul>");
        out.print("<li><a href='handleRecord?action=search&name=&InformationType=trainer'>list Trainer/center</a></li>");
        out.print("<li><a  href='staff_addNew.jsp?type=trainer'>Add personal trainer</a></li>");
        out.print("<li><a href='staff_addNew.jsp?type=center'>Add Gym Center</a></li>");
        out.print("<li><a href='handleRecord?action=searchBooking&BookingType=trainer&role="+role+"'>Confirm/decline booking request</a></li>");
        out.print("<li style='float:right'><a href='main?action=doLogout'>Logout</a></li>");
        out.print("</ul>");
    } else {
        out.print("<ul>");
        out.print("<li><a href='handleRecord?action=search&name=&InformationType=trainer'>list Trainer/center</a></li>");
        out.print("<li><a href='handleRecord?action=searchBooking&BookingType=trainer&role="+role+"&id="+id+"'>Confirm/decline booking request</a></li>");
        out.print("<li style='float:right'><a href='main?action=doLogout'>Logout</a></li>");
        out.print("</ul>");
    }
%>


<!--<ul>
    <li><a href="handleRecord?action=search&name=&InformationType=trainer">list Trainer/center</a></li>  
    <li><a  href="staff_addNew.jsp?type=trainer">Add personal trainer</a></li>
    <li><a href="staff_addNew.jsp?type=center">Add Gym Center</a></li>
    <li><a href="handleRecord?action=searchBooking&BookingType=trainer">Confirm/decline booking request</a></li>
    <li style="float:right"><a href="main?action=doLogout">Logout</a></li>
</ul>-->

