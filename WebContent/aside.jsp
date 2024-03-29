<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Aside</title>
<%--  jQuery Load --%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous">
	
</script>
<script src="https://kit.fontawesome.com/69749f5203.js"
	crossorigin="anonymous"></script>

<style>
button {
	font-size: 16px;
	color: #888787;
	background-color: #fdfdfd;
	border: 2px solid #888787;
	border-radius: 0.25em;
	transition: all 150ms ease-in;
	cursor: pointer;
}

button:hover {
	background-color: #e2e2e2;
}

.aside_login {
	padding: 1em;
	font-weight: 400;
}

.login_divide {
	background-color: #ed7c31;
	width: 100%;
	height: 5px;
}

.login_orangeBox {
	padding: 0.3em;
	background-color: #ed7c31;
	border-radius: 30px;
}

.login_whiteBox {
	padding: 1em;
	background-color: white;
	border-radius: 30px;
	text-align: center;
}

.login_box {
	padding: 1em;
}

.login_icon {
	width: 2em;
	height: 2em;
}

.login_title {
	font-size: 28px;
	font-weight: 700;
}

.login_input {
	margin-top: 1em;
	text-align-last: justify;
}

.login_inputBox {
	width: 8em;
	outline: auto;
}

.login_btnBox {
	margin-top: 1em;
	text-align: center;
}

.login_btn {
	margin: 0 1em;
}

.aside_category {
	padding: 0.8em;
}

.category_orangeBox {
	padding: 0.3em;
	background-color: #ed7c31;
	border-radius: 30px;
}

.category_whiteBox {
	padding: 2em;
	background-color: white;
	border-radius: 30px;
	test-align: center;
}

.category_section {
	display: flex;
	align-items: center;
}

.categoty_title {
	cursor: pointer;
	color: #ed7c31;
}

.categoty_title:hover {
	color: #f9da78;
}

.category_icon {
	width: 2em;
	height: 2em;
	margin-left: 0.5em;
}

.login_profile {
	width: 4rem;
	height: 4rem;
	border-radius: 50%;
}

.user_info_text {
	text-align: center;
	margin: 0.25em;
}

.user_icon {
	font-size: 24px;
	margin: 0 0.25em;
	cursor: pointer;
	transition: transform 200ms linear;
}

.user_icon:hover {
	transform: scale(1.1);
}

i {
	color: black;
}

.btn_box {
	display: flex;
	flex-direction: column;
	height: 6rem;
	justify-content: space-around;
}
</style>

</script>
<script>
	$(document)
			.ready(
					function() {

						$('#MsgHomeBtn')
								.on(
										'click',
										function() {
											window
													.open(
															'${pageContext.request.contextPath}/sendMsgList.do',
															'_blank',
															'height=600, width=700, resizable=no');
										});

					});
</script>
</head>
<body>
	<div class="aside_login">
		<div class="login_orangeBox">
			<div class="login_whiteBox">
				<c:if test="${empty userInfo.userId }">

					<img src="images/login.png" alt="login_icon" class="login_icon" />
					<span class="login_title">로그인</span>
					<div class="login_divide"></div>

					<form action="${pageContext.request.contextPath}/login.do"
						method="POST">
						<div class="login_input">
							<span>아이디</span> <input class="login_inputBox" type="text"
								name="userId" id="userId" />
						</div>
						<div class="login_input">
							<span>비밀번호</span> <input class="login_inputBox" type="password"
								name="userPwd" id="userPwd" />
						</div>
						<div class="login_btnBox">
							<button type="submit" class="login_btn" value="submit">로그인</button>
							<c:url var="joinUrl" value="/joinUserForm.do"></c:url>
							<a href="${joinUrl }">
								<button type="button" class="login_btn">회원가입</button>
							</a>
						</div>

					</form>

				</c:if>

				<c:if test="${not empty userInfo.userId }">
					<c:if test="${empty userInfo.photoSys }">
						<img src="upload/user/profile.png" alt="profile"
							class="login_profile" />
					</c:if>
					<c:if test="${not empty userInfo.photoSys }">
						<img src="upload/user/${userInfo.photoSys }" alt="profile"
							class="login_profile" />
					</c:if>

					<p class="user_info_text">아이디 : ${userInfo.userId }</p>
					<p class="user_info_text">닉네임 : ${userInfo.userNick }</p>
					<p class="user_info_text">가입일자 : ${userInfo.joindate }</p>

					<div class="btn_box">
						<c:if test="${not userInfo.rankType.equals('A') }">
							<div>
								<button class="userInfo_btn">마이페이지</button>
							</div>
							<div>
								<button id="MsgHomeBtn">쪽지 보관함</button>
							</div>
							<c:url var="logoutUrl" value="/logout.do"></c:url>
							<a href="${logoutUrl }">
								<button class="logout_btn">로그아웃</button>
							</a>
						</c:if>
						<c:if test="${userInfo.rankType.equals('A') }">
							<c:url var="userInfoUrl" value="/listUser.do"></c:url>
							<a href="${userInfoUrl }">
								<button class="userInfo_btn">사용자 정보 조회</button>
							</a>
							<div>
								<button id="MsgHomeBtn">쪽지 보관함</button>
							</div>
							<c:url var="logoutUrl" value="/logout.do"></c:url>
							<a href="${logoutUrl }">
								<button class="logout_btn">로그아웃</button>
							</a>
						</c:if>
					</div>
				</c:if>


			</div>
		</div>

	</div>

	<div class="aside_category">
		<div class="category_orangeBox">
			<div class="category_whiteBox">
				<div class="category_section">
					<c:url var="listUrl" value="/listBoardNewMem.do">
						<c:param name="cateNo" value="1"></c:param>>
            		</c:url>
					<h2 class="categoty_title">
						<a href="${listUrl}"> 새싹 게시판</a>
					</h2>

					<img src="images/sprout.png" alt="category_icon"
						class="category_icon" />
				</div>
				<div class="category_section">
					<c:url var="listUrl" value="/listBoardMovieReview.do">
						<c:param name="cateNo" value="2"></c:param>
					</c:url>
					<h2 class="categoty_title">
						<a href="${listUrl}">영화리뷰</a>
					</h2>

					<img src="images/review.png" alt="category_icon"
						class="category_icon" />
				</div>
				<div class="category_section">
					<c:url var="listUrl" value="/listBoardsisa.do">
						<c:param name="cateNo" value="3"></c:param>
					</c:url>
					<h2 class="categoty_title">
						<a href="${listUrl}">시사회정보</a>
					</h2>
					<img src="images/theater.png" alt="category_icon"
						class="category_icon" />
				</div>
				<div class="category_section">
					<c:url var="listUrl" value="/listBoardTip.do">
						<c:param name="cateNo" value="4"></c:param>
					</c:url>
					<h2 class="categoty_title">
						<a href="${listUrl}">영화관람 팁</a>
					</h2>
					<img src="images/tip.png" alt="category_icon" class="category_icon" />
				</div>
				<div class="category_section">
					<c:url var="listUrl" value="/listBoardTicket.do">
						<c:param name="cateNo" value="5"></c:param>
					</c:url>
					<h2 class="categoty_title">
						<a href="${listUrl}">티켓마켓</a>
					</h2>
					<img src="images/ticket.png" alt="category_icon"
						class="category_icon" />
				</div>
				<div class="category_section">
					<c:url var="listUrl" value="/listBoardEvent.do">
						<c:param name="cateNo" value="6"></c:param>
					</c:url>
					<h2 class="categoty_title">
						<a href="${listUrl}">이벤트</a>
					</h2>
					<img src="images/party.png" alt="category_icon"
						class="category_icon" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>