<%-- 
    Document   : confirmATM
    Created on : 2017/3/13, 下午 05:16:59
    Author     : Administrator
--%>

<%@page import="java.util.Date"%>
<%@page import="com.verygoodbook.utils.VeryGoodBookUitilities"%>
<%@page import="com.verygoodbook.entity.PaymentType"%>
<%@page import="com.verygoodbook.entity.OrderStatus"%>
<%@page import="com.verygoodbook.entity.Order"%>
<%@page import="com.verygoodbook.service.OrderService"%>
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

    String orderId = request.getParameter("orderId");
    Order order = null;
    if (orderId != null && orderId.matches("\\d+")) {
        OrderService service = new OrderService();
        int id = Integer.parseInt(orderId);
        order = service.get(id);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>已轉帳通知</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp" >
            <jsp:param name="subtitle" value="轉帳通知"/>
        </jsp:include>
        <div id="article">
            <%
                if (order != null && member.getId().equals(order.getCustomer().getId())
                        && order.getStatus() == OrderStatus.NEW.ordinal() && order.getPaymentType() == PaymentType.ATM) {

            %>            

            <form action="<%= request.getContextPath()%>/member/confirmATM.do" method="POST">
                <p>
                    <label>訂單編號: </label>
                    <input type="text" value="<%= String.format("VGB%07d", order.getId())%>" readonly>
                    <input type="hidden" value="<%= order.getId()%>" name='orderId'>
                </p>
                <p>
                    <label for="bank">轉帳銀行: </label>
                    <input type="text" name="bank" required/>
                </p>
                <p>
                    <label>帳號後五碼: </label>
                    <input type="text" name="last5Code" pattern="\d{5}" required/>
                </p>
                <p>
                    <label>轉帳日期: </label>
                    <input type="date" name="transferDate" 
                           min="<%= VeryGoodBookUitilities.webDateFormat.format(order.getCreatedTime())%>" 
                           max="<%= VeryGoodBookUitilities.webDateFormat.format(new Date())%>" 
                           required/>
                </p>
                <p>
                    <label>轉帳時間: </label>
                    <select required name="transferTime">
                        <option value="">請選擇</option>
                        <% for (int i = 9; i < 24; i++) {
                                String timeStr = String.format("%02d:00", i);
                        %>
                        <option value="<%= timeStr%>"><%= timeStr%></option>
                        <%}%>
                    </select>
                </p>                
                <p>
                    <label>轉帳金額: </label>
                    <input type="number" name="amount" value="<%= order.getTotalAmountWithFee()%>"/>
                </p>                
                <input type="submit" value="確定"/>                
            </form>
            <%} else {%>
            <p>無需[已付款通知]處理</p>
            <%}%>
        </div>
        <%@include file="/WEB-INF/subviews/footer.jsp" %>            
    </body>
</html>
