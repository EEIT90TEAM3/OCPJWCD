<%-- 
    Document   : checkCodeBox
    Created on : 2017/3/3, 下午 04:09:29
    Author     : Administrator
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="imgSrc" required="true"%>
<%@attribute name="inputName" %>
<%-- any content can be specified here e.g.: --%>
<c:set var="inputName" value='${empty inputName?"checkCode":inputName}' />
<p>
    <a href="javascript:refreshImage()"  title='點選即可更新圖片'>
        <img src='${imgSrc}' id='check_img'>
    </a>
    <label for='check_code'>驗證碼:</label>
    <input type='text' name="${inputName}" id='check_code'            
           value="${empty param[inputName]?"": param[inputName]}"
           required>
    <script>
        function refreshImage() {
            var myImg = document.getElementById("check_img");
            myImg.src = "${imgSrc}?get=" + new Date();
        }
    </script>                
</p>