<%-- 
    Document   : login_ok
    Created on : 2017/2/20, 下午 03:49:23
    Author     : Administrator
--%>
<%@page import="com.verygoodbook.entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登入成功</title>
    </head>
    <body>
        <h1>登入成功!</h1>
        <%
            Object obj = session.getAttribute("member");
            if (obj instanceof Customer) {
                Customer c = (Customer) obj;
        %>
        <p>歡迎光臨，<%=c.getName() %></p>
        <%  }%>
    </body>
</html>
