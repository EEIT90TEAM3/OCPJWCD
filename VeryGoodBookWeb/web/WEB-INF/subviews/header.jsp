<%-- 
    Document   : header
    Created on : 2017/2/24, 下午 03:59:36
    Author     : Administrator
--%>
<%@page import="com.verygoodbook.entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <div id="header">
            <h1>非常好書 
                <sub>
                    <%= request.getParameter("subtitle")==null?"歡迎光臨":request.getParameter("subtitle") %>
                </sub>
            </h1>
            <hr>
        </div>
        <div id="nav">
            <a href="<%= request.getContextPath()%>/">Home</a> | 
            <a href="<%=request.getContextPath()%>/products_list.jsp">產品清單</a> | 
            <a href="<%=request.getContextPath()%>/cart.jsp">購物車</a> | 
            <span id="member-span">
            <%
                Customer member = (Customer) session.getAttribute("member");
                if (member == null) {
            %>            
            <a href="<%=request.getContextPath()%>/register.jsp">會員註冊</a> | 
            <a href="<%=request.getContextPath()%>/login.jsp">會員登入</a> |             
            <% } else {%>            
            <a href="<%=request.getContextPath()%>/member/update.jsp">會員修改</a> | 
            <a href="<%=request.getContextPath()%>/member/order_history.jsp">歷史訂單</a> | 
            <a href="<%=request.getContextPath()%>/logout.do">會員登出</a> | 
            <a href="javascript: logout()">登出(ajax)</a> |             
            <% }%>                
            </span>
            <span style="font-size: smaller;float:right" id="welcome-span">
                你好! ${sessionScope.member.name}
            </span>
            <hr>
        </div>
