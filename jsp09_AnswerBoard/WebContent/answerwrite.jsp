<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="myStyle.css">

</head>
<body>

	
		<h1>답변 글 작성</h1>
			
		<form action="answer.do" method="post" >
			<input type="hidden" name="command" value="answerwrite"> 
			<input	type="hidden" name="parentboardno" value="${parent.boardno }">
		<table border="1">

				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer" value="${dto1.myid }" readonly="readonly"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" value="RE:${parent.title }"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="10" cols="60" name="content">${parent.content }
-----------------------------------------------------------
</textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="답글 작성 완료">
					    <input type="button" value="취소" onclick="location.href='answer.do?command=detail&boardno=${parent.boardno}'"></td>
				</tr>

			</table>
		</form>
	
</body>
</html>