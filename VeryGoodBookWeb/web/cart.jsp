<%-- 
    Document   : cart
    Created on : 2017/2/24, 下午 04:33:29
    Author     : Administrator
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>購物車</title>
        <%@include file="WEB-INF/subviews/global_js.jsp" %>
    </head>
    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp" >
            <jsp:param name="subtitle" value="購物車"/>
        </jsp:include>
        <div id="article">
            
        </div>
        <%@include  file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
