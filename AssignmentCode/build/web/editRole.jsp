<%-- 
    Document   : editUser
    Created on : Apr 17, 2022, 1:30:03 AM
    Author     : User
--%>

<%@page import="java.util.ArrayList"%>
<jsp:useBean id="r" scope="request" class="ict.bean.roleBean"/>

<%
    ArrayList roleList = (ArrayList) request.getAttribute("roleList");

    String role = request.getParameter("role");
    String rtype = request.getParameter("rtype");

    if (rtype == null) {
        rtype = "";
    }
    String id = r.getId();
    String name = r.getName() != null ? r.getName() : "";
    String description = r.getDescription() != null ? r.getDescription() : "";
    String roleType = r.getType() != null ? r.getType() : "";
    String enable = r.getEnable() != null ? r.getEnable() : "";

    String title = "";
    String type = r.getId() != null ? "edit" : "add";

    if (type.equalsIgnoreCase("add")) {
        title = "Please fill the following form to create an new role:";
    } else if (type.equalsIgnoreCase("edit")) {
        title = "Please edit the role below:";

    }

    String readonlyEdit;
    if (type.equalsIgnoreCase("edit")) {
        readonlyEdit = "readonly";
    } else {
        readonlyEdit = "";
    }

    String inputType = "text";
    if (type.equalsIgnoreCase("add")) {
        inputType = "hidden";
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Play&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Role Form</title>
    </head>
    <style>
        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }
        body{
            font-family: 'Play', sans-serif;
        }

    </style>
    <body style="min-height: 100%">
        <jsp:include page="heading.jsp" />
    <center style="background-color: #FBEEC1; top:  0; height:85vh"   >
        <br><br>
        <h1><%=title%></h1>
        <form  method="post" action="handleRole" style='font-size: 20px;'>

            <input type="hidden" name="action"  value="confirm" />
            <input type="hidden" name="actionType"  value="<%=type%>" />
            <input name="beforeName"  maxlength="15" type="hidden" value="<%=name%>"/> 


            <%
                if (type.equalsIgnoreCase("edit")) {

            %>
            <br>ID:<br>  <input name="id"  <%=readonlyEdit%> type="text" value="<%=id%>" style='font-size: 20px;'/> <br>
            <%
                }
            %>

            <br>Role Name:<br><input name="name"  maxlength="15" type="text" value="<%=name%>" required style='font-size: 20px;'/> <br>

            <br>Description:<br> <input name="description" style =" width:400px;font-size: 20px;" min="3" maxlength="40" type="text" value="<%=description%>" required style='font-size: 20px;'/> <br>
            <br>Role Type:<br>

            <select name="type" style='font-size: 20px;'>
                <%
                    String disable = "";
                    if (rtype.equalsIgnoreCase("trainer") || rtype.equalsIgnoreCase("customer")) {
                        disable = "disabled";
                    }
                    for (int i = 0; i < roleList.size(); i++) {
                        String roleCheck = "";
                        if (roleList.get(i).equals(roleType)) {
                            roleCheck = "selected";
                        }
                %>                <option value="<%=roleList.get(i)%>" <%=roleCheck%> <%=disable%>> <%=roleList.get(i)%> </option>
                <%

                    }

                %></select><br><%                    if (rtype.equalsIgnoreCase("trainer")) {

                %>
            <input  type="hidden" name="type" value="trainer">
            <%                    } else if (rtype.equalsIgnoreCase("customer")) {

            %>
            <input  type="hidden" name="type" value="customer">

            <%                        }

            %>




            <%                String disable2 = "";
                if (rtype.equalsIgnoreCase("management")) {
                    disable2 = "disabled";
                }
                if (enable.equalsIgnoreCase("true")) {
            %>      
            <br>Enable:<br>
            <select name="enable" style='font-size: 20px;' <%=disable2%>>
                <option value="true" selected >true</option>
                <option value="false">false</option>
            </select>
            <%
            } else {
            %>
            <br>Enable:<br>
            <select name="enable" style='font-size: 20px;' <%=disable2%>>
                <option value="true" >true</option>
                <option value="false" selected >false</option>
            </select>
            <%
                }

            %>


            <br>
            <br>
            <td > <input style='font-size: 20px;' type='button' value='Back' onclick='history.back()'/> <input style='font-size: 20px;' type="submit" value="submit"/><br>
        </form>
    </center>
    <jsp:include page="footer.jsp"         />
</body>
</html>
