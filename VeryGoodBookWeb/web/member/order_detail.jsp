<%-- 
    Document   : order_detail
    Created on : 2017/3/13, 下午 03:29:12
    Author     : Administrator
--%>

<%@page import="java.util.List"%>
<%@page import="com.verygoodbook.service.OrderService"%>
<%@page import="com.verygoodbook.entity.*"%>
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
    
    String orderId = request.getParameter("orderId");    
    OrderService service= new OrderService();
    Order order = null;
    if(orderId!=null && orderId.matches("\\d+")){
        int oid = Integer.parseInt(orderId);
        order = service.get(oid);            
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>訂單明細</title>
        <%@include file="/WEB-INF/subviews/global_js.jsp" %>
    </head>
    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp" >
            <jsp:param name="subtitle" value="訂單明細"/>
        </jsp:include>
        <div id="article">
            <% if(order!=null && member.getId().equals(order.getCustomer().getId())){ %>
                <%= order %>
            <% }else{ %>
                <p>您無權檢視此訂單!</p>
            <% } %>
        </div>
        <%@include file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
