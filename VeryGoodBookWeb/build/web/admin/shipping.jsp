<%-- 
    Document   : shipping
    Created on : 2017/3/3, 上午 11:30:49
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>出貨作業</title>
    </head>
    <body>
         <jsp:include page="/WEB-INF/subviews/header.jsp" >
            <jsp:param name="subtitle" value="出貨作業"/>
        </jsp:include>
        <div id="article">
        </div>
        <%@include file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
