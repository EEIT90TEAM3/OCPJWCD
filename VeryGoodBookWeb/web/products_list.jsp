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
    String[] types = request.getParameterValues("type");
    
    ProductService service = new ProductService();
    List<Product> list = null;    
    if(search!=null){
        list = service.getProductsByName(search);
    }else if(types!=null && types.length >0){        
        list = service.getProductsByType(types);        
    }else{
        list = service.getProductsByType("Book");        
    }    
%>
<html>
    <head>
        <title>產品清單</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@include file="/WEB-INF/subviews/global_js.jsp" %>        
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
            
            function addProductToCart(pid, color_selecter){
                var quantity = $("#quantity_"+pid).val();
                if(!quantity){
                    quantity=1;
                }                                
                var color;
                if(color_selecter){   
                    color= $("#"+color_selecter+pid).val();
                    alert(color);           
                }
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
        <div id="aside" style="float:left">
            <a href="<%=request.getRequestURI()%>?type=Book">我要買書</a><br>
            <a href="<%=request.getRequestURI()%>?type=Pen&type=Product">文具/其他</a>
        </div>
        <div id="article" style="float:left;">
        <form method="POST" action='products_list.jsp'>
            <label>查詢: </label>
            <input type='search' placeholder="請輸入完整產品編號或部分名稱..." style='width:20em' 
                   name='search' value="${param.search}">
            <input type='submit' value="查詢">                
        </form>
        <hr>
        <%-- = list --%>
        <ul>
            <% 
            if(list!=null && list.size()>0){
                for(Product p:list) {                    
            %>
            <li style='display:inline-block;width:15em;border: solid lightblue 1px;padding: 1ex;box-shadow: 1px 1px 3px gray'>
                <a href='javascript:getProduct(<%= p.getId()%>)'>
                    <img style='width:200px' src='<%= p.getPhotoUrl() %>'>
                </a>                
                <h5>
<%--                    <span style="font-size: smaller">
                                No.<%= String.format("%05d", p.getId()) %></span>--%> 
                    <%= p.getName() %></h5>
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
                <% if (p.getColors() != null && p.getColors().size() > 0) {%>
                <span>
                    <label for='thiscolor_<%= p.getId()%>'>顏色:</label>                
                    <select id='thiscolor_<%= p.getId()%>' name='color' required>
                        <option value=''>請選擇...</option>
                        <%  for (String colorName : p.getColors()) { %>
                        <option value='<%=colorName%>'><%=colorName%></option>
                        <%}%>
                    </select> 
                </span>
                <%}%>                
                <span>
                    <a href='javascript:addProductToCart(<%= p.getId()%>, "thiscolor_")'>
                        <img style="padding-top: 1ex" alt="add to cart" src='images/cart.png'>
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
