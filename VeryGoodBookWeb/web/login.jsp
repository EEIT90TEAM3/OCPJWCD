<%-- 
    Document   : login
    Created on : 2017/2/20, 下午 04:59:30
    Author     : Administrator
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>會員登入</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@include file="WEB-INF/subviews/global_js.jsp" %>  
        <script>
            function refreshImage(){
                var myImg = document.getElementById("check_img");
                myImg.src = "images/check.jpg?get=" + new Date();
            }
        </script>
    </head>
    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp" >
            <jsp:param name="subtitle" value="會員登入"/>
        </jsp:include>
        <div id="article">
        <%
            List<String> errors = (List<String>)request.getAttribute("errors");
            if(errors!=null && errors.size()>0){
        %>
        <ul>
            <% for(int i=0;i<errors.size();i++) { %>
            <li><%= errors.get(i) %></li>
            <% } %>
        </ul>
        <%}%>
        <%--
            //以下為讀取Cookie的示範
            String auto = "";
            String uid =  "";
            
            Cookie[] cookiesArray = request.getCookies();
            if(cookiesArray!=null && cookiesArray.length>0){
                for(Cookie c: cookiesArray){
                    if(c.getName().equals("uid")){
                      uid = c.getValue();                              
                    }else if(c.getName().equals("auto")){
                        auto = "checked";
                    }
                }
            }//以上為讀取Cookie的示範
        --%>
        <form action='login.do' method='POST'>
            <!--<div name='hello'>Hello</div>--> 
            <p>
                <label for='uid'>會員帳號:</label>
                <input type='text' name='userid' 
                       value="${empty param.userid?cookie.uid.value:param.userid}" 
                       id='uid' required>
                <input type="checkbox" name="auto" id="auto" ${cookie.auto.value}><label for="auto">記住帳號</label>
            </p>
            <p>
                <label for='pwd'>會員密碼:</label>
                <input type='password' name='password' id='pwd' required>
            </p>
            <p>
                <a href="javascript:refreshImage()"  title='點選即可更新圖片'>
                    <img src='images/check.jpg' id='check_img'>
                </a>
                <label for='check_code'>驗證碼:</label>
                <input type='text' name='checkCode' id='check_code' 
                       value="<%= request.getParameter("checkCode")==null?"":request.getParameter("checkCode") %>"
                       required>
            </p>
            <input type='submit' value='確定登入'>
        </form>
        </div>
        <%@include file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
