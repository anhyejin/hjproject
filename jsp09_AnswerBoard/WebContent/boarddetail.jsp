<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="myStyle.css">

</head>
<body>



	<jsp:useBean id="dto" class="com.answer.dto.dto" scope="request"></jsp:useBean>

	<h1>상세 글 보기</h1>

	<table border="1">

		<tr>
			<th>작성자</th>
			<td><jsp:getProperty property="writer" name="dto"></jsp:getProperty></td>
		</tr>
		<tr>
			<th>제 목</th>
			<td><jsp:getProperty property="title" name="dto"></jsp:getProperty></td>
		</tr>
		<tr>
			<th>내 용</th>
			<td><textarea rows="10" cols="60"  readonly="readonly"><jsp:getProperty
						property="content" name="dto"></jsp:getProperty></textarea></td>
		</tr>

		<tr>
			<td colspan="2">
				<c:choose>
					<c:when test="${dto1.myid eq dto.writer }">
						<input type="button" value="수정" onclick="location.href='answer.do?command=update&boardno=${dto.boardno }'">
						<input type="button" value="삭제" onclick="location.href='answer.do?command=delete&boardno=${dto.boardno }'">
					</c:when>
				</c:choose> 
				<input type="button" value="목록" onclick="location.href='answer.do?command=list'"> 
				<input type="button" value="답글" onclick="location.href='answer.do?command=answerform&parentboardno=${dto.boardno}'">
			</td>
		</tr>


	</table>
</body>
</html>