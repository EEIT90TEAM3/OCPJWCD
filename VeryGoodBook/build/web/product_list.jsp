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
        list = service.getProdcutsByType("Book");
        search="";
    }
%>

<html>
    <head>
        <title>產品清單</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>非常好書<sub>產品清單</sub></h1>
        <hr>
        <a href="<%= request.getContextPath()%>/" >Home</a>
        <a href="<%= request.getContextPath()%>/product_list.jsp">產品清單</a> 
         <a href="<%= request.getContextPath()%>/cart.jsp">購物車</a> 
        <hr>
        <form method="POST" >
            <input type='search' placeholder="請輸入完整產品編號或部分名稱..." style='width:20em' 
                   name='search'>
            <input type="submit" value="查詢">
        </form> 
        <!--看看上面有沒有撈出結果-->
        <%--<%= list %>--%>
        <ul>            
            <% 
                if(list!=null && list.size()>0){
                for(Product p:list){ 
                    
            %>
            <li style='display: inline-block;border-style: dotted dashed solid double;padding: 1ex;box-shadow: 1px 1px 3px gray'>
                <img src='<%= p.getPhotoUrl() %>'>
                <h4><%= p.getName() %></h4>
                <% if(p instanceof Book){ %>
                    <span>作者：<%= ((Book)p).getAutherName() %></span><br>
                    <span>優惠價：<%= 100-((Book)p).getDiscount() %>折,<span>
                <% } %>
                <span><%= p.getUnitPrice() %>元</span>
            </li>
            <% }} %>

        </ul>

    </body>
</html>
