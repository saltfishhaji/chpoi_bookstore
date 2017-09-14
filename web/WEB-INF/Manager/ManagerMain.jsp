<%-- 
    Document   : ManagerMain
    Created on : 2017-3-17, 16:13:44
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="User.User.User"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>管理员主界面</title>
        <%
            User cond = (User)session.getAttribute("User");
            Integer managerid=cond.getUserid();
            String managername=cond.getUsername();
        %>
    </head>
    <body>
        <%=managerid%>
        <%=managername%>
        <br>
        <strong>管理员选项</strong>
        <div>
            <ul>
                <a href="javascript:document.BookAffair.submit();">书本管理</a><br>
                <a href="javascript:document.UserAffair.submit();">查看用户</a>
            </ul>
        </div>
    </body>
    <footer>
        <form name="BookAffair" action="Jump_BookAffair" method="post"></form>
        <form name="UserAffair" action="Jump_UserAffair" method="post"></form>
    </footer>
    <div>  
            <form accept-charset="utf-8" action="Jump_Index" method="post">
                <tr><td><input type="submit" value="退出登录"/>
            </form>        
    </div>
</html>
