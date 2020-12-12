<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--header  -->
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hanul Shop</title>
<script src="js/jQuery/jquery-3.4.1.js"></script>
</head>
<body>
<div id=index>
	<div id="main_img">
			<img alt="main_img" src="images/main_img.png">
	</div>		
	<div id="front">
			<h2 align="center" >新商品</h2>	
			<div id="newProduct">
				<c:forEach items="${listNewProduct}" var="newProductDto">
			 		<div id="item">
			 			<a href="product_detail.hanul?p_code=${newProductDto.p_code}">
			 				<img alt="신상품" src="images/product_img/${newProductDto.p_img}">
			 				<h3>${newProductDto.p_name}</h3>
			 				<p><fmt:formatNumber value="${newProductDto.p_price2}" type="currency"/></p>
			 			</a>
			 		</div>
				</c:forEach>
			</div>	
			<h2 align="center" >人気商品</h2>
			<div id="bestProduct">
				<c:forEach items="${listBestProduct}" var="bestProductDto">
				 <div id="item">
			 		<a href="product_detail.hanul?p_code=${bestProductDto.p_code}">
			 			<img alt="베스트상품" src="images/product_img/${bestProductDto.p_img}">
			 			<h3>${bestProductDto.p_name}</h3>
			 			<p><fmt:formatNumber value="${bestProductDto.p_price2}" type="currency"/></p>
			 		</a>
				 </div>	
				</c:forEach>
		</div>
<%@ include file="footer.jsp" %>
	</div>	
</div>	
</body>
</html>