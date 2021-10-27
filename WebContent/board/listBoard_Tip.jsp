<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, domain.board.BoardVo"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
	session.setAttribute("userId","test_user01");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화관람 팀 게시글 목록</title>
<style>
table {
	width: 700px;
	border-collapse: collapse;
	margin: 50px auto;
	font-size: 12px;
}

table, tr, th, td {
	border: 2px solid black;
	text-align: center;
}

th, td {
	height: 35px;
}

h3 {
	text-align: center;
}

#paging {
	width: 200px;
	margin: 10px auto;
}

#search {
	margin: 10px auto;
	width: 500px;
}

.highlight {
	background-color: yellow;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>

</head>
<body>
 
	<h3>영화관람 팁 게시판</h3>
	<form action="${pageContext.request.contextPath}/board/searchBoard.do" method="GET">
	<input type="hidden" name= "cateNo" value="${requestScope.cateNo }">
	<div id="search">
		<select name= "keyfield" id="keyfield" style="height: 30px;">
			<option value="all">전체</option>
			<option value="user_id">작성자</option>
			<option value="board_title">제목</option>
			<option value="10">할인 정보</option>
			<option value="11">영화관리뷰</option>
		</select>
	
		
			<input type="search" placeholder="검색어를 입력하세요" size="10" name= "keyword" id="keyword"	>
		
			<button type="submit" id="searchBtn" >검색</button>
		</div>
	</div>
	</form>
	
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일자</th>
				<th>조회</th>
				<th>추천</th>
				<th>댓글</th>
			</tr>
		</thead>
		<tbody>

			<c:if test="${empty requestScope.boards}">
				<tr>
					<td colspan="7">등록된 게시글이 없습니다.</td>
				</tr>
			</c:if>

			<c:if test="${not empty requestScope.boards}">
				<c:forEach var="board" items="${requestScope.boards}"
					varStatus="loop">
					<c:url var="url" value="/board/tipDetailBoard.do">
						<c:param name="boardNo" value="${pageScope.board.boardNo}"></c:param>
						
						<c:param name="cateNo" value="${param.cateNo}"></c:param>
					</c:url>
					<tr>
						<td>${requestScope.totalPostCount - (param.currentPage -1) * requestScope.postSize - loop.index }</td>
						<td>${pageScope.board.userId }</td>
						<c:if test="${pageScope.board.boardNotice==1 }">
						<td style="background-color:yellow;"><a href="${pageScope.url}">${pageScope.board.boardTitle }</a></td>
						</c:if>
						<c:if test="${pageScope.board.boardNotice==0 }">
						<td><a href="${pageScope.url}">${pageScope.board.boardTitle }</a></td>
						</c:if>
						<td>${pageScope.board.boardWdate }</td>
						<td>${pageScope.board.boardCount }</td>
						<td>${pageScope.board.recomCount}</td>
						<td>${pageScope.board.commentCount}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>

	<%-- 페이징 처리 --%>

	<div id="paging">
	<c:set var="cateNo" value="${param.cateNo }" scope="page" />
		<c:set var="pageBlock" value="${requestScope.pageBlock}" scope="page" />
		<c:set var="startPage" value="${requestScope.startPage}" scope="page" />
		<c:set var="endPage" value="${requestScope.endPage}" scope="page" />
		<c:set var="totalPage" value="${requestScope.totalPage}" scope="page" />
		<c:set var="currentPage" value="${param.currentPage }" scope="page" />

		<c:if test="${startPage > pageBlock }">
			<c:url var="prevUrl" value="/board/listBoard_Tip.do">
				<c:param name="cateNo" value="${param.cateNo }"></c:param>
				<c:param name="currentPage" value="${startPage - pageBlock }"></c:param>
			</c:url>
			<a href="${prevUrl}">[Prev]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<c:if test="${i == currentPage}">
			&nbsp;${i} &nbsp;
			</c:if>
			<c:if test="${i != currentPage}">
				<c:url var="url" value="/board/listBoard_Tip.do">
				<c:param name="cateNo" value="${param.cateNo }"></c:param>
					<c:param name="currentPage" value="${i}" />
				</c:url>
				<a href="${url}">&nbsp;${i} &nbsp;</a>
			</c:if>
		</c:forEach>
		<c:if test="${endPage < totalPage}">
			<c:url var="nextUrl" value="/board/listBoard_Tip.do">
			
				<c:param name="cateNo" value="${param.cateNo }"></c:param>
				<c:param name="currentPage" value="${endPage + 1 }"></c:param>
			</c:url>
			<a href="${nextUrl}">[Next]</a>
		</c:if>
	</div>
	
<c:url var="writeUrl" value="/board/tipWrite.do">
				<c:param name="cateNo" value="${param.cateNo}"></c:param>
			</c:url>
			<a href="${writeUrl}">[글쓰기]</a>
</form>

</body>
</html>