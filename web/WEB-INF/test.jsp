<%-- 
    Document   : test
    Created on : 2017-4-16, 16:57:34
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>ActiveMQ Demo程序</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="js/jquery.min.js"></script>
<style type="text/css">
.h1 {
	margin: 0 auto;
}

#producer{
	width: 48%;
 	border: 1px solid blue; 
	height: 80%;
	align:center;
	margin:0 auto;
}

body{
	text-align :center;
} 
div {
	text-align :center;
}
textarea{
	width:80%;
	height:100px;
	border:1px solid gray;
}
button{
	background-color: rgb(62, 156, 66);
	border: none;
	font-weight: bold;
	color: white;
	height:30px;
}
</style>
<script type="text/javascript">
	
	function send(controller){
		if($("#message").val()==""){
			$("#message").css("border","1px solid red");
			return;
		}else{
			$("#message").css("border","1px solid gray");
		}
		$.ajax({
			type: 'post',
			url:'/BookStore/Queue_Sender',
			dataType:'text', 
			data:{"message":$("#message").val()},
			success:function(data){
				if(data=="suc"){
					$("#status").html("<font color=green>发送成功</font>");
					setTimeout(clear,1000);
				}else{
					$("#status").html("<font color=red>"+data+"</font>");
					setTimeout(clear,5000);
				}
			},
			error:function(data){
				$("#status").html("<font color=red>ERROR:"+data["status"]+","+data["statusText"]+"</font>");
				setTimeout(clear,5000);
			}
			
		});
	}
	
	function clear(){
		$("#status").html("");
	}

</script>
</head>

<body>
	<h1>Hello ActiveMQ</h1>
	<div id="producer">
		<h2>Producer</h2>
		<textarea id="message"></textarea>
		<br>
                <button onclick="send('queueSender')">发送Queue消息</button>
		<br>
		<span id="status"></span>
	</div>
</body>
</html>
