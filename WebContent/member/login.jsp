<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Form Design</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="js/jQuery/jquery-3.4.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js" charset="utf-8"></script>
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
	$(".txtb input").on("focus",function(){
        $(this).addClass("focus");
      });

      $(".txtb input").on("blur",function(){
        if($(this).val() == "")
        $(this).removeClass("focus");
      });
</script>
</head>
<body>
      <form action="login.hanul" class="login-form" method="post" name="frm" onsubmit="return check()" >
        <h1>ログイン</h1>

        <div class="txtb">
          <input type="text" name="id" id="id">
          <span data-placeholder="ユーザー ID"></span>
        </div>

        <div class="txtb">
          <input type="password" name="pwd" id="pwd">
          <span data-placeholder="パスワード"></span>
        </div>

        <input type="submit" class="logbtn" value="ログイン">

        <div class="bottom-text">
          	新しいお客様ですか？ <a href="contract.hanul">会員登録</a>
        </div>

      </form>

</body>
</html>


