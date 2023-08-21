<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>장바구니 내역</title>
</head>
<body>
	<div class="container w-75 mt-5 mx-auto">
		<h3>장바구니 내역</h3>
		<hr>
		<a href="javascript:history.back()"
			class="btn btn-outline-success btn-sm"> << Back </a>
			<hr>
		<ul class="list-group">
			<c:forEach var="shoppingcart" items="${shoppingcartlist}"
				varStatus="status">
				<li
					class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
					<a
					href="/CartItemController?action=listShoppingCart&cartId=${shoppingcart.cartId}"
					class="text-decoration-none"> [${status.count}] 제목:
						${shoppingcart.title} 가격: ${shoppingcart.price} </a> <a
					href="/FarmerMarket/CartItemController?action=CancleShoppingCart&cartId=${shoppingcart.cartId}">
						<span class="badge bg-secondary"> &times;</span>
				</a>
				</li>
				<!--  <div action="/FarmerMarket/CartItemController?action=listShoppingCart">총 구매 갯수: {$shoppingcart.count</div>
				<div action="/FarmerMarket/CartItemController?action=listShoppingCart">총 결제 금액: {$shoppingcart.total</div>
			-->
			</c:forEach>
		</ul>
		<hr>

	</div>
</body>
</html>
