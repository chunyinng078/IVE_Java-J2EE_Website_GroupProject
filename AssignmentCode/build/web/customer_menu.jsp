<link rel="stylesheet" href="css/menu.css">


<ul>
  <li><a href="customerHandleRecord?action=search&name=&InformationType=trainer&keyword=">list Trainer/center</a></li>  
  <li><a href="customerHandleRecord?action=BookingStates&id=<%=session.getAttribute("id")%>">check booking request status</a></li>
  <li><a href="customerHandleRecord?action=listBooking&id=<%=session.getAttribute("id")%>">Check/ Update personal booking record</a></li>
  <li style="float:right"><a href="main?action=doLogout">Logout</a></li>
</ul>

