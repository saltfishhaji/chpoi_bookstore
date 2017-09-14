<%-- 
    Document   : UserLogin
    Created on : 2017-2-22, 11:39:37
    Author     : Administrator
--%>

<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登录界面</title>
        <%
            String cond = ServletActionContext.getRequest().getParameter("role");
        %>
    </head>
    <body>
        <div>
            <strong>登录</strong>
            <%
                if(cond!=null&&cond.equals("manager"))
                {
            %>
            <form accept-charset="utf-8" name="j_security_check" action="Manager_Login" method="post">
                <label for="username">用户名</label>
                <input type="text" name="j_username" id="username" placeholder="用户名"/><br>
                <label for="password">密码</label>
                <input type="password" name="j_password" id="password" placeholder="密码"/><br>
                <input type="submit" value="登录"/>
            </form>
            <%
                }
                else
                {
            %>
            <form accept-charset="utf-8" action="User_Login" method="post">
                <label for="username">用户名</label>
                <input type="text" name="username" id="username" placeholder="用户名"/><br>
                <label for="password">密码</label>
                <input type="password" name="password" id="password" placeholder="密码"/><br>
                <input type="submit" value="登录"/>
            </form>
              <%
                  }
             %>
        </div>
    </body>
</html>
