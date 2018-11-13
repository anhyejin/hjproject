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

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
  <%@ page import="com.my.dao.LoginBoardDao" %>
 <%@ page import="com.my.dto.LoginBoardDto" %>
       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="myStyle.css">
<title>Insert title here</title>
</head>
<%
	//LoginBoardDto dto = (LoginBoardDto)session.getAttribute("dto");

	//if(dto == null){
	//	pageContext.forward("index.jsp");
	//}
%>
<body>


	<div>
		<span>${dto1.myid }님 환영합니다(등급 :${dto1.myrole }  )</span>
		    <a href="answer.do?command=logout">로그아웃</a 로그아웃>
	</div>
	
	<div>
		<div>
			<a href="answer.do?command=userlistall">회원정보 조회 (All)</a>
		</div>
		<div>
			<a href="answer.do?command=userlistenabled">회원정보 조회(enabled='Y')</a>
		</div>
		<div>
			<a href="answer.do?command=list">글 목록 보기</a>
			
		</div>

	</div>
		




</body>
</html>