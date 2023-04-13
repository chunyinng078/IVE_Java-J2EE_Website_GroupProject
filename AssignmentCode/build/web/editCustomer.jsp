<%-- 
    Document   : editCustomer
    Created on : Apr 13, 2022, 6:47:43 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="c" scope="request" class="ict.bean.CustomerBean"/>
<%
    String type = c.getCustid() != null ? "edit" : "add";
    String id = c.getCustid() != null ? c.getCustid() : "";
    String name = c.getCustid() != null ? c.getName() : "";
    String tel = c.getCustid() != null ? c.getTel() : "";
    String age = c.getCustid() != null ? String.valueOf(c.getAge()):"";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form  method="get" action="handleEdit">
            <input type="hidden" name="action"  value="<%=type%>" />
            ID  <input name="id"  type="text" value="<%=id%>"/> <br>
            Name <input name="name"  type="text" value="<%=name%>"/> <br>
            Tel <input name="tel"  type="text" value="<%=tel%>"/> <br>
            Age <input name="age"  type="text" value="<%=age%>"/> <br>
            <td ><input type="submit" value="submit"/> <br>
        </form>

    </body>
</html>
