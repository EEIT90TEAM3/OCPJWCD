<%-- 
    Document   : index
    Created on : 2017/2/21, 下午 12:10:00
    Author     : Administrator
--%>

<%@page import="com.verygoodbook.entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>首頁</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src='https://code.jquery.com/jquery-1.12.4.js'></script>
        <script>
            function  logout() {
                $.ajax({
                    url: "<%= request.getContextPath()%>/logout.do",
                    method: "POST"
                }).done(doneHandler);
            }
            function doneHandler(result) {
                alert("已登出成功!");
                $("#member-span").text("您好!");
                $("#member-span").html('<a href="<%= request.getContextPath()%>/register.jsp">會員註冊</a>|' +
                        ' <a href="<%= request.getContextPath()%>/login.jsp">會員登入</a>|');
            }
        </script>
    </head>
    <body>
        <div id="header">
            <h1>非常好書</h1>
            <hr>
        </div>
        <div id="nav">
            <a <%= request.getContextPath()%> >Home</a>
            <a href="<%= request.getContextPath()%>/product_list.jsp">產品清單</a> 
            <a href="<%= request.getContextPath()%>/cart.jsp">購物車</a> 
            <span id="member-span">
                <%
                    Customer member = (Customer) session.getAttribute("member");
                    if (member == null) {
                %>
                <a href="<%= request.getContextPath()%>/register.jsp">會員註冊</a>|
                <a href="<%= request.getContextPath()%>/login.jsp">會員登入</a>|
                <% } else {%>
                <a href="<%= request.getContextPath()%>/member/update.jsp">會員修改</a>|
                <a href="<%= request.getContextPath()%>/member/order_history.jsp">歷史訂單</a>|
                <a href="<%= request.getContextPath()%>/logout.do">會員登出</a>|
                <a href="javascript:logout()">會員登出(ajax)</a>
                <% }%>
            </span>
            <span style="font-size: smaller;float:right" id="welcome-span">
                您好!<%= (member != null) ? member.getName() : ""%>
            </span>
            <hr>
        </div>

        <!--<img src="http://www.santabarbararen.com/wp-content/uploads/2016/08/2.jpg">-->
        <div id="article" style="height: 75vh">
            <img src="https://tse2.mm.bing.net/th?id=OIP.5cyw4YEtFwgG1MmgqBIKnQEsDA&pid=15.1&P=0&w=276&h=178"><br>
            <audio controls>

            </audio>
        </div>
        <div id="footer">
            <hr>
            <%
               //以下為拜訪人次之灌水程式 
//               Integer counter= (Integer)application.getAttribute("app.vistiors.counter");
//               if(counter==null){
//                   counter=16800;
//               }
//               application.setAttribute("app.vistiors.counter",++counter);
               //以上為拜訪人次之灌水程式
            %>
           版權所有 &copy; 非常好書<span>拜訪人次:<%= application.getAttribute("app.vistiors.counter") %></span>
        </div>
    </body>
</html>

