<%-- 
    Document   : 99
    Created on : 2017/2/17, 下午 05:36:22
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
        <table border='1'>        
            <%
                int k=1; //local variable
                //java ....            
                for (int i = 1; i < 10; i++) {
            %>
            <tr>
                <%
                for (int j = 1; j < 10; j++) {
                    out.println("<td>" + i + "*" + j + "=" + i * j + "</td>");
                }
                %>
            </tr>
            <%    }
            %>
        </table>
    </body>

</html>
