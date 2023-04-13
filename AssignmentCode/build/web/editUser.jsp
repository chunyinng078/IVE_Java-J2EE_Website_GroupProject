<%-- 
    Document   : editUser
    Created on : Apr 17, 2022, 1:30:03 AM
    Author     : User
--%>

<%@page import="java.util.ArrayList"%>
<jsp:useBean id="u" scope="request" class="ict.bean.UserBean"/>

<%
    ArrayList roleList = (ArrayList) request.getAttribute("roleList");

    String custid = u.getUserid() != null ? u.getUserid() : "";
    String username = u.getUsername() != null ? u.getUsername() : "";
    String password = u.getPassword() != null ? u.getPassword() : "";
    String name = u.getName() != null ? u.getName() : "";
    int tel = u.getTel();
    String sTel = String.valueOf(tel);
    if (sTel.equalsIgnoreCase("0")) {
        sTel = "";
    }
    String enable = u.getEnable() != null ? u.getEnable() : "";

    String type = u.getUserid() != null ? "edit" : "add";
    String title = "";

    if (type.equalsIgnoreCase("add")) {
        title = "Please fill the following form to create an new account:";
    } else if (type.equalsIgnoreCase("edit")) {
        title = "Please edit the record below:";

    }

    String creator = request.getParameter("creator");
    boolean customerPage = false;
    if (creator != null && creator.equalsIgnoreCase("customer")) {
        customerPage = true;
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

    String role = "";

    if (customerPage) {
        role = u.getRole() != null ? u.getRole() : "customer";
    } else {
        role = u.getRole() != null ? u.getRole() : "staff";

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
        <title>Account Form</title>
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
        <form  method="post" action="handleUserEdit" style='font-size: 20px;'>

            <input type="hidden" name="action"  value="confirm" />
            <input type="hidden" name="actionType"  value="<%=type%>" />

            <%
                if (customerPage) {

            %>
            <input type="hidden" name="creator"  value="customer" />
            <%                }
            %>

            <%
                if (type.equalsIgnoreCase("edit")) {

            %>
            <br>ID:<br>  <input name="id"  <%=readonlyEdit%> type="text" value="<%=custid%>" style='font-size: 20px;'/> <br>
            <%
                }
            %>

            <br>Username:<br><input name="username"  <%=readonlyEdit%> maxlength="15" type="text" value="<%=username%>" required style='font-size: 20px;'/> <br>
            <br>Password:<br> <input name="password"  min="3" maxlength="15" type="text" value="<%=password%>" required style='font-size: 20px;'/> <br>
            <br>Name:<br> <input name="name"   pattern="[a-zA-Z, ]*" min="3" maxlength="15" max="20" type="text" value="<%=name%>" required style='font-size: 20px;'/> <br>

            <%
                if (!customerPage) {
                    if (!role.equals("customer") && !role.equals("trainer")) {
            %>

            <br>Role:<br>

            <select name="role" style='font-size: 20px;'>
                <%
                    for (int i = 0; i < roleList.size(); i++) {
                        String roleCheck = "";
                        if (roleList.get(i).equals(role)) {
                            roleCheck = "selected";
                        }
                %>                <option value="<%=roleList.get(i)%>" <%=roleCheck%>><%=roleList.get(i)%></option>
                <%

                    }

                %></select><br><%                        } else if (role.equals("customer")) {
                %>
            <input type="hidden" name="role" value="customer" >    

            <%
            } else if (role.equals("trainer")) {

            %>            <input type="hidden" name="role" value="trainer ">    
            <%    }
            %>
            <%
                }
            %>



            <br>Tel:<br> <input style='font-size: 20px;' name="tel" maxlength="8" size="8" type="tel" value="<%=sTel%>" pattern="[0-9]{8}" required/> <br>


            <%
                if (!customerPage

                
                    ) {
                    if (enable.equalsIgnoreCase("true")) {
            %>      
            <br>Enable:<br>
            <select name="enable" style='font-size: 20px;'>
                <option value="true" selected >true</option>
                <option value="false">false</option>
            </select>
            <%
            } else {
            %>
            <br>Enable:<br>
            <select name="enable" style='font-size: 20px;'>
                <option value="true" >true</option>
                <option value="false" selected >false</option>
            </select>
            <%
                    }
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
