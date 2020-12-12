<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規投稿</title>
<!-- include libraries(jQuery, bootstrap) -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jQuery/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="dist/summernote.min.css" rel="stylesheet">
<script src="dist/summernote.min.js"></script>
<style type="text/css">
#boardForm {
	width: 971px;
	margin: 0 auto;
	margin-top: 50px;

}
#articleForm {
	width: 800px;
	margin: 0 auto;
	margin-bottom: 50px;
}

</style>
<script type="text/javascript">
$(document).ready(function() {
	  $('#summernote').summernote({
	    	placeholder: '内容を入力してください',
	        minHeight: 300,
	        maxHeight: null,
	        focus: true, 
	        lang : 'ko-KR'
	  });
	});
	// 이미지 파일 첨부시 이미지 미리 보기 
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader(); //자바스크립트 io객체 
			reader.onload = function(e) {
				$("#preview").attr("src", e.target.result);				
			}
			reader.readAsDataURL(input.files[0]);
			}
		}
	
	
	/* // 게시판 목록보기 
	function backToList(obj) {
			obj.action = "boardList.hanul";
			obj.submit();
		} */
	
 
 	/* $(document).ready(function() {
 		$("#subject").blur(function(){
 		
		if ($("#subject").val().trim().length == 0) {
			　 $("#message1").html("タイトルを入力してください").css("color", "red");
			  $("#subject").val("");
			  document.articleForm.subject.focus();
		} 
 		});
	});  */
	/* function formChk() {		
			
		if ($("#subject").val().trim().length == 0) {
			alert("タイトルを入力してください");
			$("#subject").focus();
		} else if($("#summernote").val().trim().length == 0){
			alert("内容を入力してください");
			document.getElementById("summernote").focus();
		} else {
			document.articleForm.action = "boardAdd.hanul";
			document.articleForm.submit();
		}
		} */
	function formChk(frm) {
			var title = frm.subject.value;
			var content = frm.content.value;
			
			if (title.trim().length == 0){
				alert("タイトルを入力してください");
				return false;
			}
			if ($('#summernote').summernote('isEmpty')){
				alert("内容を入力してください");
				return false;
			}
			frm.action= "boardAdd.hanul";
			frm.submit();
		}
	
</script>
</head>
<body>
<div id="boardForm">
	<h1 style="text-align: center;">新しい投稿</h1>
	<form id="articleForm" name="articleForm"  method="post" enctype="multipart/form-data" >
		<input type="hidden" value="${sessionScope.loginUser.id }" name="id">
		<table border="0" align="center">
			<tr>
				<td align="right"></td>
				<td colspan="2"><span id="message1"></span></td>
			</tr>
			<tr>
				<td align="right">タイトル&nbsp;</td>
				<td colspan="2"><input type="text" size="100" maxlength="50" name="subject" id="subject" placeholder="タイトルを入力してください" ></td>
			</tr>
			<tr>
				<td align="right"></td>
				<td colspan="2" style="padding: 10px 0;"><input type="checkbox" value="1" name="lock_post" />投稿を秘密にしたいならチェックしてください</td>
			</tr>
			<tr>
				<td align="right" valign="top">内容&nbsp;</td>
				<td colspan="2"><textarea name="content" id="summernote"></textarea></td>
			</tr>
			<tr>
				<td align="right" valign="top">ファイル添付&nbsp;</td>
				<td><input type="file" name="fileName" onchange="readURL(this)" /></td>
				<td><img id="preview" alt="preview" src="" width="200" height="200"></td>
			</tr>
			<tr>
				<td align="right"></td>
				<td colspan="2">
					<input type="button" value="作成完了" onclick="formChk(this.form)"> 
					<input type="button" value="キャンセル" onclick="javascript:history.go(-1)" /></td>
			</tr>
		</table>
	</form>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>