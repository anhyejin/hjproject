<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="myStyle.css">

</head>
<body>
   <h1>글 쓰기</h1>
   
   <!-- controller.do로 넘어갈 때 command 값을 가지고 넘어간다 -->
   <form action="answer.do" method="post">
      <input type="hidden" name="command" value="boardwrite"/>
      <table border="1">
         <tr>
            <th>작성자</th>
            <td><input type="text" name="writer" value="${dto1.myid }" readonly="readonly"/></td>
         </tr>      
         <tr>
            <th>제   목</th>
            <td><input type="text" name="title"/></td>
         </tr>
         <tr>
            <th>내   용</th>
            <td><textarea rows="10" cols="60" name="content"></textarea></td>
         </tr>
         <tr>
            <td colspan="2">
               <input type="submit" value="작성"/>
               <input type="button" value="취소" onclick="location.href='answer.do?command=list'"/>
            </td>
         </tr>
      </table>   
   </form>
</body>
</html>