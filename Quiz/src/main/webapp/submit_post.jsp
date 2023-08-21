<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%
String title=request.getParameter("title");
String content=request.getParameter("content");
%>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	Title: <%= title %><br>
	Content: <%= content %>
</body>
</html>