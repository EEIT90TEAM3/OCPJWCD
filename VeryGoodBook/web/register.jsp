<%@page import="java.util.List"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>會員註冊</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"><!--1:1的調整-->
    </head>
    <body>
        <h1>會員註冊</h1>
        <hr>        
         <a href="/vgb/">Home</a>
        <a href="/vgb/register.html">會員註冊</a>
        <a href="/vgb/login.jsp">會員登入</a>
        <a href="/vgb/product_list.jsp">產品清單</a>
        <hr>
        <%
            List<String> errors = (List<String>)request.getAttribute("errors");
            if(errors!=null){
        %>
            <%= errors%>
        <%}%>
        <form action="register.do" method="POST">
            <p>
                <label for="uid">會員帳號</label>
                <input type="text" name="userid" id="uid" placeholder="請輸入身分證字號"
                       required pattern="[A-Z][12][0-9]{8}">
            </p> 
            <p>
                <label for="pwd1">會員密碼</label>
                <input type="password" name="password1" id="pwd1" required minlength="6" maxlength="20"> 
                <label for="pwd2">確認密碼</label>
                <input type="password" name="password2" id="pwd2" required minlength="6" maxlength="20">
            </p>
            <p>
                <label for="name">會員姓名</label>
                <input type="text" name="name" id="name">
            </p> 
            <p>
                <label for="email">電子郵件</label>
                <input type="email" name="email" id="email">
            </p>
            <p>
                <label for="gender">會員性別</label>
                <input type="radio" name="gender" id="male" value="M" checked> 男
                <input type="radio" name="gender" id="female" value="F">女<br>
                <!--<select name='gender' id='gender'>
                    <option value=''></option>
                    <option value='M'>男</option>
                    <option value='F'>女</option>
                </select>-->
            </p>
            <p>
                <label for="birthday">出生日期</label>
                <input type="date" name="birthday" id="birthday">
                <!--測試用，自己的想法-->
                <!--                <input type="text" name="birthday" id="birthday" placeholder="請參照yyyy-MM-dd" 
                                       required pattern="[1-2][0-9]{3}[-][0-1][0-9][-][0-3][0-9]">-->
            </p>
            <p>
                <label for="phone">聯絡電話</label>
                <input type="tel" name="phone" id="phone">
            </p>
            <p>
                <label for="address">聯絡地址</label>
                <input type="text" name="address" id="address">
            </p>
            <p>
                <label for='married'>婚姻狀況:</label>
                <input type='checkbox' name='married' id='married'><label for='married'>已婚</label>
            </p>
            <p>
                <!--發送圖片請求-->
                <a href="javascript:refreshImage()" title="點擊可更新圖片">
                    <!--src不用斜線-->
                    <img src="images/register_check.jpg" id="check_img">
                </a>

                <label for="check_code">驗證碼</label>
                <input type="text" name="checkCode" id="check_code">
                <script>
                    function refreshImage() {
                        var myImg = document.getElementById("check_img");

                        myImg.src = "images/register_check.jpg?get=" + new Date();
                    }
                </script>
            </p>    
            <input type="submit" value="確定註冊" name="submit">
        </form>    
    </body>
</html>
