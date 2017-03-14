<%-- 
    Document   : orders_history
    Created on : 2017/3/1, 下午 02:39:52
    Author     : Administrator
--%>
<%@page import="com.verygoodbook.utils.VeryGoodBookUitilities"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.verygoodbook.service.*"%>
<%@page import="com.verygoodbook.entity.*"%>
<%@page import="java.util.List"%>
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
    
    OrderService service= new OrderService();
    List<Order> list = service.getOrdersByCustomer(member.getId());    
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
            <% if(list!=null && list.size()>0) {%>
            <table style="width:100%">
                    <tr>
                        <th>編號</th>
                        <th>時間</th>
                        <th>狀態</th>
                        <th>總金額</th>
                        <th>付款方式</th>
                        <th>貨運方式</th>                        
                        <th>總金額(含手續費)</th>
                        <th>明細</th>
                    </tr>
                <% for(Order order:list){%>
                    <tr>
                        <th><%= String.format("VGB%07d", order.getId()) %></th>
                        <th><%= VeryGoodBookUitilities.webDateTimeFormat.format(order.getCreatedTime()) %></th>
                        <th><%= OrderStatus.values()[order.getStatus()].getDescription() %>
                        <th><%= VeryGoodBookUitilities.webPriceFormat.format(order.getTotalAmount()) %></th>
                        <th>
                            <%= order.getPaymentType().getDescription() %>
                            <% if(order.getStatus()==OrderStatus.NEW.ordinal()
                                    && order.getPaymentType()==PaymentType.ATM){%>
                            <input type="button" value="通知已付款" onclick='confirmATM(<%= order.getId()%>)'>
                            <%}%>
                        </th>
                        <th><%= order.getShippingType().getDescription() %></th>
                        <th><%= VeryGoodBookUitilities.webPriceFormat.format(order.getTotalAmountWithFee()) %>元</th>
                        <th><a href='order_detail.jsp?orderId=<%= order.getId() %>'>檢視</a></th>
                    </tr>
                <% } %>
                </table>
            <%}else{%>
                <p>查無最近一個月內的訂單!</p>
            <%}%>
            <%-- = list --%>
        </div>
        <%@include file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
<script>
    function confirmATM(orderId){
        alert(orderId);
        
        //Synchronous request
        location.href = "<%= request.getContextPath() %>/member/confirmATM.jsp?orderId=" + orderId;
                
        //aSynchronous request                
    }
    
</script>