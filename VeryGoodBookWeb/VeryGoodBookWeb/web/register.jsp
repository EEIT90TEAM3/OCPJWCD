<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>會員註冊</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

    </head>
    <body>
        <h1>非常好書 <sub>會員註冊</sub></h1>                
        <hr>
        <a href="<%= request.getContextPath()%>/">Home</a> | 
        <a href="<%= request.getContextPath()%>/register.jsp">會員註冊</a> | 
        <a href="<%= request.getContextPath()%>/login.jsp">會員登入</a> | 
        <a href="<%= request.getContextPath()%>/products_list.html">產品清單</a> | 
        <hr>
        <%
            List<String> errors = (List<String>)request.getAttribute("errors");
            if(errors!=null){
        %>
            <%= errors%>
        <%}%>
        <form action='register.do' method='POST'>
            <!--<div name='hello'>Hello</div>--> 
            <p>
                <label for='uid'>會員帳號:</label>
                <input type='text' name='userid' id='uid' placeholder="請輸入身分證號" required 
                       pattern='[A-Z][12][0-9]{8}'>
            </p>
            <p> 
                <label for='pwd1'>會員密碼:</label>
                <input type='password' name='password1' id='pwd1' required minlength="6" maxlength="20"><br>
                <label for='pwd2'>確認密碼:</label>
                <input type='password' name='password2' id='pwd2' required minlength="6" maxlength="20">
            </p>
            <p>
                <label for='name'>會員姓名:</label>
                <input type='text' name='name' id='name' required>
            </p>
            <p>
                <label for='email'>電子郵件:</label>
                <input type='email' name='email' id='email' required>           
            </p>
            <p>
                <label for='gender'>會員性別:</label>
                <input type='radio' name='gender' value='M' id='male' required><label for='male'>男</label>
                <input type='radio' name='gender' value='F' id='female' required><label for='female'>女</label>
            </p>

            <p>
                <label for='birthday'>出生日期:</label>
                <input type='date' name='birthday' id='birthday'>
            </p>
            <p>
                <label for='phone'>連絡電話:</label>
                <input type='tel' name='phone' id='phone'>
            </p>            
            <p>
                <label for='address'>連絡地址:</label>
                <input type='text' name='address' id='address'>
            </p>
            <p>
                <label for='married'>婚姻狀況:</label>
                <input type='checkbox' name='married' id='married'><label for='married'>已婚</label>
            </p>
            <p>
                <a href="javascript:refreshImage()"  title='點選即可更新圖片'>
                    <img src='images/register_check.jpg' id='check_img'>
                </a>
                <label for='check_code'>驗證碼:</label>
                <input type='text' name='checkCode' id='check_code' required>
                <script>
                    function refreshImage() {
                        var myImg = document.getElementById("check_img");
                        myImg.src = "images/register_check.jpg?get=" + new Date();
                    }
                </script>                  
            </p>
            <input type='submit' value='確定註冊' name='submit'>
        </form>        
    </body>
</html>
