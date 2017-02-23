<%-- 
    Document   : update
    Created on : 2017/2/23, 下午 02:27:57
    Author     : Administrator
--%>

<%@page import="com.verygoodbook.entity.Customer"%>
<%@page import="com.verygoodbook.entity.VIP"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //當url路徑符合:/member/*.*
    String url = request.getRequestURI();//???
    //在session檢查有無Customer或Customer子類別建立物件，且attribute:"member"
    Customer member = (Customer) session.getAttribute("member");
    if (member == null) {
        //若無已登入的會員，redirect到login.jsp強迫先登入後才能繼續執行
        return;
    }
    //若存在則繼續執行
    //否則redirect到login.jsp強迫先登入後才能繼續執行
%>    
<!DOCTYPE html>
<html>
    <head>
        <title>會員修改</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"><!--1:1的調整-->
    </head>
    <body>
        <h1>會員修改</h1>
        <hr>        
        <a href="/vgb/">Home</a>
        <a href="/vgb/register.html">會員註冊</a>
        <a href="/vgb/login.jsp">會員登入</a>
        <a href="/vgb/product_list.jsp">產品清單</a>
        <hr>
        <%
            List<String> errors = (List<String>) request.getAttribute("errors");
            if (errors != null) {
        %>
        <%= errors%>
        <%}%>
        <form action="updata.do" method="POST">
            <p>
                <label for="uid">會員帳號</label>
                <input type="text" name="userid" id="uid" placeholder="請輸入身分證字號"
                       value="<%= request.getParameter("userid") == null
                               ? member.getId() : request.getParameter("userid")%>"
                       required pattern="[A-Z][12][0-9]{8}">
                <% if (member instanceof VIP) {%>
                <input type="checkbox" name="vip" id="vip" disabled checked ><label for="vip">VIP</label>
                <label for="discount">享有折扣:</label>
                <input type="number" name="discount" id="discount" max="100" min="0" readonly disabled
                       value="<%= ((VIP) member).getDiscount()%>">
                <%}%>
            </p> 
            <p>
                <label for="pwd1">原來密碼</label>
                <input type="password" name="password" id="pwd1" required minlength="6" maxlength="20"> 
                <label for="pwd1">會員密碼</label>
                <input type="password" name="password1" id="pwd1" required minlength="6" maxlength="20"> 
                <label for="pwd2">確認密碼</label>
                <input type="password" name="password2" id="pwd2" required minlength="6" maxlength="20">
            </p>
            <p>
                <label for="name">會員姓名</label>
                <input type="text" name="name" 
                       value="<%= request.getParameter("name") == null ? member.getName(): request.getParameter("name")%>"
                       id="name">
            </p> 
            <p>
                <label for="email">電子郵件</label>
                <input type="email" name="email"
                       value="<%= request.getParameter("email") == null ? member.getEmail() : request.getParameter("email")%>"
                       id="email">
            </p>
            <p>
                <label for="gender">會員性別</label>
                <input type='radio' name='gender' value='M' id='male' required 
                       <%= request.getParameter("gender") == null
                               || (request.getParameter("gender").charAt(0) == Customer.FEMALE) ? "" : "checked"%>><label for='male'>男</label>
                <input type='radio' name='gender' value='F' id='female' required
                       <%= request.getParameter("gender") == null
                               || (request.getParameter("gender").charAt(0) == Customer.MALE) ? "" : "checked"%>
                       ><label for='female'>女</label>
            </p>
            <p>
                <label for="birthday">出生日期</label>
                <input type="date" name="birthday" id="birthday"
                       value="<%= request.getParameter("birthday") == null ? member.getBirthday() : request.getParameter("birthday")%>">

            </p>
            <p>
                <label for="phone">聯絡電話</label>
                <input type="tel" name="phone" id="phone"
                       value="<%= request.getParameter("phone") == null ? member.getPhone() : request.getParameter("phone")%>">
            </p>
            <p>
                <label for="address">聯絡地址</label>
                <input type="text" name="address" id="address"
                       value="<%= request.getParameter("address") == null
                               ? member.getAddress() : request.getParameter("address")%>">
            </p>
            <p>
                <label for='married'>婚姻狀況:</label>
                <input type='checkbox' name='married' id='married'
                       <%= request.getParameter("married") == null ? "" : "checked"%>>                
                <label for='married'>已婚</label>
            </p>
            <p>
                <!--發送圖片請求-->
                <a href="javascript:refreshImage()" title="點擊可更新圖片">
                    <!--src不用斜線-->
                    <img src="images/register_check.jpg" id="check_img">
                </a>

                <label for="check_code">驗證碼</label>
                <input type="text" name="checkCode" 
                       value="<%= request.getParameter("checkCode") == null ? "" : request.getParameter("checkCode")%>"
                       id="check_code">
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