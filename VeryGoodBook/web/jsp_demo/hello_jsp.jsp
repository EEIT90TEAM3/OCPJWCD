<%-- 
    Document   : hello_jsp
    Created on : 2017/2/17, 下午 05:33:22
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            request.setCharacterEncoding("UTF-8");//處理表單編碼問題!
            String welcome="您好";
        %>
        <h1><%= welcome %>，HelloServlet at/vgb</h1>
        <p></p>
        <h2>Implicit Variables</h2>
        <h3>request</h3>
        <p>Context Path:<%= request.getContextPath() %></p>
        <p>RequestURL:<%= request.getRequestURI() %></p>
        <p>RequestURL:<%= request.getRequestURI() %></p>
        <p>Method:<%= request.getMethod()%></p>
        <p>Parameter('resr'):<%= request.getParameter("test")%></p>
        <form method="POST">
            <fieldset>
                <input name="test"><br>
                <input type="submit">
            </fieldset>    
        </form>
        <p>Header('user.agent'):<%= request.getHeader("user-agent") %></p>
    </body>
</html>
