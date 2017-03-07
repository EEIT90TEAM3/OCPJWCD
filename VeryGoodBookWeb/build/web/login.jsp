<%-- 
    Document   : login
    Created on : 2017/2/20, 下午 04:59:30
    Author     : Administrator
--%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  tagdir="/WEB-INF/tags" prefix="vgb" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" >會員登入</c:set>
<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@include file="/WEB-INF/subviews/global_js.jsp" %>  
    </head>
    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp" >
            <jsp:param name="subtitle" value="${title}"/>
        </jsp:include>
        <div id="article">
            <c:if test="${not empty requestScope.errors}">
            <ul>                
                <c:forEach items="${requestScope.errors}" var="msg"> 
                <li>${msg}</li>                
                </c:forEach>
            </ul>
            </c:if>
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
            <form action='<c:url value="/login.do"/>' method='POST'>
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
                <vgb:checkCodeBox imgSrc="images/check.jpg"  />
                <input type='submit' value='確定登入'>
            </form>
        </div>
        <%@include file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
