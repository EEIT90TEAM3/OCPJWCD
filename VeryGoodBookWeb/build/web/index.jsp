<%@page import="com.verygoodbook.entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
       <%
           request.setCharacterEncoding("UTF-8");
       %>
<html>
    <head>
        <title>Home</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--<meta http-equiv="refresh" content ="3; url=http://edu.uuu.com.tw">-->
    <%@include file="/WEB-INF/subviews/global_js.jsp" %>    
    </head>
    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp" />        
        <div id="article" style="height: 75vh;width:75%;margin:auto;">
            <p>session id: <%= session.getId() %></p>
            <img src="https://tse2.mm.bing.net/th?id=OIP.5cyw4YEtFwgG1MmgqBIKnQEsDA&pid=15.1&P=0&w=276&h=178"><br>
            <audio controls>
                <source src="http://www.w3schools.com/HTML/horse.ogg" type="audio/ogg">
                <source src="http://www.w3schools.com/HTML/horse.mp3" type="audio/mpeg">
                Your browser does not support the audio element.
            </audio>        
        </div>
        <%@include  file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
