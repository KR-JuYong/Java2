<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	int age=Integer.parseInt(request.getParameter("age"));
	int today=java.time.LocalDateTime.now().getYear();
	long result =today-age;
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>만 나이 구하기 프로그램</title>
</head>
<body>
	<h2>만 나이 계산기</h2><br>
	
	올해 <%=result %>세 입니다.
	
</body>
</html>