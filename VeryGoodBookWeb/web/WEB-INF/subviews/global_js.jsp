<%-- 
    Document   : global_js
    Created on : 2017/2/24, 下午 04:27:35
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src='https://code.jquery.com/jquery-1.12.4.js'></script>
<script>
    function logout() {
        $.ajax({
            url: '<%= request.getContextPath()%>/logout.do',
            method: 'POST'
        }).done(doneHandler);
    }
    function doneHandler(result) {
        alert("已登出成功!");
        $("#welcome-span").text("你好! ");
        $("#member-span").html('<a href="<%=request.getContextPath()%>/register.jsp">會員註冊</a> | ' +
                '<a href="<%=request.getContextPath()%>/login.jsp">會員登入</a> | ');
    }
</script>