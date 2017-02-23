<%-- 
    Document   : login_fail
    Created on : 2017/2/20, 下午 03:50:46
    Author     : Administrator
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--網頁跳轉-->
        <meta http-equiv="refresh" content="3;url=login.jsp">
        <title>登入失敗</title>
    </head>
    <body>
        <h1>可惜失敗了</h1>
        <%
           List<String> errors=(List<String>)request.getAttribute("errors");//偷懶強迫轉型，正確請參考login_ok!
         %>  
         <div style="color: red">
             <%= errors %>
         </div>
    </body>
</html>
