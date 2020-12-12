<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function idok() {
		opener.frm.id.value ="${userId}"
		opener.frm.reid.value ="${userId}"
		self.close();
	}
</script>
</head>
<body>
<div id="wrap">
	<h1>重複確認</h1>
	<form action="id_check_form.hanul" name="frm" method="post" style="margin-right: 0">
		User ID <input type="text" name="id" value="">
				<input type="submit" value="검색" class="submit"><br>
	<div style="margin-top: 20px">
		<c:if test="${message == 1 }">
			<script type="text/javascript">
				opener.document.frm.id.value="";
			</script>
			${userId }はもう利用しているIDです。
		</c:if>
		<c:if test="${message == -1 }">
			${userId }は利用可能です。
			<input type="button" value="사용" class="cancel" onclick="idok()"> 
		</c:if>
	</div>			
	</form>
</div>
</body>
</html>