<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="myStyle.css">

</head>
<body>

	<div class="mainForm">

		<jsp:useBean id="dto" class="com.answer.dto.dto" scope="request"></jsp:useBean>

		<h1>글 수정</h1>
		<form action="answer.do" method="post" id=update>
			<input type="hidden" name="command" value="update_res"> <input
				type="hidden" name="boardno" value="${dto.boardno }">
			<table border="1">
			

				<tr>
					<th>작성자</th>
					<td>${dto.writer }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" value="${dto.title }"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="10" cols="60" name="content" value="${dto.content }"></textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="수정 완료">
					 <input	type="button" value="취소" onclick="location.href='answer.do?command=detail&boardno=${dto.boardno}'"></td>
				</tr>

			</table>
		</form>
	</div>
</body>
</html>