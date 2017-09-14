<%-- 
    Document   : UserAffair
    Created on : 2017-2-23, 15:53:15
    Author     : Administrator
--%>


<%@page import="java.util.List"%>
<%@page import="User.User.User"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>用户管理</title>
        <%
            User cond = (User)session.getAttribute("User");
            List<User> user_search = (List<User>)ServletActionContext.getRequest().getAttribute("User_Search");
            Integer managerid=cond.getUserid();
            String managername=cond.getUsername();
        %>
    </head>
    <body>
        <%=managerid%>
        <%=managername%>
        <div>
            <strong>用户管理</strong>
            <form accept-charset="utf-8" action="Search_Users" method="post">
                <select name="type">
                    <option value="userid">用户id</option>
                    <option value="username">姓名</option>
                    <option value="email">邮件</option>
                    <option value="mobile">电话</option>
                </select>&nbsp;
                <input type="text" name="search"/>&nbsp;
                <input type="hidden" name="from" value="manager"/>
                <input type="submit" value="Search"/>
            </form>
            <table>
                <thead>
                    <tr>
                        <td>用户id</td>
                        <td>姓名</td>
                        <td>邮件</td>
                        <td>电话</td>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if(user_search!=null)
                        {
                            int size = user_search.size();
                            for(int i=0;i<size;++i)
                            {
                    %>
                    <tr>
                        <td><%=user_search.get(i).getUserid()%></td>
                        <td><%=user_search.get(i).getUsername()%></td>
                        <td><%=user_search.get(i).getEmail()%></td>
                        <td><%=user_search.get(i).getMobile()%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
        <div>
            <form accept-charset="utf-8" action="Jump_Manager" method="post">
                <tr><td><input type="submit" value="返回管理页面"/>
            </form>  
            <form accept-charset="utf-8" action="Jump_Index" method="post">
                <tr><td><input type="submit" value="退出登录"/>
            </form>        
        </div>
    </body>
</html>
