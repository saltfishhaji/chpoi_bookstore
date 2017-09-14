<%-- 
    Document   : Orders
    Created on : 2017-3-17, 16:11:44
    Author     : Administrator
--%>

<%@page import="Order.Order.Order" %>
<%@page import="ShoppingCart.ShoppingCart.ShoppingCart" %>
<%@page import="Book.Book.Book"%>
<%@page import="User.User.User"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>Order Main Page</title>
        <%
            //User user = (User)ServletActionContext.getRequest().getAttribute("User");
            User user2=(User)session.getAttribute("User");
            //int userid = 0;
            //String username = "";
            //if(user!=null)
            //{
            ///    userid = user.getUserid();
            //    username = user.getUsername();
            //}
            int userid2 = 0;
            String username2 = "";
            if(user2!=null)
            {
                userid2 = user2.getUserid();
                username2 = user2.getUsername();
            }
            //List<Book> books = (List<Book>)ServletActionContext.getRequest().getAttribute("Search_Result");
            System.out.println("hahahah");
            List<Order> Orders = (List<Order>)ServletActionContext.getRequest().getAttribute("My_Order");
            List<ShoppingCart> cart = (List<ShoppingCart>)ServletActionContext.getRequest().getAttribute("mycart");
        %>
    </head>
    <body>
        <%=userid2%>
        <%=username2%>
        <div>
            <strong>我的订单</strong>
            <table>
                <thead>
                    <tr>
                        <td>书名</td>
                        <td>数量</td>
                        <td>单价</td>
                        <td>时间</td>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if(Orders!=null)
                        {
                            for(int i=0;i<Orders.size();++i)
                            {
                    %>
                    <tr>
                        <td><%=Orders.get(i).getBookname()%></td>
                        <td><%=Orders.get(i).getAmount()%></td>
                        <td><%=Orders.get(i).getPrice()%></td>
                        <td><%=Orders.get(i).getTime()%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
        <div>
            <form accept-charset="utf-8" action="Jump_Home" method="post">
                            <tr><td><input type="submit" value="返回首页"/>
                        </form>  
            <form accept-charset="utf-8" action="Jump_Index" method="post">
                            <tr><td><input type="submit" value="退出登录"/>
                        </form>        
        </div>
    </body>
</html>
