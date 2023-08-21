<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>회원가입 및 로그인</title>
</head>
<body>
<div class="container w-75 mt-5 mx-auto">
    <h2>회원가입</h2><a href="javascript:history.back()" class="btn btn-outline-success btn-sm"> << Back </a>
    <hr>
    <form method="post" action="userCt?action=register">
        <div class="mb-3">
            <label for="user" class="form-label">이름</label>
            <input type="text" name="user" class="form-control" id="user" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">이메일</label>
            <input type="email" name="email" class="form-control" id="email" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">비밀번호</label>
            <input type="password" name="password" class="form-control" id="password" required>
        </div>
        <button type="submit" class="btn btn-primary">가입하기</button>
    </form>
    <hr>
    <h2>로그인</h2><a href="javascript:history.back()" class="btn btn-outline-success btn-sm"> << Back </a>
    <hr>
    <form method="post" action="mkCont?action=login">
        <div class="mb-3">
            <label for="username" class="form-label">이메일</label>
            <input type="text" name="username" class="form-control" id="username" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">비밀번호</label>
            <input type="password" name="password" class="form-control" id="password" required>
        </div>
        <button type="submit" class="btn btn-primary" >로그인</button>
    </form>
    <hr>
</div>
</body>
</html>
