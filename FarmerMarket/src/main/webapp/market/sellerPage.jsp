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
<meta charset="UTF-8">
<title>판매자 관리</title>
</head>
<body>
	<div class="container w-75 mt-5 mx-auto">
	<a href="javascript:history.back()" class="btn btn-outline-success btn-sm"> << Back </a>
	<br>
		<h2>물건 관리</h2>
		<hr>
		<button class="btn btn-outline-info mb-3" type="button"
			data-bs-toggle="collapse" data-bs-target="#addd"
			aria-expanded="false" aria-controls="addd">상품 목록</button>



		<button class="btn btn-outline-info mb-3" type="button"
			data-bs-toggle="collapse" data-bs-target="#orders"
			aria-expanded="false" aria-controls="orders">판매 내역</button>


		<button class="btn btn-outline-info mb-3" type="button"
			data-bs-toggle="collapse" data-bs-target="#addForm"
			aria-expanded="false" aria-controls="addForm">상품 등록</button>



		<div class="collapse" id="addd">
			<ul class="list-group">
				<c:forEach var="product" items="${productlist}" varStatus="status">
					<li
						class="list-group-item list-group-item-action
		    d-flex justify-content-between align-items-center">
						<a href="sellerCt?action=getProduct&pid=${product.pid}"
						class="text-decoration-none">[${status.count}]
							이름: ${product.title}, 날짜: ${product.date} 가격: ${product.price}</a> <a
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



		<div class="collapse" id="addForm">
			<div class="card card-body">
				<form method="post"
					action="/FarmerMarket/sellerCt?action=addProduct"
					enctype="multipart/form-data">
					<label class="form-label">이름</label> <input type="text"
						name="title" class="form-control"> <label
						class="form-label">상품사진</label> <input type="file" name="file"
						class="form-control"> <label class="form-label">가격</label>
					<input type="number" name="price" class="form-control"> <label
						class="form-label">내용</label>
					<textarea cols="50" rows="5" name="content" class="form-control"></textarea>
					<button type="submit" class="btn btn-success mt-3">저장</button>
					<hr>
				</form>
			</div>
		</div>

		<!-- 판매 내역 조회로 수정 -->
		<div class="collapse" id="orders">
            <ul class="list-group">
                <c:forEach var="orders" items="${orderslist}" varStatus="status">
                    <li class="list-group-item list-group-item-action d-flex 
                        justify-content-between align-items-center">
                        <a href="OrderCt?action=orderslist&orderId=${orders.orderId}"
                            class="text-decoration-none">[${status.count}] 주문번호 :${orders.productId}
                            구매자id : ${orders.userId} 금액 : ${orders.price} 구매날짜 : ${orders.orderDate}</a>
                        <a href="OrderCt?action=cancelOrder&orderId=${orders.orderId}">
                            <span class="badge bg-secondary"> &times;</span>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>


	</div>
</body>
</html>