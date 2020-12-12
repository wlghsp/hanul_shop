<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン　失敗</title>
<style type="text/css">
 #loginFail{
	text-align: center;
	width: 971px;
	margin: 0 auto;
	margin-top: 50px;
	margin-bottom: 50px;	
	font-size: 2em;
}
#failForm {
	width: 800px;
	margin: 0 auto;
}

#failForm input{
	font-size: 18px;
}
</style>
<script type="text/javascript">
 
 function loginGo(obj) {
	obj.action = "login_form.hanul";
	obj.submit();
}
 function mainGo(obj) {
	 obj.action = "index.hanul";
	 obj.submit();
}

</script>
</head>
<body>
<div id="loginFail">
	<form id="failForm" action="">
		<h2>ユーザーIDかパスワードが違います。</h2>
		<input type="button" value="ログイン"  onclick="loginGo(this.form)">
		<input type="button" value="メインへ戻る" onclick="mainGo(this.form)">
	</form>
</div>
<%@ include file="../footer.jsp" %>	
</body>
</html>