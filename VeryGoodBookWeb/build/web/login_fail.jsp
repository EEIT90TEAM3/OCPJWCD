<%-- 
    Document   : login_fail
    Created on : 2017/2/20, 下午 03:49:59
    Author     : Administrator
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登入失敗</title>
    </head>
    <body>
        <h1>登入失敗!</h1>
        <%
            List<String> errors = 
                    (List<String>)request.getAttribute("errors");
        %>
        <div style="color: darkred">
        <%= errors %>
        </div>
    </body>
</html>
