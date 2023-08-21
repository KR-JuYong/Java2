<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 첫 JSP 프로그램</title>
</head>
<body>
	<h1>Main Page Web</h1><br>
	현재 날짜와 시간은
	<%= java.time.LocalDateTime.now() %> //위에서 language를 java로 설정 했기 때문에 그냥 LocalDateTime.now()만 입력 해도 됨
</body>
</html>