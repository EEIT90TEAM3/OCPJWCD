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
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="https://jqueryui.com/resources/demos/style.css">
        <%@include file="/WEB-INF/subviews/global_js.jsp" %>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $( function() {
                $( "#product_item" ).dialog({
                    autoOpen: false,width: 500, 
                    show: {effect: "blind",duration: 500},
                    hide: {effect: "blind",duration: 500}
                });
            });
            
            function getProduct(pid){
               //alert("查詢產品: No." + pid);
               //同步請求:
               <%--location.href="<%= request.getContextPath()%>/product_item.jsp?pid="+pid;--%>
               
               //非同步請求:
               $.ajax({
                   url: '<%= request.getContextPath()%>/product_ajax_item.jsp',
                   meth:'POST',
                   data:{"pid": pid}
               }).done(getProductDoneHandler);
            }
            
            function getProductDoneHandler(result){
                //alert(result);
                $("#product_item" ).html(result);
                $("#product_item" ).dialog("open");                
            }            
            
            function addProductToCart(pid){
                var quantity = $("#quantity_"+pid).val();
                if(!quantity){
                    quantity=1;
                }                
                var color = $("#thecolor_"+pid).val();
                //alert(color);                
                addToCart(pid, quantity, color)
            }
            
            function addToCart(pid, quantity, color){    
                if(!quantity) quantity = 1;               
                var urlPath = "<%= request.getContextPath()%>/add_cart.do?pid="+pid
                    +"&quantity=" + quantity + (color==undefined?'':"&color="+color);  
                alert("加入購物車: " + urlPath);
                //同步請求:
                location.href=urlPath;                
            }
            
        </script>
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
                <a href='javascript:getProduct(<%= p.getId()%>)'>
                    <img style='width:200px' src='<%= p.getPhotoUrl() %>'>
                </a>                
                <h4>
<%--                    <span style="font-size: smaller">
                        No.<%= String.format("%05d", p.getId()) %>
                    </span>--%> <%= p.getName() %></h4>
                <div>
                <% if(p instanceof Book) {%>
                    <span>作者：<%= ((Book)p).getAutherName() %></span><br>
                    <%  
                        int discount = (100 - ((Book) p).getDiscount());
                        if (discount % 10 == 0) discount /= 10;
                    %>
                    <span>優惠價：<%= discount %>折,</span> 
                <%}%>                            
                <span><%= p.getUnitPrice() %>元</span>
                <span>
                    <a href='javascript:addToCart(<%= p.getId()%>)'>
                        <img alt="add to cart" src='images/cart.png'>
                    </a>
                </span>
                </div>                
            </li>            
            <%}} %>
        </ul>
        </div>
        <div id="product_item" title="產品明細"></div>        
        <%@include file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
