<%@page import="ShoppingCart.ShoppingCart.ShoppingCart" %>
<%@page import="Book.Book.Book"%>
<%@page import="User.User.User"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="java.util.List"%>
<%@page import="ShoppingCart.ShoppingCart.ShoppingCart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" charset="utf-8" content="text/html">
        
        <%
            User user=(User)session.getAttribute("User");
            int userid = 0;
            String username = "";
            if (user != null) {
                userid = user.getUserid();
                username = user.getUsername();
            }
            List<Book> books = (List<Book>)ServletActionContext.getRequest().getAttribute("Book_Search");
        %>
    </head>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script>
         $(document).ready(function(){
             $(".submit_button").click(function(){
                 alert($(this).prev().val());
                 var bookid = $(this).prev().val();
                 $.ajax({
                     url: '/BookStore/Search_Books_Ajax',
                     type: 'POST',
                     data: {bookid:bookid},
                     success: function (msg) {
                         alert(msg);
                         document.getElementById("showbookid").innerHTML = msg;
                     },
                    error : function(){
                        window.alert("ERROR!");
                    }
                 });
             });
         });
    </script>
    <body>
        <div>
            <%=userid%> <%=username%>      
        </div>
        
        <div>
            <strong>books</strong>
            <form accept-charset="utf-8" action="Search_Books" method="post">
                <select name="type">
                    <option value="bookname">书名</option>
                    <option value="author">作者</option>
                    <option value="category">类别</option>
                </select>&nbsp;
                <input type="text" name="search"/>&nbsp;
                <input type="hidden" name="from" value="user"/>
                <input type="submit" value="Search"/>
            </form>
            
            use lucene to search
            <form accept-charset="utf-8" action="lucene" method="post">
                <select name="type">
                    <option value="bookname">书名</option>
                    <option value="author">作者</option>
                    <option value="category">类别</option>
                </select>&nbsp;
                <input type="text" name="search"/>&nbsp;
                <input type="submit" value="Search"/>
            </form>
    
            <table style="text-align: center">
                <thead style="text-align: center">
                    <tr style="text-align: center">
                        <td>书名</td>
                        <td>作者</td>
                        <td>价格</td>
                        <td>类别</td>
                        
                    </tr>
                </thead>
                <tbody>
                    <%
                        if(books!=null)
                        {
                            for(int i=0;i<books.size();++i)
                            {
                    %>
                    <tr>
                        <form accept-charset="utf-8" action="update_Cart" method="post">
                            <tr>
                                <td><%=books.get(i).getBookname()%></td>
                                <td><%=books.get(i).getAuthor()%></td>
                                <td><%=books.get(i).getPrice()%></td>
                                <td><%=books.get(i).getCategory()%></td>
                                <td>
                                    <input type='hidden' id="bookid" name='bookid' value='<%=books.get(i).getBookid()%>'/>
                                    <input type='hidden' name='bookname' value='<%=books.get(i).getBookname()%>'/>
                                    <input type='hidden' name='price' value='<%=books.get(i).getPrice()%>'/>
                                    <input type='hidden' name='category' value='<%=books.get(i).getCategory()%>'/>
                                    <input type='hidden' name='amount' value='1' width="30px" />
                                    <input type="submit" value="add"/>
                                    <td>
                                        <input type='hidden' id="bookid" name='bookid' value='<%=books.get(i).getBookid()%>'/>
                                        <input type="button" id="button" value="detail" class="submit_button"/>
                                    </td>
                                </td>
                                <td></td>
                                <td>
                                    <div type='text' id='showbookid' value='' />
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
