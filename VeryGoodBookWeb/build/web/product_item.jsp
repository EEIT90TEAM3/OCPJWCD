<%-- 
    Document   : product_item
    Created on : 2017/3/1, 下午 03:44:18
    Author     : Administrator
--%>

<%@page import="com.verygoodbook.entity.Book"%>
<%@page import="com.verygoodbook.entity.Product"%>
<%@page import="com.verygoodbook.service.ProductService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>產品明細</title>
        <%@include file="/WEB-INF/subviews/global_js.jsp" %>
    </head>
    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp" >
            <jsp:param name="subtitle" value="產品明細"/>
        </jsp:include>
        <div id="article">
            <%
                String pid = request.getParameter("pid");
                int id = 1;
                if (pid != null && pid.matches("\\d+")) {
                    id = Integer.parseInt(pid);
                }
                ProductService service = new ProductService();
                Product p = service.get(id);
            %>

            <a href='product_item.jsp?pid=<%= p.getId()%>'><img style='width:200px' src='<%= p.getPhotoUrl()%>'></a>                
            <h4>
                <span style="font-size: smaller">
                    No.<%=(p instanceof Book && ((Book) p).getPublisher() != null) ? String.format("%02d", ((Book) p).getPublisher().getId()) : ""%>-<%= String.format("%05d", p.getId())%>
                </span>. <%= p.getName()%></h4>

            <% if (p instanceof Book) {%>
            <span>作者：<%= ((Book) p).getAutherName()%></span><br>
            <span>優惠價：<%= 100 - ((Book) p).getDiscount()%>折,</span> 
            <%}%>                            
            <span><%= p.getUnitPrice()%>元 
                <input id='quantity_<%= p.getId()%>' type="number" min='1' max='<%= p.getStock()%>'>
                <a href='javascript:addToCart(<%= p.getId()%>)'>
                    <img alt="add to cart" src='images/cart.png'>
                </a>
            </span>
            <% if (p.getColors() != null && p.getColors().size()>0) {%>
            <div>
                <label for='color'>顏色</label>                
                <select id='color__<%= p.getId()%>' name='color'>
                    <% for(String colorName:p.getColors()){%>
                    <option value='<%=colorName%>'><%=colorName%></option>
                    <%}%>
                </select>
            </div>        
            <%}%>
        </div>
        <%@include file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
