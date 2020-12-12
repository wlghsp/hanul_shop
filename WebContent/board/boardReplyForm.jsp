<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jQuery/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="dist/summernote.min.css" rel="stylesheet">
<script src="dist/summernote.min.js"></script>
<style type="text/css">
#replydiv {
	width: 971px;
	margin: 0 auto;
	margin-top: 50px;
}
#replyForm {
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

	function backToList(obj) {
		obj.action="boardList.hanul";
		obj.submit();
	}
	
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader(); //자바스크립트 io객체 
			reader.onload = function(e) {
				$("#preview").attr("src", e.target.result);				
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	/* $(document).ready(function() {
 		$("#subject").blur(function(){
 			var subject = $('#subject').val();
 			
		if ($("#subject").val().trim().length == 0) {
			$("#message1").html("タイトルを入力してください").css("color", "red");
			document.getElementById("subject").focus();
		} else if($("#content").val().trim().length == 0){
			 $("#message1").html("内容を入力してください").css("color", "red");
			 document.getElementById("content").focus();		   
		   } 
 		});
	}); */
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
		frm.action= "boardReply.hanul";
		frm.submit();
	}

</script>
</head>
<body>
	<h1 style="text-align: center;">コメント作成</h1>
<div id="replydiv">
	<form id="replyForm" name="frm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="ref" value="${replyInfo.ref }">
		<input type="hidden" name="re_step" value="${replyInfo.re_step }">
		<input type="hidden" name="re_level" value="${replyInfo.re_level }">
		<input type="hidden" name="id" value="${sessionScope.loginUser.id }">
		
		<table align="center">
			<tr>
				<td align="right"></td>
				<td colspan="2"><span id="message1"></span></td>
			</tr>
			<tr>
				<td align="right">タイトル&nbsp;</td>
				<td colspan="2"><input type="text" size="100" maxlength="50" name="subject" id="subject"></td>
			</tr>
			<tr>
				<td align="right">内容&nbsp;</td>
				<td colspan="2"><textarea name="content" id="summernote"></textarea></td>
			</tr>
			<tr>
				<td align="right" valign="top">ファイル添付&nbsp;</td>
				<td><input type="file" name="fileName" onchange="readURL(this)"></td>
				<td><img alt="preview" src="" id="preview" width="200" height="200"></td>
			</tr>			
			<tr>
				<td align="right"></td>
				<td colspan="2">
					<input type="button" value="作成完了" onclick="formChk(this.form)">
					<input type="button" value="キャンセル" onclick="javascript:history.go(-1)">
				</td>
			</tr>
		</table>
	</form>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>