<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome Hanul Shop</title>
<link href="css/shopping.css" rel="stylesheet">
<script src="js/jQuery/jquery-3.4.1.min.js"></script>
</head>
<body>
	<div id="wrap">
		<header>
			<!-- 로고 시작 -->
			<div id="logo">
				<a href="index.hanul">
					<img alt="hanulshop" src="images/logo.png" width="180" height="100">
				</a>
			</div>
			<nav id="catagory_menu">
				<ul>
					<c:choose>
						<c:when test="${empty sessionScope.loginUser}">
							<li><a href="login_form.hanul">LOGIN</a></li>
							<li>|</li>
							<li><a href="contract.hanul">JOIN</a></li>
						</c:when>
						<c:otherwise>
							<li style="color: orange;">
								${sessionScope.loginUser.name }(${sessionScope.loginUser.id })
							</li>
							<li><a href="logout.hanul">LOGOUT</a></li>
						</c:otherwise>
					</c:choose>
					<li>|</li>
					<li><a href="cart_list.hanul">CART</a></li>
					<li>|</li>
					<li><a href="mypage.hanul">MY PAGE</a></li>
					<li>|</li>
					<li><a href="qna_list.hanul">Q&amp;A(1:1)</a></li>
					<li>|</li>
					<li><a href="boardList.hanul">掲示板</a></li>
				</ul>
			</nav>
		</header>
	</div>
</body>
</html>





