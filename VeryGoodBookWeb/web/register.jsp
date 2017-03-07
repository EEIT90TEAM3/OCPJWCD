<%@page import="com.verygoodbook.entity.Customer" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <%@include file="WEB-INF/subviews/global_js.jsp" %>    
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script src="https://jqueryui.com/resources/demos/datepicker/i18n/datepicker-zh-TW.js"></script>
        <script>
            <% if (request.getHeader("user-agent").indexOf("Chrome") < 0) { %>
            $(function () {
                $("#birthday").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: "yy-mm-dd"
                });
            });
            <% } %>
        </script>        
    </head>
    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp" >
            <jsp:param name="subtitle" value="會員註冊"/>
        </jsp:include>
        <div id="article">
            <%
                List<String> errors = (List<String>) request.getAttribute("errors");
                if (errors != null) {
            %>
            <ul>
            <%for(String msg:errors){%>
            <li><%=msg%></li>
            <%}%>
            </ul>
            <%}%>
            <form action='register.do' method='POST'>
                <!--<div name='hello'>Hello</div>--> 
                <p>
                    <label for='uid'>會員帳號:</label>
                    <input type='text' name='userid' id='uid' placeholder="請輸入身分證號" required 
                           value="${param.userid}" pattern='[A-Z][12][0-9]{8}'>
                </p>
                <p> 
                    <label for='pwd1'>會員密碼:</label>
                    <input type='password' name='password1' id='pwd1' required minlength="6" maxlength="20"><br>
                    <label for='pwd2'>確認密碼:</label>
                    <input type='password' name='password2' id='pwd2' required minlength="6" maxlength="20">
                </p>
                <p>
                    <label for='name'>會員姓名:</label>
                    <input type='text' name='name' id='name' required value="${param.name}">
                </p>
                <p>
                    <label for='email'>電子郵件:</label>
                    <input type='email' name='email' id='email' required
                           value="<%= request.getParameter("email") == null ? "" : request.getParameter("email")%>">           
                </p>
                <p>
                    <label for='gender'>會員性別:</label>
                    <input type='radio' name='gender' value='M' id='male' required 
                           <%= request.getParameter("gender") == null
                                   || (request.getParameter("gender").charAt(0) == Customer.FEMALE) ? "" : "checked"%>><label for='male'>男</label>
                    <input type='radio' name='gender' value='F' id='female' required
                           <%= request.getParameter("gender") == null
                                   || (request.getParameter("gender").charAt(0) == Customer.MALE) ? "" : "checked"%>
                           ><label for='female'>女</label>
                </p>

                <p>
                    <label for='birthday'>出生日期:</label>
                    <input type='date' name='birthday' id='birthday'
                           value="<%= request.getParameter("birthday") == null ? "" : request.getParameter("birthday")%>">
                </p>
                <p>
                    <label for='phone'>連絡電話:</label>
                    <input type='tel' name='phone' id='phone'
                           value="<%= request.getParameter("phone") == null ? "" : request.getParameter("phone")%>">
                </p>            
                <p>
                    <label for='address'>連絡地址:</label>
                    <input type='text' name='address' id='address'
                           value="<%= request.getParameter("address") == null ? "" : request.getParameter("address")%>">
                </p>
                <p>
                    <label for='married'>婚姻狀況:</label>
                    <input type='checkbox' name='married' id='married' 
                           <%= request.getParameter("married") == null ? "" : "checked"%>><label for='married'>已婚</label>
                </p>
                <p>
                    <a href="javascript:refreshImage()"  title='點選即可更新圖片'>
                        <img src='images/register_check.jpg' id='check_img'>
                    </a>
                    <label for='check_code'>驗證碼:</label>
                    <input type='text' name='checkCode' id='check_code' required
                           value="<%= request.getParameter("checkCode") == null ? "" : request.getParameter("checkCode")%>">
                    <script>
                        function refreshImage() {
                            var myImg = document.getElementById("check_img");
                            myImg.src = "images/register_check.jpg?get=" + new Date();
                        }
                    </script>                  
                </p>
                <input type='submit' value='確定註冊' name='submit'>
            </form>   
        </div>
        <%@include  file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
