<%-- 
    Document   : global_js
    Created on : 2017/2/24, 下午 04:27:35
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="https://jqueryui.com/resources/demos/style.css">
<link href="<%= request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
<script src='https://code.jquery.com/jquery-1.12.4.js'></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    function logout() {
        $.ajax({
            url: '<%= request.getContextPath()%>/logout.do',
            method: 'POST'
        }).done(doneHandler);
    }
    function doneHandler(result) {
        alert("已登出成功!");
        if (location.href.indexOf("/member/") < 0) {
            $("#welcome-span").text("你好! ");
            $("#member-span").html('<a href="<%=request.getContextPath()%>/register.jsp">會員註冊</a> | ' +
                    '<a href="<%=request.getContextPath()%>/login.jsp">會員登入</a> | ');
        } else {
            alert(location.href.indexOf("/member/"));
            location.href = '<%= request.getContextPath()%>/';
        }
    }
</script>