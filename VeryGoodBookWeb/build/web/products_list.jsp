<%@page import="com.verygoodbook.entity.Book"%>
<%@page import="com.verygoodbook.entity.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.verygoodbook.service.ProductService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%
    request.setCharacterEncoding("UTF-8");
    String search = request.getParameter("search");
    ProductService service = new ProductService();
    List<Product> list = null;    
    if(search!=null){
        list = service.getProductsByName(search);
    }else{
        list = service.getProductsByType("Book");
        search="";
    }    
%>
<html>
    <head>
        <title>產品清單</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@include file="WEB-INF/subviews/global_js.jsp" %>
    </head>
    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp" >
            <jsp:param name="subtitle" value="產品清單"/>
        </jsp:include>
        <div id="article">
        <form method="POST" action='products_list.jsp'>
            <input type='search' placeholder="請輸入完整產品編號或部分名稱..." style='width:20em' 
                   name='search' value="<%= search %>">
            <input type='submit' value="查詢">                
        </form>
        <hr>
        <%-- = list --%>
        <ul>
            <% 
            if(list!=null && list.size()>0){
                for(Product p:list) {                    
            %>
            <li style='width:300px;display: inline-block;border-style: dotted dashed solid double;padding: 1ex;box-shadow: 1px 1px 3px gray'>
                <a href='#'><img style='width:200px' src='<%= p.getPhotoUrl() %>'></a>
                <h4><%= p.getName() %></h4>
                <div>
                <% if(p instanceof Book) {%>
                    <span>作者：<%= ((Book)p).getAutherName() %></span><br>
                    <span>優惠價：<%= 100-((Book)p).getDiscount() %>折,<span> 
                <%}%>                            
                <span><%= p.getUnitPrice() %>元 <a href='#'><img alt="add to cart" src='images/cart.png'></a></span>
                </div>                
            </li>
            <%}} %>
        </ul>
        </div>
        <%@include file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
