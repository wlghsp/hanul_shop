<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>投稿</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jQuery/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="dist/summernote.min.css" rel="stylesheet">
<script src="dist/summernote.min.js"></script>
<style type="text/css">

	#boardView1 {
		width: 971px;
		margin: 0 auto;
		margin-top: 40px;
	}
	#boardView2 {
		margin: 0 auto;
	}
	#tr_btn_modify {
		display: none;
	}
	.cls2 {
		text-align: center;
		font-size: 30px;
		margin-top: 50px;
	}
		
	div.button {
	   margin: auto;
	   width: 50%;
	}
		
	div.button input {
	   padding: 5px;
	   font-size: 18px;
	}
</style>
<script type="text/javascript">
$(document).ready(function() {
	 $('#summernote').summernote({
	    	minHeight: 250,
	        maxHeight: null,
	        lang : 'ko-KR'
	  });	
	$('#summernote').summernote('disable');
	
	});

	function fn_enable(obj) {
		var fileName = "${selBoardView.fileName}";
		document.getElementById("i_subject").disabled = false;
		$('#summernote').summernote('enable');
		
		if (fileName != "") {
			document.getElementById("i_fileName").disabled = false;
		}
		document.getElementById("tr_btn_modify").style.display = "block";
		document.getElementById("tr_btn").style.display= "none";
	}
	
	function fn_modify_article(frm) {
		var title = frm.subject.value;
		var content = frm.content.value;
		
		if (title.trim().length == 0) {
			alert("タイトルを入力してください");
			return false;
		} 
		if ($('#summernote').summernote('isEmpty')){
			alert("内容を入力してください");
			return false;
		}
		frm.action= "boardModify.hanul";
		frm.submit();
	}
	
	function backToList(obj) {
		obj.action = "boardList.hanul";
		obj.submit();  //history.go(-1); 도 가능 
	}  
	
	// 자바스크립트로 폼 전송 
	function fn_reply_form(url, articleNo, ref, re_step, re_level) {
		// <form>태그 와 <input>태그를 생성해서 글번호를 parentNo 속성으로 컨트롤러에 전달 
		var form = document.createElement("form");
		var parentNoInput =  document.createElement("input");
		var id= "${sessionScope.loginUser.id}";
		
		if (!id) {
			alert("ログインが必要です");	
			location.href="login_form.hanul";
		} else {
		form.setAttribute("method", "post");
		form.setAttribute("action", url);
		
		// input 태그 만드는 과정.
		parentNoInput = document.createElement("input");
		parentNoInput.setAttribute("type", "hidden");
		parentNoInput.setAttribute("name", "articleNo");
		parentNoInput.setAttribute("value", articleNo);
		form.appendChild(parentNoInput);
		
		parentNoInput = document.createElement("input");
		parentNoInput.setAttribute("type", "hidden");
		parentNoInput.setAttribute("name", "ref");
		parentNoInput.setAttribute("value", ref);
		form.appendChild(parentNoInput);
		
		parentNoInput = document.createElement("input");
		parentNoInput.setAttribute("type", "hidden");
		parentNoInput.setAttribute("name", "re_step");
		parentNoInput.setAttribute("value", re_step);
		form.appendChild(parentNoInput);
		
		parentNoInput = document.createElement("input");
		parentNoInput.setAttribute("type", "hidden");
		parentNoInput.setAttribute("name", "re_level");
		parentNoInput.setAttribute("value", re_level);
		form.appendChild(parentNoInput);
		
		document.body.appendChild(form);
		
		form.submit();
			
		}	
	}
		
	function readURL(input) { //이미지 미리보기 
		if (input.files && input.files[0]) {
			var reader = new FileReader(); //자바스크립트 io객체 
			reader.onload = function(e) {
				$("#preview").attr("src", e.target.result);				
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	function fn_delete(obj) {
		var result = confirm("この投稿を削除しますか?");
		if (result) {
			obj.action="boardDelete.hanul";
			obj.submit();
		}
	}

	
	
</script>
</head>
<body>
<c:choose>
	<c:when test="${selBoardView.lock_post == 1 }">
			<c:choose>
				<c:when test="${sessionScope.loginUser.id == selBoardView.id }">
					<table id=boardView1 >
						<form name="frm" method="post" enctype="multipart/form-data">
							<table border="0" align="center" id=boardView2>
								<tr>
									<td width="300" align="center" bgcolor="#FF9933">投稿No.</td>
									<td>
										<input type="text" value="${selBoardView.articleNo }" disabled="disabled"/>
										<input type="hidden" value="${selBoardView.articleNo }" name="articleNo"  id="articleNo"/>
									</td>
								</tr>
								<tr>
									<td width="300" align="center" bgcolor="#FF9933">作成者ID</td>
									<td>
										<input type="text" value="${selBoardView.id }" name="id" disabled="disabled"/>
									</td>
								</tr>
								<tr>
									<td width="300" align="center" bgcolor="#FF9933">タイトル</td>
									<td>
										<input type="text" value="${selBoardView.subject }" name="subject" id="i_subject" disabled="disabled"/> 
									</td>
								</tr>
								<tr>
									<td width="150" align="center" bgcolor="#FF9933">内容</td>
									<td>
										<textarea  name="content" id="summernote">${selBoardView.content }</textarea>
									</td>
								</tr>
								<c:if test="${not empty selBoardView.fileName && selBoardView.fileName ne 'null' }">
									<tr>
										<td width="250" align="center" bgcolor="#FF9933" rowspan="2">イメージ</td>
										<td>
											<input type="hidden" name="originalFileName" value="${selBoardView.fileName }">
											<img alt="이미지" src="imagePreView.hanul?articleNo=${selBoardView.articleNo }&fileName=${selBoardView.fileName}" id="preview" style="width:200px; height: 200px;"><br>
										</td>
									</tr>
									<tr>
										<td>
											<input type="file" name="fileName" id="i_fileName" disabled="disabled" onchange="readURL(this);">
										</td>
									</tr>
									<tr>
										<td width="150" align="center" bgcolor="#FF9933">添付ファイル</td>	
										<td><a href="boardDown.hanul?articleNo=${selBoardView.articleNo }&fileName=${selBoardView.fileName}" >&nbsp;&nbsp;${selBoardView.fileName}</a></td>
									</tr>
								</c:if>
								<tr>
									<td width="150" align="center" bgcolor="#FF9933">登録</td>
									<td>
										<input type="text" value="<fmt:formatDate value="${selBoardView.reg_date }" pattern="yyyy-MM-dd HH:mm:ss"/>"  disabled="disabled"/>
									</td>
								</tr>
								<tr id="tr_btn_modify"  >
									<td colspan="2" align="center">
										<input type="button" value="修正完了" onclick="fn_modify_article(frm)">
										<input type="button" value="キャンセル" onclick="backToList(frm)">
									</td>
								</tr>
								<tr id="tr_btn">
									<td colspan="2" align="center">
									<c:choose>
										<c:when test="${empty sessionScope.loginUser}">
										</c:when>
										<c:otherwise>
											<c:if test="${sessionScope.loginUser.id == selBoardView.id}">
												<input type="button" value="修正" onclick="fn_enable(this.form)">
												<input type="button" value="削除" onclick="fn_delete(this.form)">
											</c:if>
										</c:otherwise>
									</c:choose>
										<input type="button" value="目録" onclick="javascript:history.go(-1)">
										<input type="button" value="コメント" onclick="fn_reply_form('boardReplyForm.hanul', ${selBoardView.articleNo}, ${selBoardView.ref}, ${selBoardView.re_step},  ${selBoardView.re_level});">
									</td>
								</tr>
							</table>
						</form>
					</table>
			</c:when>
			<c:otherwise>
				<tr id="#boardView2">
					<td colspan="2" align="center">
						<h1 class="cls2">秘密投稿</h1>
						<h2 class="cls2" style="font-size: 25px;">作成者と管理者以外閲覧できません</h2>
						<div class="button">
							<center><input type="button" value="掲示板へ戻る" onclick="javascrit:history.go(-1)"></center>
						</div>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
			<table id=boardView1 >
				<form name="frm" method="post" enctype="multipart/form-data">
					<table border="0" align="center" id=boardView2>
						<tr>
							<td width="150" align="center" bgcolor="#FF9933">投稿No.</td>
							<td wid>
								<input type="text" value="${selBoardView.articleNo }" disabled="disabled"/>
								<input type="hidden" value="${selBoardView.articleNo }" name="articleNo"  id="articleNo"/>
							</td>
						</tr>
						<tr>
							<td width="150" align="center" bgcolor="#FF9933">作成者ID</td>
							<td>
								<input type="text" value="${selBoardView.id }" name="id" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td width="150" align="center" bgcolor="#FF9933">タイトル</td>
							<td>
								<input type="text" value="${selBoardView.subject }" name="subject" id="i_subject" disabled="disabled"/> 
							</td>
						</tr>
						<tr>
							<td width="150" align="center" bgcolor="#FF9933">内容</td>
							<td>
								<textarea name="content" id="summernote" >${selBoardView.content }</textarea>
							</td>
						</tr>
						<c:if test="${not empty selBoardView.fileName && selBoardView.fileName ne 'null' }">
							<tr>
								<td width="250" align="center" bgcolor="#FF9933" rowspan="2">イメージ</td>
								<td>
									<input type="hidden" name="originalFileName" value="${selBoardView.fileName }">
									<img alt="이미지" src="imagePreView.hanul?articleNo=${selBoardView.articleNo }&fileName=${selBoardView.fileName}" id="preview" style="width:200px; height: 200px;"><br>
								</td>
							</tr>
							<tr>
								<td>
									<input type="file" name="fileName" id="i_fileName" disabled="disabled" onchange="readURL(this);">
								</td>
							</tr>
							<tr>
								<td width="150" align="center" bgcolor="#FF9933">添付ファイル</td>	
								<td><a href="boardDown.hanul?articleNo=${selBoardView.articleNo }&fileName=${selBoardView.fileName}" >&nbsp;&nbsp;${selBoardView.fileName}</a></td>
							</tr>
						</c:if>
						<tr>
							<td width="150" align="center" bgcolor="#FF9933">登録</td>
							<td>
								<input type="text" value="<fmt:formatDate value="${selBoardView.reg_date }" pattern="yyyy-MM-dd HH:mm:ss"/>"  disabled="disabled"/>
							</td>
						</tr>
						<tr id="tr_btn_modify"  >
							<td colspan="2" align="center">
								<input type="button" value="修正完了" onclick="fn_modify_article(frm)">
								<input type="button" value="キャンセル" onclick="backToList(frm)">
							</td>
						</tr>
						<tr id="tr_btn">
							<td colspan="2" align="center">
							<c:choose>
								<c:when test="${empty sessionScope.loginUser}">
								</c:when>
								<c:otherwise>
									<c:if test="${sessionScope.loginUser.id == selBoardView.id}">
										<input type="button" value="修正" onclick="fn_enable(this.form)">
										<input type="button" value="削除" onclick="fn_delete(this.form)">
									</c:if>
								</c:otherwise>
							</c:choose>
								<input type="button" value="目録" onclick="backToList(this.form)"> <!--backToList(this.form)  -->
								<input type="button" value="コメント" onclick="fn_reply_form('boardReplyForm.hanul', ${selBoardView.articleNo}, ${selBoardView.ref}, ${selBoardView.re_step},  ${selBoardView.re_level});">
							</td>
						</tr>
					</table>
				</form>
			</table>
	</c:otherwise>
</c:choose>
<%@ include file="../footer.jsp" %>
</body>
</html>


