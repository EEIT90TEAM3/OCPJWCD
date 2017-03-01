<%-- 
    Document   : check_out
    Created on : 2017/2/20, 下午 03:42:09
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
        <title>結帳作業</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp" />        
        <div id="article" style="height: 75vh;width:75%;margin:auto;">
        </div>
        <%@include  file="/WEB-INF/subviews/footer.jsp" %>        
    </body>
</html>
