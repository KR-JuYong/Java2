<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="project.CookDAO"%>

<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.isdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anoymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8N1+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<title>레시피 검색 결과</title>
</head>
<body>
	<h1>추천 레시피</h1>
	<form method="post" action="/jwbook3/cook?action=doPost">
	<label>레시피 정보</label><input type="text name=ti
	</form>
	
	
	<button onclick="window.open('<%=newUrl%>');">레시피 보러가기</button>
</body>
</html>