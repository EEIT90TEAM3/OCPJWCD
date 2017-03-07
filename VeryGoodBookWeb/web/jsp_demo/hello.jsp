<%@page import="java.util.Date" info='JSP示範程式'%>
<%@page import="java.text.DateFormat"%>
<%@page buffer="3kb" autoFlush="true"%>
<%@page contentType="text/html"  pageEncoding="UTF-8" errorPage="/WEB-INF/error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title><%= this.getServletInfo() %></title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    String welcome = "你好";
    DateFormat df;
%>
<h1><%= welcome %>，HelloServlet at /vgb</h1>
<p>時間: <%  out.print(new Date());       %></p>
<hr>
<h2>Implicit Variables</h2>
<h3>request</h3>
<p> Context Path: <%= request.getContextPath() %>  </p>
<p> RequestURI: <%= request.getRequestURI() %>  </p>
<p> RequestURL: <%= request.getRequestURL() %>  </p>
<p> Method: <%= request.getMethod() %>  </p>
<p> Header('user-agent'): <%= request.getHeader("user-agent") %> </p>
<p> Parameter('tesr'): <%= request.getParameter("test") %> </p>
<form method='POST'>
    <fieldset>
    <input name='test'><br>
    <input type='submit'>
    </fieldset>
</form>
<hr>
<h3>response</h3>
<p>status code:  <%= response.getStatus() %> </p>    
<p>contentType:  <%= response.getContentType() %> </p>
<p>buffer size:  <%= response.getBufferSize() %> </p>
<hr>
<h3>out</h3>
<p>Math.round(4.5):  <%= Math.round(4.5) %> </p>
<p>println:  <% out.println("test"); %> </p>
<hr>
<h3>exception</h3>
<%--<p> <%= exception %> </p>--%>
<hr><hr>
<h2>Implicit Variables</h2>
<h3>request</h3>
<% 
    out.flush(); 
    Thread.sleep(1000);
%>
<p> Context Path: <%= request.getContextPath() %>  </p>
<p> RequestURI: <%= request.getRequestURI() %>  </p>
<p> RequestURL: <%= request.getRequestURL() %>  </p>
<p> Method: <%= request.getMethod() %>  </p>
<p> Header('user-agent'): <%= request.getHeader("user-agent") %> </p>
<p> Parameter('tesr'): <%= request.getParameter("test") %> </p>

<form method='POST'>
    <fieldset>
    <input name='test'><br>
    <input type='submit'>
    </fieldset>
</form>
<hr>
<h3>response</h3>
<p>status code:  <%= response.getStatus() %> </p>    
<p>contentType:  <%= response.getContentType() %> </p>
<p>buffer size:  <%= response.getBufferSize() %> </p>
<hr>
<h3>out</h3>
<p>Math.round(4.5):  <%= Math.round(4.5) %> </p>
<p>println:  <% out.println("test"); %> </p>
<hr>
</body>
</html>
