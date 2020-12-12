<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Form Design</title>
    <link rel="stylesheet" type="text/css" href="css/style1.css">
<script src="js/jQuery/jquery-3.4.1.js"></script>
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
<div class="loginbox">
    <img src="images/avatar.png" class="avatar">
        <h1>Login Here</h1>
        <form action="login.hanul" method="post" name="frm" onsubmit="return check()" >
            <p>Username</p>
            <input type="text" name="id" placeholder="Enter Username" id="id">
            <p>Password</p>
            <input type="password" name="pwd" placeholder="Enter Password" id="pwd">
            <input type="submit" name="" value="Login" class="submit">
            <a href="#">Lost your password?</a><br>
            <a href="contract.hanul">Don't have an account?</a>
        </form>
    </div>
</body>
</html>
<%-- <div id="login1">
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
</div> --%>

