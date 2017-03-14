<%-- 
    Document   : check_out_ok
    Created on : 2017/3/13, 上午 11:25:06
    Author     : Administrator
--%>

<%@page import="com.verygoodbook.service.OrderService"%>
<%@page import="com.verygoodbook.entity.Order"%>
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
    
    Order order = (Order)request.getAttribute("order");

    if(order!=null){
        OrderService service = new OrderService();
        order = service.get(order.getId());
    }
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>結帳完成</title>
        <%@include file="/WEB-INF/subviews/global_js.jsp" %>
    </head>
    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp" >
            <jsp:param name="subtitle" value="結帳完成"/>
        </jsp:include>
        <div id="article">
            <%= order %>
            <h3>訂單編號<%= String.format("vGB%07d", order.getId()) %>, 
                訂購時間:<%= order.getCreatedTime() %>, 訂單狀態: <%= order.getStatus()%></h3>
                <table>
                    <caption>訂單明細</caption>
                </table>
                
        </div>
        <%@include  file="/WEB-INF/subviews/footer.jsp" %> 
    </body>
</html>
