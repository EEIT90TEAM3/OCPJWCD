<%-- 
    Document   : product_item
    Created on : 2017/3/1, 下午 03:44:18
    Author     : Administrator
--%>

<%@page import="com.verygoodbook.entity.Product"%>
<%@page import="com.verygoodbook.service.ProductService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>產品明細</title>
        <%@include file="WEB-INF/subviews/global_js.jsp" %>
    </head>
    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp" >
            <jsp:param name="subtitle" value="產品明細"/>
        </jsp:include>
        <div id="article">
            <%
                String pid = request.getParameter("pid");
                int id = 1;
                if(pid!=null && pid.matches("\\d+")){
                    id = Integer.parseInt(pid);
                }
                ProductService service = new ProductService();
                Product p = service.get(id);           
            %>
            
            <%=p %>
        </div>
        <%@include file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
