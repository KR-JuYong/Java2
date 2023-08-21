<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
	crossorigin="anonymous"></script>

<script>
function addToCart() {
    // 여기에 장바구니에 물건을 담는 로직을 구현합니다.
    // 성공적으로 담겼을 경우에는 success 변수를 true로 설정하고, 담기지 않았을 경우에는 false로 설정합니다.
    var success = true;
    
    if (success) {
        alert("장바구니에 담겼습니다.");
        
    } else {
        alert("장바구니에 담을 수 없습니다.");
     
    } //onclick="addToCart()"
    
}
</script>

<meta charset="UTF-8">
<title>main</title>
</head>
<body>



<div class="container w-75 mt-5 mx-auto">

<a href="mkCont?action=register" class="btn btn-outline-success btn-sm"> 회원가입 </a>
<a href="mkCont?action=logout" class="btn btn-outline-success btn-sm"> 로그아웃 </a>
<a href="mkCont?action=cartitem" class="btn btn-outline-success btn-sm"> 장바구니 </a>
<a href="mkCont?action=MasterLogin" class="btn btn-outline-success btn-sm"> 관리자 </a>
		<hr>
		<h2>Market</h2>
		<hr>
		<ul class="list-group">
			<c:forEach var="market" items="${marketlist}" varStatus="status">
				<li class="list-group-item list-group-item-action
		    d-flex justify-content-between align-items-center">
					<a href="/mkCont?action=ge&mid=${market.mid}"
					class="text-decoration-none">[${status.count}] ${market.title},
						${market.date}</a>
					<a href="mkCont?action=getShoppingCart&mid=${market.mid}">
					<span class="badge bg-secondary" onclick="addToCart()">담기</span></a>
				</li>
			</c:forEach>
		</ul>
		<hr>


</body>
</html>