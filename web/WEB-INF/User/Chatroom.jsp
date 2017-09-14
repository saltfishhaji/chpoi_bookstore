<%@page import="User.User.User"%>
        <%
            User user=(User)session.getAttribute("User");
            int userid = 0;
            String username = "";
            if (user != null) {
                userid = user.getUserid();
                username = user.getUsername();
            }
        %>
<!DOCTYPE html>
<html>

    <head>
        <title>Chatroom</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
    </head>
    
    <body>
        <div>
            <%=userid%> <%=username%>      
        </div>
        <input type="text" id="txt"/>
        <input type="hidden" id="id" value="<%=userid%>"/>
        <input type="hidden" id="name" value="<%=username%>"/>
        <input type="button" id="sendBtn" value="Send" onclick="sendMessage();"/>
        <input type="button" id="closeBtn" value="Close" onclick="closeSocket();"/>
        <br>
        Messagers:<br>
        <div id="messages"></div>
        
        

        <script type="text/javascript">
            var webSocket = new WebSocket("ws://localhost:8080/BookStore/chatroom");
            
            webSocket.onmessage=function(event)
            {
                var messages=document.getElementById('messages');
                messages.innerHTML += event.data+"<br>";
            };
            
            function sendMessage()
            {
                var txt=document.getElementById('id').value+":"+document.getElementById('name').value+"   "+document.getElementById('txt').value;
                //var txt=$("#id").val()+$("#name").val()+$("#txt").val();
                //alert(txt);
                webSocket.send(txt);
            };
            
            function closeSocket()
            {
                webSocket.close();
            }
        </script>
    </body>
</html>
