<%-- 
    Document   : Carts
    Created on : 2017-3-17, 16:10:44
    Author     : Administrator
--%>

<%@page import="ShoppingCart.ShoppingCart.ShoppingCart" %>
<%@page import="Book.Book.Book"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="User.User.User"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="java.util.List"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>Cart Main Page</title>
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
            System.out.println("hehehehe");
            List<ShoppingCart> ShoppingCarts = (List<ShoppingCart>)ServletActionContext.getRequest().getAttribute("My_Cart");
            SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<ShoppingCart> cart = (List<ShoppingCart>)ServletActionContext.getRequest().getAttribute("mycart"); %>
    </head>
    <body>
        <%=userid2%>
        <%=username2%>
        <div>
            <strong>我的购物车</strong>
            <table>
                <thead>
                    <tr>
                        <td>书名</td>
                        <td>数量</td>
                        <td>单价</td>
                        <td>下单</td>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if(ShoppingCarts!=null)
                        {
                            for(int i=0;i<ShoppingCarts.size();++i)
                            {
                    %>
                    <tr style="text-align: center">
                        <form accept-charset="utf-8" action="update_Order" method="post">
                            <tr>
                                <td><%=ShoppingCarts.get(i).getBookname()%></td>
                                <td><%=ShoppingCarts.get(i).getAmount()%></td>
                                <td><%=ShoppingCarts.get(i).getPrice()%></td>
                                <td><input type='hidden' name='cartid' value=<%=ShoppingCarts.get(i).getCartid()%> />
                            <input type='hidden' name='bookid' value=<%=ShoppingCarts.get(i).getBookid()%> />
                            <input type='hidden' name='bookname' value="<%=ShoppingCarts.get(i).getBookname()%>" />
                            <input type='hidden' name='price' value=<%=ShoppingCarts.get(i).getPrice()%> />
                            <input type='hidden' name='amount' value=<%=ShoppingCarts.get(i).getAmount()%> />
                            <input type="submit" value="add"/>   
                            </td>
                            </tr>
                        </form>    
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
