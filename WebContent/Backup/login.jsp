<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link href="css/shopping.css" rel="stylesheet">
<script src="js/jQuery/jquery-3.4.1.js"></script>
<script type="text/javascript" src="js/member.js"></script>
<style>
#login1 {
	width: 971px;
	margin: 0 auto;
	margin-top: 100px;
	margin-right: 500px;
	height: 200px;
}
</style>
<script>
	function check() {
		var re = /^[a-zA-Z0-9]{4,12}$/
		var id = $('#id').val();
		var pw = $('#pwd').val();
		if (!id) {
			$('#id').focus();
			alert("IDを入力してください");
			return false;
		} else if (!pw) {
			$('#pw').focus();
			alert("パスワードを入力してください");
			return false;
		}

	};
</script>
</head>
<body>
<div id="login1">
<article>
	<h1>ログイン</h1>
	<form action="login.hanul" method="post" name="frm" onsubmit="return check()" >
		<fieldset>
			<legend></legend>
			<label>ユーザー ID</label>
			<input name="id" type="text" id="id"><br>
			<label>パスワード</label>
			<input name="pwd" type="password" id="pwd"><br>
		</fieldset>
		<div id="buttons">
			<input type="submit" value="ログイン" class="submit" >
			<input type="button" value="会員登録へ進む" class="cancel" onclick="join()" >
			<input type="button" value="ユーザID・パスワードを忘れた場合" class="submit">
		</div>
	</form>
<%@ include file="../footer.jsp" %>
</article>
</div>
</body>
</html>


