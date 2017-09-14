<%-- 
    Document   : BookAffair
    Created on : 2017-2-22, 19:53:15
    Author     : Administrator
--%>


<%@page import="java.util.List"%>
<%@page import="Book.Book.Book"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>书本管理</title>
        <%
            String cond = ServletActionContext.getRequest().getParameter("User");
            List<Book> book_search = (List<Book>)ServletActionContext.getRequest().getAttribute("Book_Search");
        %>
    </head>
    <body>
        
        <div>
            <table>
                <tbody>
                    <form accept-charset="utf-8" action="Add_Book" method="post">
                        <strong><tr>书本管理</tr></strong>
                        <tr>
                            <td>书名</td>
                            <td><input type="text" name="bookname" id="bookname"/></td><br>
                        </tr>
                        <tr>
                            <td>作者</td>
                            <td><input type="text" name="author" id="author"/></td><br>
                        </tr>
                        <tr>
                            <td>价格</td>
                            <td><input type="text" name="price" id="price"/></td><br>
                        </tr>
                        <tr>
                            <td>类别</td>
                            <td><input type="text" name="category" id="category"/></td>
                        </tr>
                        <tr><td><input type="submit" value="Add"/></td></tr>
                    </form>
                </tbody>
            </table>
            <br><br>
            <strong>找书</strong>
            <form accept-charset="utf-8" action="Search_Books" method="post">
                <select name="type">
                    <option value="bookname">书名</option>
                    <option value="author">作者</option>
                    <option value="bookid">ID</option>
                    <option value="category">类别</option>
                </select>&nbsp;
                <input type="text" name="search"/>&nbsp;
                <input type="hidden" name="from" value="manager"/>
                <input type="submit" value="Search"/>
            </form>
            <table>
                <thead>
                    <tr>
                        <td>ID</td>
                        <td>书名</td>
                        <td>作者</td>
                        <td>价格</td>
                        <td>类别</td>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if(book_search!=null)
                        {
                            int size = book_search.size();
                            for(int i=0;i<size;++i)
                            {
                    %>
                    <tr>
                        
                        <td><%=book_search.get(i).getBookid()%></td>
                        <td><%=book_search.get(i).getBookname()%></td>
                        <td><%=book_search.get(i).getAuthor()%></td>
                        <td><%=book_search.get(i).getPrice()%></td>
                        <td><%=book_search.get(i).getCategory()%></td>
                        
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
        </span>
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
