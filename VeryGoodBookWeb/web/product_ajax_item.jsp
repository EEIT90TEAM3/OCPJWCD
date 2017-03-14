<%-- 
    Document   : product_item
    Created on : 2017/3/1, 下午 03:44:18
    Author     : Administrator
--%>

<%@page import="com.verygoodbook.entity.Book"%>
<%@page import="com.verygoodbook.entity.Product"%>
<%@page import="com.verygoodbook.service.ProductService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="item_article">
    <%
        String pid = request.getParameter("pid");
        int id = 0;
        if (pid != null && pid.matches("\\d+")) {
            id = Integer.parseInt(pid);
        }
        ProductService service = new ProductService();
        Product p = service.get(id);
        if (p != null) {
    %>

    <a href='product_item.jsp?pid=<%= p.getId()%>'><img style='width:200px' src='<%= p.getPhotoUrl()%>'></a>                
    <h4><%= p.getName()%></h4>
    <% if (p instanceof Book) {%>
    <span><%= ((Book) p).getSubtitle()!=null?((Book) p).getSubtitle()+"<br>":""%></span>
    <span>作者：<%= ((Book) p).getAutherName()%></span><br>
    <span>
        原價: <%= ((Book) p).getListPrice()%><br>
        <%  
            int discount = (100 - ((Book) p).getDiscount());
            if (discount % 10 == 0) discount /= 10;
        %>
        <span>優惠價：<%= discount %>折,</span>     
    </span> 
    <%}%>                            
    <div>
        <span>售價: <%= p.getUnitPrice()%>元</span>
        <% if (p.getColors() != null && p.getColors().size() > 0) {%>
        <span>
            <label for='thecolor_<%= p.getId()%>'>顏色:</label>                
            <select id='thecolor_<%= p.getId()%>' name='color' required>
                <option value=''>請選擇...</option>
                <%  for (String colorName : p.getColors()) { %>
                <option value='<%=colorName%>'><%=colorName%></option>
                <%}%>
            </select> 
        </span>
        <%}%>    
        <label for='quantity_<%= p.getId()%>'>數量:</label>
        <input id='quantity_<%= p.getId()%>' type="number" min='1' max='<%= p.getStock()%>' value='1'>
        <a href='javascript:addProductToCart(<%= p.getId()%>, "thecolor_")'>
            <img alt="add to cart" src='images/cart.png'>
        </a>
    </div>
    <%} else {%>
    <p>查無此商品資訊!</p>
    <%}%>
</div>
