<%-- 
Document   : login.jsp
Created on : 2017/2/20, 下午 05:00:06
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
        <style></style>
        <script>
            function refreshImage() {
                var myImg = document.getElementById("check_img");
                //圖片會送出請求!
                //讓字串不同才能改變圖片
                //new Date產生時間，加到字串裡每秒都可以改變一張
                myImg.src = "images/check.jpg?get=" + new Date();
            }
        </script>
    </head>   
    <body>
        <h1>會員登入</h1>
        <hr>        
        <a <%= request.getContextPath()%> >Home</a>
        <a href="<%= request.getContextPath()%>/register.jsp">會員註冊</a>
        <a href="<%= request.getContextPath()%>/login.jsp">會員登入</a>
        <a href="<%= request.getContextPath()%>/product_list.jsp">產品清單</a>
        <hr>
        <%
            List<String> errors = (List<String>) request.getAttribute("errors");
            if (errors != null && errors.size() > 0) {
                out.print(errors);
            }
        %>  
        <%    
            String auto = "";
            String uid = "";
            Cookie[] cookiesArray = request.getCookies();
            if (cookiesArray != null && cookiesArray.length > 0) {
                for (Cookie c : cookiesArray) {
                    if (c.getName().equals("uid")) {
                        uid = c.getValue();
                    }else if(c.getName().equals("auto")){
                        auto="checked";
                    }                     
                }
                 
            }
        %>    
        <form action="login.do" method="POST">
            <p>
                <label for="uid">會員帳號</label>
                <input type="text" name="userid" 
                       value="<%= request.getParameter("userid") == null ?uid:request.getParameter("userid")%>"
                       id="uid"><!--name->後端,id->前端-->
                <input type="checkbox" name="auto" id="auto" <%=auto%>><label for="auto">記住帳號</label>
            </p>
            <p>
                <label for="pwd">會員密碼</label>
                <input type="password" name="password" id="pwd"><!--name->後端,id->前端-->
            </p>
            <p>
                <a href="javascript:refreshImage()" title="點擊可更新圖片">
                    <img src="images/check.jpg" id="check_img">
                </a>

                <label for="check_code">驗證碼</label>
                <input type="text" name="checkCode" 
                       value="<%= request.getParameter("checkCode") == null ? "" : request.getParameter("checkCode")%>"
                       id="check_code"><!--name->後端,id->前端-->
            </p>
            <input type="submit" value="確定登入" name="submit">
        </form>    
    </body>
</html>