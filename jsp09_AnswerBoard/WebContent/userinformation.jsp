<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
  <%@ page import="com.my.dao.LoginBoardDao" %>
 <%@ page import="com.my.dto.LoginBoardDto" %>
    <%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="myStyle.css">
</head>

	<jsp:useBean id="dto1" class="com.my.dto.LoginBoardDto" scope="request"></jsp:useBean>

<body>
	<div class = "mainForm">
		<h1>유저 정보 보기</h1>
	<form action="answer.do" method="post" id=userinformation>
		<input type = "hidden" name="command" value="userinformation_res">
		<input type = "hidden" name="myno" value="<%=dto1.getMyno()%>">
		
		<table border="1">
			<tr>
				<th>이름
				<td><%=dto1.getMyname()%>
			</tr>
			<tr>
				<th>주소
				<td><input type="text" name="myaddr" id="textbox" readonly="readonly" value="<%=dto1.getMyaddr()%>">
			</tr>
			<tr>
				<th>전화번호
				<td ><input type="text" name="myphone" id="textbox" readonly="readonly" value="<%=dto1.getMyphone()%>">
			</tr>
			<tr>
				<th>이메일
				<td ><input type="text" name="myemail" id="textbox" readonly="readonly" value="<%=dto1.getMyemail()%>">
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="수정" > 
				<input type="button" value="취소" onclick="location.href='usermain.jsp'">
				
			</tr>
		</table>
	</form>
	</div>
</body>

</body>
</html>