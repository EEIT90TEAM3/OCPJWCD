<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <div id="footer">
            <hr>
            版權所有 &copy; 非常好書 2015~<%= Calendar.getInstance().get(Calendar.YEAR) %> 
            <span style="float:right">
                拜訪人次: ${ applicationScope["app.visitors.counter"]}
            </span>
        </div>
