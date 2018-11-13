
<%
	response.setHeader("Pragma", "no-coche");
response.setHeader("Cache-control", "no-store");
response.setHeader("Expires", "0");
/*
데이터가 변경되었을 때 이전 내용을 화면에 보여주는 이유 -> 서버 값이 아닌 캐시에 저장된 내용을 가져오기 때문 

브라우저가 캐시에 응답결과를 저장하지 않도록 설정
http 1.0-> 	response.setHeader("Praoma", "no-coche");
http 1.1 -> response.setHeader("Cache-control", "no-store");
proxy server -> response.setHeader("Expires", "0");

*/

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index-answerAnswer</title>
<link rel="stylesheet" type="text/css" href="myStyle.css">
<script type="text/javascript">
	function registForm(){
		location.href="answer.do?command=registform";
	}

</script>
</head>
<body>
	<jsp:useBean id="dto1" class="com.my.dto.LoginBoardDto" scope="session"></jsp:useBean>

	<form action="answer.do" method="post">
	<input type="hidden" name="command" value="login">
	<table border="1">
	
		<col width="100">
		<col width="100">
		<tr>
			<th>I D : </th>
			<td><input type="text" name="id"></td>
		</tr>
		<tr>
			<th>P W : </th>
			<td><input type="password" name="pw"></td>
		</tr>
		<tr>
			<td colspan="2">
			<input type="submit" value="login">
			<input type="button" value="regist" onclick="registForm();">
			</td>
		</tr>
	</table>
	</form>




</body>
</html>