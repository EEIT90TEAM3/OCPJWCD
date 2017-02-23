<%-- 
    Document   : another_99
    Created on : 2017/2/20, 上午 10:30:34
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>99</title>
    </head>
    <body>
        <h1>99乘法表!</h1>
        <table>
            <caption>99乘法表</caption>
            <%for(int i=1;i<=9;i++){ %>
            <tr>
                <% for(int j=1;j<=9;j++){ %>
                <td><%= i%>*<%= j%> = <%= i*j %></td>
                <% } %>
            </tr>
            <% } %>
        </table>      
    </body>
</html>
