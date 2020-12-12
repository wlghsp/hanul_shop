<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>細部情報ページ</title>
<style type="text/css">
table {
	width: 971px;
	margin: 0 auto;
	margin-bottom: 100px;
}
</style>
</head>
<body>
<h2 align="center">細部情報ページ</h2>
	<table border="1">
			<tr>
				<td>
					<img alt="商品詳細" src="images/product_img/${detailProduct.p_img}" width="340" height="300">
				</td>
				<td>
					<table border="1" style="height: 300px; width: 400px;">
						<tr align="center">
							<td>商品名</td>
							<td>${detailProduct.p_name}</td>
						</tr>
						<tr align="center">
							<td>価格</td>
							<td><fmt:formatNumber value="${detailProduct.p_price2}" type="currency"/></td>
						</tr>
						<tr align="center">
							<td>商品紹介</td>
							<td>${detailProduct.p_content}</td>
						</tr>
						<tr align="center">
							<td colspan="2">
								<form name="form1" method="post" action="">
									<input type="hidden" name="productId" value="${detailProduct.p_code }">
									<select name="amount">
									 	<c:forEach begin="1" end="10" var="i">
									 		<option value="${i}">${i}</option>
									 	</c:forEach>
									</select>&nbsp;個
									<input type="submit" value="商品をカゴに追加">
								</form>
								<h3><a href="index.hanul">商品目録</a></h3>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
<%@ include file="../footer.jsp" %>	
</body>
</html>
