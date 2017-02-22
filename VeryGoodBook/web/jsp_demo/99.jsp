<%-- 
    Document   : 99
    Created on : 2017/2/17, 下午 05:39:49
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
            //JAVA
            out.println("<table border='1'>");
            for(int i=1;i<10;i++){
                out.println("<tr>");
                for(int j=1;j<10;j++){
                    out.println("<td>"+i+"*"+j+"="+i*j+"</td>");
                }
                out.println("</tr>");
            }
         %>   
    </body>
</html>
