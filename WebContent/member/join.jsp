<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<link href="css/shopping.css" rel="stylesheet">
<script type="text/javascript" src="js/member.js"></script>
<script src="js/jQuery/jquery-3.2.1.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<style>
#join1 {
	width: 971px;
	margin: 0 auto;
	margin-top: 60px;
}
</style>
<script>
 	$(document).ready(function() {
 		
 		$("#idChk").blur(function(){
 		
 		
 		
		if ($("#idChk").val().trim().length == 0) {
			$("#message1").html("IDを入力してください").css("color", "red");
			$("#idChk").val("");
			$("#idChk").focus();
		} else {
			$("#idChk").css("background-color", "white");
			$.ajax({
				type : "POST",
				url : "id_check_form.hanul",
				dataType : "json",
				data : "id=" + id,
				success : function(data) {
					if (data.message == "1") {
						$("#message1").html("もう存在しているIDです。").css("color", "red");
						$("#reid").val("-1"); //초기화 
						$("#idChk").focus();
					}  else {
						$("#message1").html("利用可能なIDです").css("color", "blue");
						$("#reid").val("-1");
					}
				},
				fail : function() {
					alert("システムエラー");
				}
			
				});
			}
 		
 		});
 		$("#pwdChk").blur(function(){
 			var pw = $('#pwd').val();
 			var pw2 = $('#pwdChk').val();
 			
 			if (pw != pw2) {
 				$("#message2").html("パスワードが違います。").css("color", "red");
 				
 				
 			} else if (pw == pw2) {
 				$("#message2").html("パスワードオーケー").css("color", "blue");
 				
 			} 
 		});
 		
 	});

</script>
<div id=join1 >
<article>
	<h2>会員登録</h2>
	<form action="join.hanul" method="post" name="frm">
		<fieldset>
			<legend>基本情報</legend>
			<label>ユーザー ID</label>
			<input type="text" name="id" size="12" id="idChk">&nbsp;<span id="message1"></span>
			<input type="hidden" name="reid" id="reid"><br>
			<!-- <input type="button" value="重複確認" onclick="idcheck()"> -->
			<label>パスワード</label>
			<input type="password" name="pwd" id="pwd" ><br>
			<label>パスワード再入力</label>
			<input type="password" name="pwdChk" id="pwdChk" >&nbsp;<span id="message2"></span><br>
			<label>名前</label>
			<input type="text" name="name">
			<label>Eメール</label>
			<input type="text" name="email" id="email">
		</fieldset>
		<fieldset>
			<legend>付加情報</legend>
			<label>郵便番号</label>
			<input type="text"  id="zipNum" name="zipNum" size="10" readonly>
			<input type="button" value="住所検索" onclick="sample6_execDaumPostcode()"><br>
			<label>住所</label>
			<input type="text" id="addr1" name="addr1" size="50" readonly>
			<input type="text" id="addr2" name="addr2" size="50" placeholder="詳細住所を入力してください"><br>
			<label>電話番号</label>
			<input type="text" name="phone">ex)010-0000-0000<br>
		</fieldset>
		<div id="buttons">
			<input type="button" value="会員登録" class="submit" onclick="go_save()">
			<input type="reset" value="キャンセル" class="cancel">
		</div>
	</form>
<%@ include file="../footer.jsp" %>
</article>
</div>













