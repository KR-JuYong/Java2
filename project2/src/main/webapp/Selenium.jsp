<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
	crossorigin="anonymous"></script>
<title>주식 데이터 가져오기</title>
</head>
<body>
	<div class="container w-75 mt-5 mx-auto">
		<h2>주식 데이터 목록</h2>
		<hr>
		<ul class="list-group">
			<c:forEach var="Selenium2" items="${Selenium}" varStatus="status">
				<li
					class="list-group-item list-group-item-action d-flex justify-content-between align-items-center"><a
					href="Controller?action=getNews&aid=${selenium.id}"
					class="text-decoration-none">[${status.count}]
						${selenium.종목이름}, ${selenium.date}</a> <a
					href="news.nhn?action=deleteNews&aid=${selenium.id}"><span
						class="badge bg-secondary">&times;</span></a></li>
			</c:forEach>
		</ul>
		<hr>
		<c:if test="${error != null}">
			<div class="alert alert-danger alert-dismissible fade show mt-3">
				에러 발생: ${error}
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
			</div>
		</c:if>
	</div>
</body>
</html>