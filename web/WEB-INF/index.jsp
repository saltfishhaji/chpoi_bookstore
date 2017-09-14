<%-- 
    Document   : index
    Created on : 2017-3-10, 12:12:40
    Author     : Administrator
--%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="User.User.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String base = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    User user = (User)request.getSession().getAttribute("User");
    String language=(String)request.getSession().getAttribute("language");
    //String logintime=(String)request.getSession().getAttribute("logintime");
    String userid = "";
    String bookstore="";
    String register="";
    String manager="";
    String login="";
    String cart="";
    String order="";
    String chatroom="";
    //if(logintime!=null)
    //    logintime="登录时间：  "+logintime;
    if(user != null)
        userid = user.getUserid().toString();
    if(language==null)
    {
        ResourceBundle bundle = ResourceBundle.getBundle("International/Bundle", request.getLocale());
        bookstore = bundle.getString("bookstore");
        register = bundle.getString("register");
        manager=bundle.getString("manager");
        login=bundle.getString("login");
        cart=bundle.getString("cart");
        order=bundle.getString("order");
        chatroom=bundle.getString("chatroom");
    }
    else if(language=="US")
    {
        Locale locale = Locale.US;
        ResourceBundle bundle = ResourceBundle.getBundle("International/Bundle", locale);//request.getLocale()
        bookstore = bundle.getString("bookstore");
        register = bundle.getString("register");
        manager=bundle.getString("manager");
        login=bundle.getString("login");
        cart=bundle.getString("cart");
        order=bundle.getString("order");
        chatroom=bundle.getString("chatroom");
    }
    else if(language=="ZN")
    {
        Locale locale = Locale.CHINESE;
        ResourceBundle bundle = ResourceBundle.getBundle("International/Bundle", locale);//request.getLocale()
        bookstore = bundle.getString("bookstore");
        register = bundle.getString("register");
        manager=bundle.getString("manager");
        login=bundle.getString("login");
        cart=bundle.getString("cart");
        order=bundle.getString("order");
        chatroom=bundle.getString("chatroom");
    }
%>
<html>
    <head>
        <base href="<%=base%>"/>
        <meta http-equiv="Content-Type" content="text/html"charset="UTF-8">
    </head>

    <body>
        <%
            if(user != null)
            {
        %>
        <%=user.getUserid()%>
        <%=user.getUsername()%>
        <%
            }
        %>
        <div>
            
            <strong><%=bookstore%></strong>&nbsp;&nbsp;
            <a href="javascript:document.English.submit();">English</a>&nbsp;&nbsp;
            <a href="javascript:document.Chinese.submit();">中文</a><br>
            <ul>
                <a href="javascript:document.UserRegister.submit();"><%=register%></a><br>
                <a href="Jump_UserLogin?role=manager"><%=manager%></a><br>
                <a href="javascript:document.UserLogin.submit();"><%=login%></a><br>
                <a href="javascript:document.Books.submit();"><%=bookstore%></a><br>
                <a href="javascript:document.ShoppingCart.submit();"><%=cart%></a><br>
                <a href="javascript:document.Order.submit();"><%=order%></a><br>
                <a href="javascript:document.Chatroom.submit();"><%=chatroom%></a>
                <!--a href="javascript:document.Test.submit();">test</a-->
            </ul>
        </div>
    </body>
    <footer>
        <form action="Jump_UserLogin" method="post" name="UserLogin"></form>
        <form action="Jump_UserRegister" method="post" name="UserRegister"></form>
        <form action="Jump_Books" method="post" name="Books">
            <input type="hidden" name="userid" value="<%=userid%>"/>
        </form>
        <form action="Set_English" method="post" name="English"></form>
        <form action="Set_Chinese" method="post" name="Chinese"></form>
        <form action="Jump_ShoppingCart" method="post" name="ShoppingCart"></form>
        <form action="Jump_Order" method="post" name="Order"></form>
        <form action="Jump_Chatroom" method="post" name="Chatroom"></form>
        <!--form action="Jump_Test" method="post" name="Test"></form-->
    </footer>
</html>
