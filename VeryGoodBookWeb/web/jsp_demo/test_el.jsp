<%-- 
    Document   : test_el
    Created on : 2017/2/24, 下午 05:25:54
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EL Demo</title>
    </head>
    <body>
        <h1>EL Operators</h1>
        <p>2+3: <%= 2+3 %></p>
        <p>2+3: ${ 2+3 }</p>

        <p>'2' + '3': <%= "2.1" + '3' %></p>
        <p>'a' + 'b': ${ '2.1' < '3' }</p>
        <p>2<1 && 2>3: <%= '2'<'1' && '2'>3 %></p>
        <p>'2' > '1' && '2.5' < '3': ${'2' > '1' && '2.5' < '3'}</p>
        <p>'張': <%= '張' %></p>
        <p>'張': ${'張'}</p>
        
        <p>2<3?'very'+'good':'bad': ${2<3?'good':'bad'}</p>
        
        <hr>
        <h1>EL Implicit Variable- param</h1>
        <p>param.name: <%= request.getParameter("name")==null?"":request.getParameter("name") %></p>
        <p>param.name: ${param.name}</p>
        <p>param["name"]: ${param["name"]}</p>
        <hr>
        <h1>EL Implicit Variable- header</h1>
        <p>user-agent: <%= request.getHeader("user-agent") %></p>
        <p>user-agent: ${header["user-agent"]}</p>        
        <hr>
        <h1>EL Implicit Variable- pageContext</h1>
        <p>param.name: <%= pageContext.getRequest().getParameter("name")==null?"":request.getParameter("name") %></p>
        <p>Context Path: <%= ((HttpServletRequest)pageContext.getRequest()).getContextPath() %></p>
        <p>Context Path: ${ pageContext.request.contextPath }</p>
        <p>Session ID: <%= session.getId() %></p>
        <p>Session ID: ${ pageContext.session.id }</p>
        <hr>
    </body>
</html>
