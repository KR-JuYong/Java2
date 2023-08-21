<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- url 이 아니라 uri를 사용함 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 종합 예제</title>
</head>
<body>
	<h2>JSTL 종합 에제</h2>
	<hr>
	<h3>set, out</h3>
	<c:set var="product1" value="애플 아이폰" /> <!-- 이게 태그 -->
	<c:set var="product2" value="삼성 갤럭시 노트" />
	
	<p> <!-- p = 줄뜨움 -->
		product1(jstl):
			<c:out value="${product1}" default="Not registered" escapeXml="true" /> <!-- c:set은 값을 설정 c:out은 값을 출력 -->
	</p>
	<p>product1(e1):${product2}</p>
	<p>intArray[2]: ${intArray[2]}</p>
</body>
</html>