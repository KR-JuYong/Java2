<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.isdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha.384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anoymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8N1+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<title>레시피 관리 앱</title>
</head>
<body>
	<div class="container w-75 mt-5 mx-auto">
		<h2>음식</h2>
		${cook.title}
		<hr>
		<div class="card w-75 mx-auto">
			<img class="card-img-top" src="음식 사진 <br> ${cook.img}">
			<div class="card-body">
				<h4 class="card-title">저장 날짜: ${cook.date}</h4>
				<p class="card-ingredient">재료: ${cook.ingredient}</p>
				<p class="card-text">레시피: ${cook.content}</p>
			</div>
		</div>
		<hr>
		<a href="javascript:history.back()" class="btn btn-primary"> <<
			뒤로가기 </a>

	</div>
</body>
</html>