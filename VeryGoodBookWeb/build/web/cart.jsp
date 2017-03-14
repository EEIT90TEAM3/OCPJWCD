<%-- 
    Document   : cart
    Created on : 2017/2/24, 下午 04:33:29
    Author     : Administrator
--%>
<%@page import="com.verygoodbook.entity.VIP"%>
<%@page import="com.verygoodbook.entity.Customer"%>
<%@page import="com.verygoodbook.entity.Book"%>
<%@page import="com.verygoodbook.entity.Product"%>
<%@page import="com.verygoodbook.entity.ShoppingCart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
            <form action='update_cart.do' method="POST">
                <%
                    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
                    if (cart != null && cart.size() > 0) {
                        Customer member = (Customer) session.getAttribute("member");
                        cart.setMember(member);
                %>                
                <table border='1'>
                    <caption>購物車明細</caption>
                    <thead>
                        <tr>
                            <th>No.</th><th>名稱</th><th>顏色</th><th>原價</th><th>折扣</th><th>售價</th><th>數量</th><th>刪除</th>                        
                        </tr>
                    </thead>                    
                    <tbody>
                        <% for (Product p : cart.getProductsSet()) {%>
                        <tr>
                            <td><%= p.getId()%></td><td><%= p.getName()%></td>
                            <td><%= p.getColor() == null ? "" : p.getColor()%></td>
                            <% if (p instanceof Book) {%>
                            <td><%= ((Book) p).getListPrice()%>元</td>
                            <%
                                int discount = (100 - ((Book) p).getDiscount());
                                if (discount % 10 == 0) discount /= 10;
                            %>
                            <td><%= discount%> 折</td>
                            <%} else {%>
                                <td></td><td></td>
                            <%}%>
                            <td><%= p.getUnitPrice()%></td>
                            <td><input type='number' name='quantity_pid_<%= p.getId()%><%= p.getColor()!=null?"_"+p.getColor():"" %>' value='<%= cart.getQuantity(p)%>' min='1' max='<%= p.getStock()%>' ></td>
                            <td><input type='checkbox' name='delete_pid_<%= p.getId()%><%= p.getColor()!=null?"_"+p.getColor():"" %>'></td>   
                        </tr>                    
                        <%}%>
                    </tbody>
                    <tfoot>
                        <% if ((member instanceof VIP)) {%>
                        <tr>
                            <td colspan="3">原來總金額:</td>
                            <td colspan="5"><%= cart.getTotalAmount()%></td>
                        </tr>
                        <tr>
                            <td colspan="3">VIP折扣:</td>
                            <td colspan="5"><%= ((VIP) member).getDiscount()%></td>
                        </tr>
                        <%}%>
                        <tr>
                            <td colspan="3">實際總金額:</td>
                            <td colspan="5"><%= cart.getVIPTotalAmount()%></td>
                        </tr>
                    </tfoot>
                </table>
                <%} else {%>
                <p>查無購物車!</p>
                <%}%>
                <input type='button' value="繼續購物" onclick="goShopping()">                
                <%if (cart != null && cart.size() > 0) {%>
                <span style="float: right">
                <input type='submit' value="修改購物車">
                <input type='button' value="我要結帳" onclick="checkOut()">
                </span>
                <%}%>
            </form>
        </div>
        <%@include  file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
<script>
    function goShopping(){
        location.href="<%= request.getContextPath() %>/products_list.jsp";
    }
    
    function checkOut(){
        location.href="<%= request.getContextPath() %>/member/check_out.jsp";
    }
</script>