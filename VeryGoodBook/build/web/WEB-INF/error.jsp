<%-- 
    Document   : erroe
    Created on : 2017/2/20, 下午 02:53:08
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
       
        <title>錯誤訊息</title>
        <style>
            body{
                /*background-image:url("404.jpg");*/
            }
        </style>
        <script>
            var s1 = "block";
            var s2 = "width:80%;display:blocked;font-size:60%;color:blue";
            function show_details(){
                var d = document.getElementById("details");
                try{
                    d.style.setAttribute("display", s1);               
                    if (s1=="none"){
                        s1 = "block";
                    }else{s1 = "none";}
                }catch(err){
                    d.setAttribute("style", (s2==null?"width:80%;display:none;":s2));               
                    if (s2 == null){
                        s2 = "width:80%;display:blocked;font-size:60%;color:blue";
                    }else{s2 = null;}                   
                }
            }
        </script>
    </head>
    <body>
         <p style='font-size:80%'>執行<span style='color:darkred'><%= request.getRequestURI()%></span>時發生下列錯誤：<br/>
            <% if (exception!=null){          
                out.println(exception.getClass().getName() + ":\t" +exception.getMessage());%>
                <a href="javascript:show_details()">details...</a><br/>
            <span id='details' style="width:60%;display:none;color:blue">
                <%
                exception.printStackTrace(new java.io.PrintWriter(out));
                %>
            </span>
            <% } %>          
        </p>
    </body>
</html>
