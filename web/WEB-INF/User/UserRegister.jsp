<%-- 
    Document   : UserRegister
    Created on : 2017-2-21, 12:19:21
    Author     : Administrator
--%>

<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>用户注册</title>
        <%
            String cond = ServletActionContext.getRequest().getParameter("cond");
        %>
    </head>
    <body>
        <div >
            <strong>注册账号</strong>
            <form role="form" accept-charset="utf-8" action="Register" method="post">
                <label for="username">
                    用户名
                </label>
                <input id="username" name="username" type="text" placeholder="Username"/><br>
                <label for="password">
                    密码
                </label>
                <input id="password" name="password" type="password" placeholder="Password"/><br>
                <label for="passwordd">
                    确认密码
                </label>
                <input id="passwordd" name="passwordd" type="password" placeholder="Enter Password Again"/><br>
                <label for="email">
                    电子邮箱
                </label>
                <input id="email" name="email" type="email" placeholder="Email"/><br>
                <label for="mobile">
                    手机
                </label>
                <input id="mobile" name="mobile" type="text" placeholder="Mobile Phone"/><br>
                <input type="submit" value="注册" name="Register"/>
            </form>
        </div>
    </body>
</html>
