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

<!--<script>
        // 주문하기 버튼 클릭 시 팝업창 표시
        const confirmModal = new bootstrap.Modal(document.getElementById('confirmModal'));

        const orderButton = document.getElementById('orderButton');
        orderButton.addEventListener('click', () => {
            confirmModal.show();
        });

        // 예 버튼 클릭 시 주문 처리 및 페이지 이동
        const confirmYesBtn = document.getElementById('confirmYesBtn');
        confirmYesBtn.addEventListener('click', () => {
            // 주문 처리를 위한 AJAX 요청을 수행
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function() {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        // 주문이 성공적으로 처리되면 페이지를 이동하여 주문 내역을 확인
                        window.location.href = "/orders.jsp";
                    } else {
                        // 주문 처리 실패 시 에러 메시지를 표시
                        console.error("주문 처리 중에 오류가 발생했습니다.");
                    }
                }
            };
            xhr.open("POST", "/FarmerMarket/OrderController?action=listOrders", true);
            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhr.send("action=submitOrder");

            // 주문 확인 팝업창 닫기
            confirmModal.hide();
        });

        // 페이지가 로드될 때 주문 내역을 가져와서 표시
        window.addEventListener('load', fetchOrders);
    </script> -->
<meta charset="UTF-8">
<title>주문내역</title>
</head>
<body>
	<div class="container w-75 mt-5 mx-auto">
		<h2>주문내역</h2>
		<hr>
		<button class="btn btn-outline-info mb-3" type="button"
			data-bs-toggle="collapse" data-bs-target="#addd"
			aria-expanded="false" aria-controls="addd">상품 목록</button>
		<button class="btn btn-outline-info mb-3" type="button"
			data-bs-toggle="collapse" data-bs-target="#orders"
			aria-expanded="false" aria-controls="orders">판매 목록</button>
		<!-- 주문 내역을 표시하는 부분 -->
		<div class="collapse" id="addd">
			<ul class="list-group">
				<c:forEach var="product" items="${productlist}" varStatus="status">
					<li
						class="list-group-item list-group-item-action
		    d-flex justify-content-between align-items-center">
						<a href="sellerCt?action=getProduct&pid=${product.pid}"
						class="text-decoration-none">[${status.count}]
							${product.title}, ${product.date} ${product.price}</a> <a
						href="sellerCt?action=deleteProduct&pid=${product.pid}"> <span
							class="badge bg-secondary"> &times;</span></a>
					</li>
				</c:forEach>
			</ul>
			<hr>

			<c:if test="${error != null}">
				<div class="alert alert-danger alert-dismissible fade show mt-3">
					에러 발생: ${error}
					<button type="button" class="btn-close" data-bs-dismiss="alert">
					</button>
				</div>
			</c:if>
		</div>
		<div class="collapse" id="orders">
			<ul class="list-group">
				<c:forEach var="orders" items="${orderlist}" varStatus="status">
					<li
						class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
						<a
						href="/OrderController?action=listOrders&orderId=${orders.orderId}"
						class="text-decoration-none"> [${status.count}] 제품 이름:
							${orders.productId} 구매자 아이디: ${orders.userId} 가격:
							${orders.price}원 구매일: ${orders.orderDate} </a> <a
						href="/FarmerMarket/OrderController?action=CancleOrders&orderId=${orders.orderId}">
							<span class="badge bg-secondary">&times;</span>
					</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>
