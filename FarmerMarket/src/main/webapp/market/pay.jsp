<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 페이지</title>
</head>
<body>
	<h2>결제 페이지</h2>
	<hr>
		<form method ="post" action="/Pay?action=addPay">
			<label>보내는 사람</label>
			<input type="text" name="name" size="10">
			<br>
			<label>연락처</label>
			<input type="text" name="phonenumber" size="10">
			<hr>
			<label>받는 사람</label>
			<input type="text" name="name2" size="10">
			<br>
			<label>연락처</label>
			<input type="text" name="phonenumber2" size="10">
			<br>
			<label>주소</label>
			<input type="text" name="address" size="10">
			<br>
			<label>배송 요청사항</label>
			<input type="text" name="request" size="10">
			<hr>
			<label>결제 정보</label>
			<select name='cartType'>
			<option>롯데카드</option>
			<option>신한카드</option>
			<option>하나카드</option>
			<option>BC카드</option>
			<option>삼성카드</option>
			<option>현대카드</option>
			<option>우리카드</option>
			<option>NH농협카드</option>
			<option>KB국민가드</option>
			<option>카카오뱅크카드</option>
			<option>토스뱅크카드</option>
			</select>
			<label>카드 번호</label>
			<input type="text" name="cardnumber" size="8">
			<br>
			<label>cvc 번호</label>
			<input type="text" name="cvc" size="8">
			<br>
			<label>유효기간(MM/YY)</label>
			<input type="text" name="date" size="8">
			<br>
			<input type="submit" value="결제 하기">
		</form>
</body>
</html>