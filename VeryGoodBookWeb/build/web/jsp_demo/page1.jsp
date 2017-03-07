<%-- 
    Document   : page1
    Created on : 2017/2/24, 下午 03:33:04
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page1</title>
    </head>
    <body>
        <h1>Page1</h1>
        <%--
            out.flush();
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("page2.jsp");
            dispatcher.include(request, response);
        --%>
        <jsp:include page="page2.jsp" />        
    </body>
</html>
