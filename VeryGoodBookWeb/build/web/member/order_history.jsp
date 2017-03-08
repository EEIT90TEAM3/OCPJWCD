<%-- 
    Document   : orders_history
    Created on : 2017/3/1, 下午 02:39:52
    Author     : Administrator
--%>

<%@page import="com.verygoodbook.entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //當url路徑符合: /member/*.*
    String url = request.getRequestURI();
    Customer member = null;
    //System.out.println(url.indexOf("/member/*.*"));
    if (url.indexOf("/member/") >= 0) {
        //先在session檢查有無Customer或Customer子類別建立的物件，且attribute: "member"    
        member = (Customer) session.getAttribute("member");
        if (member == null) {
            //若無已登入的會員，redirect到login.jsp強迫先登入後才能繼續執行
            session.setAttribute("previous.page", url);
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>歷史訂單</title>
        <%@include file="/WEB-INF/subviews/global_js.jsp" %>
    </head>
    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp" >
            <jsp:param name="subtitle" value="歷史訂單"/>
        </jsp:include>
        <div id="article">
            
        </div>
        <%@include file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
