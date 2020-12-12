<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 
<c:set var="selAllBoardList" value="${selAllBoardList }"/>
<c:set var="boardListAllCnt" value="${boardListAllCnt }"/>
<c:set var="section" value="${section }"/>
<c:set var="pageNum" value="${pageNum }"/> --%>
<%@ include file="../header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板</title>
<script src="js/jQuery/jquery-3.4.1.min.js"></script>
<style type="text/css">
	.cls1 {
		text-decoration: none;
		color: black;
	}
	 a:hover {
		font-weight:bold;
		text-decoration: underline;
		color:#FF0000;
	}
	.cls2 {
		text-align: center;
		font-size: 30px;
		margin-top: 50px;
	}
	
	.no-uline {
		text-decoration: none;
	}
	
	.sel-page {
		text-decoration: none;
		color: red;
	}
	
</style>
<script type="text/javascript">
	function boardWrite() {
		var id= "${sessionScope.loginUser.id}";
			if (!id) {
				alert("ログインが必要です");	
				location.href="login_form.hanul";
			} else {
				location.href="boardForm.hanul";
			}	
		}
	function fn_searchList() {
		
		if($("#searchKeyword").val().trim().length == 0){
			alert("검색어를 입력하세요.");
			$("#searchKeyword").focus();
		} else {
			document.frm.action = "boardList.hanul";
			document.frm.submit();
		}
	}
	
</script>
</head>
<body>
	<h1 class="cls2">掲示板</h1>
	<table align="center" border="1" style="border-collapse: collapse;" width="80%">
		<thead>
			<tr height="10" align="center" bgcolor="lightgray">
				<th>投稿No.</th>
				<th>作成者</th>
				<th>タイトル</th>
				<th>日付</th>
				<th>ヒット</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty boardInfo.selAllBoardList }">
						<tr height="10">
							<th colspan="5">
								<p align="center">
								<b><span>登録された投稿がありません。</span></b>
								</p>
							</th>
						</tr>
				</c:when>
				<c:when test="${!empty boardInfo.selAllBoardList }">
					<c:forEach items="${boardInfo.selAllBoardList }" var="boardListDto" varStatus="status">
						<tr height="10">
							<td width="5%">${boardListDto.articleNo }</td>
							<td width="20%">${boardListDto.id }</td>
							<td style="text-align: left;" width="45%">
								<span style="padding-left: 10px;"></span>
								<c:choose>
									<c:when test="${boardListDto.re_level > 1}">
										<c:choose>
											<c:when test="${boardListDto.show == 'y' }">
												<c:choose>
													<c:when test="${boardListDto.lock_post == 1 }">
														<c:forEach begin="2" end="${boardListDto.re_level}">
															<span style="padding-left: 10px;"></span>
														</c:forEach>	
														<span style="font-size: 12px;">[回答]</span>
														<a class="cls1" href="boardView.hanul?articleNo=${boardListDto.articleNo}">${boardListDto.subject }</a>
														<img alt="lock" src="images/lock.png" width="15" height="15" />
													</c:when>
													<c:otherwise>
														<c:forEach begin="2" end="${boardListDto.re_level}">
															<span style="padding-left: 10px;"></span>
														</c:forEach>	
														<span style="font-size: 12px;">[回答]</span>
														<a class="cls1" href="boardView.hanul?articleNo=${boardListDto.articleNo}">${boardListDto.subject }</a>
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<c:forEach begin="2" end="${boardListDto.re_level}">
													<span style="padding-left: 10px;"></span>
												</c:forEach>	
												<span style="font-size: 12px;">[回答]</span>
												<a class="cls1" style="color:gray; " href="javascript:void(0)">[削除されました。]</a>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${boardListDto.show == 'y' }">
												<c:choose>
													<c:when test="${boardListDto.lock_post == 1 }">
														<a class="cls1" href="boardView.hanul?articleNo=${boardListDto.articleNo}">${boardListDto.subject }</a>
														<img alt="lock" src="images/lock.png" width="15" height="15" />
													</c:when>
													<c:otherwise>
														<a class="cls1" href="boardView.hanul?articleNo=${boardListDto.articleNo}">${boardListDto.subject }</a>
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<a class="cls1" style="color:gray; " href="javascript:void(0)">[削除されました。]</a>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</td>
							<td width="20%"><fmt:formatDate value="${boardListDto.reg_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td width="10%">${boardListDto.readcount }</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5" align="center" >
					<!-- 페이징 -->
					<div class="cls2">
						<c:if test="${!empty boardInfo.boardListAllCnt }">
							<c:choose>
								<c:when test="${boardInfo.boardListAllCnt > 100}">
									<c:forEach var="page" begin="1" end="10" step="1" >
											<c:if test="${boardInfo.section > 1 && page == 1 }">
												<a class="no-uline" href="boardList.hanul?section=${boardInfo.section-1 }&pageNum=${10}&searchType=${boardInfo.searchType}&searchKeyword=${boardInfo.searchKeyword}">&nbsp;pre</a>
											</c:if>
											<c:if test="${(boardInfo.boardListAllCnt/10+1) > ((boardInfo.section-1)*10 + page) }"> 
												<a class="no-uline" href="boardList.hanul?section=${boardInfo.section}&pageNum=${page}&searchType=${boardInfo.searchType}&searchKeyword=${boardInfo.searchKeyword}">${(boardInfo.section-1)*10 + page }</a>
											</c:if>
											<c:if test="${page == 10 && boardInfo.boardListAllCnt/(boardInfo.section*10) > 10}">
												<a class="no-uline" href="boardList.hanul?section=${boardInfo.section+1}&pageNum=${1}&searchType=${boardInfo.searchType}&searchKeyword=${boardInfo.searchKeyword}">&nbsp;next</a>
											</c:if>					
									</c:forEach>
								</c:when>
								<c:when test="${boardInfo.boardListAllCnt == 100 }">
									<c:forEach var="page" begin="1" end="10" step="1">
										<a class="no-uline" href="boardList.hanul?section=${boardInfo.section}&pageNum=${page}&searchType=${boardInfo.searchType}&searchKeyword=${boardInfo.searchKeyword}">${page}</a> 
									</c:forEach>
								</c:when>
								<c:when test="${boardInfo.boardListAllCnt < 100 }">
									<c:forEach var="page" begin="1" end="10" step="1">
										<c:choose>
											<c:when test="${page == boardInfo.pageNum }">
												<c:if test="${(boardInfo.boardListAllCnt/10+1) > ((boardInfo.section-1)*10 + page) }">
													<a class="sel-page" href="boardList.hanul?section=${boardInfo.section}&pageNum=${page}&searchType=${boardInfo.searchType}&searchKeyword=${boardInfo.searchKeyword}">${page}</a>
												</c:if>
											</c:when>
											<c:otherwise>
												<c:if test="${(boardInfo.boardListAllCnt/10+1) > ((boardInfo.section-1)*10 + page) }">
													<a class="no-uline" href="boardList.hanul?section=${boardInfo.section}&pageNum=${page}&searchType=${boardInfo.searchType}&searchKeyword=${boardInfo.searchKeyword}">${page}</a>
												</c:if>
											</c:otherwise>
										</c:choose>
									</c:forEach>				
								</c:when>
							</c:choose>		
						</c:if>
					</div>
				</td>	
			</tr>
			<tr>
				<td colspan="5" align="center" >
					<form action="boardList.hanul" name="frm" method="get">
						<select name="searchType" id="searchType">
							<option value="t" <c:out value="${boardInfo.searchType == 't'? 'selected': '' }" />>タイトル</option>
							<option value="c" <c:out value="${boardInfo.searchType == 'c'? 'selected': '' }" />>内容</option>
							<option value="w" <c:out value="${boardInfo.searchType == 'w'? 'selected': '' }" />>作成者</option>
							<option value="tc"<c:out value="${boardInfo.searchType == 'tc'? 'selected': '' }" />>タイトル+内容</option>
							<option value="cw"<c:out value="${boardInfo.searchType == 'cw'? 'selected': '' }" />>内容+作成者</option>
							<option value="tcw"<c:out value="${boardInfo.searchType == 'tcw'? 'selected': '' }" />>タイトル+内容+作成者</option>
						</select>
						<input type="text" name="searchKeyword" id="searchKeyword" value="${boardInfo.searchKeyword}" >
						<input type="button" value="검색" onclick="fn_searchList()" >
<!-- 						<input type="submit" value="검색" onclick="fn_searchList()"> -->
					</form>
				</td>
			</tr>
		</tfoot>
	</table>
	<a class="cls1" href="javascript:boardWrite();">
		<p class="cls2">新規投稿を追加</p>
	</a>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>