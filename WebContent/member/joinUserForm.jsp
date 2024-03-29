<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="domain.member.UserInfoVo"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<%--joinUserForm.jsp --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
content {
	width: 100%;
}

.join_box {
	display: flex;
	width: 100%;
	justify-content: center;
}

.join_right{
	width: 30em;
}
.join_left{
	width: 45em;
}

.join_title {
	font-size: 36px;
	font-weight: bolder;
	margin: 0.3em 0;
}

.join_notice {
	padding: 3em 0 2em 5em;
}

.join_devide {
	width: 30%;
	height: 4px;
	background-color: black;
}

.join_text {
	margin: 0.25em;
}

.input_section {
	display: flex;
	align-items: center;
}

.input_title {
	margin-right: 1em;
	width: 10em;
}

.input_box {
	height: 1.5rem;
	outline: auto;
	border-radius: 0.25em;
	width: 15em;
}

.input_select{
	display: flex;
}
.input_profile{
	display: flex;
	align-items: center;
	margin-bottom: 3em;
}
.input_radio{
	font-size: 1.3rem;
}
#pickMale, #pickfeMale{
	width: 1.3rem;
	height: 1.3rem;
}

#contact1{
	width: 5em;
}
#contact2, #contact3, #birthYear, #birthMonth, #birthDate{
	width: 6em;
}


button {
	cursor: pointer;
	height: 2rem;
	margin: 0 0.3em;
	font-size: 18px;
	color: #454545;
	background-color: #fdfdfd;
	border: 2px solid #454545;
	border-radius: 0.25em;
	transition: all 150ms ease-in;
}

button:hover {
	background-color: #e2e2e2;
}

#profilePhoto {
	outline: none;
}

.form_imgBtn label {
	margin-top: 1em;
	display: inline-block;
	padding: 0.5em 0.75em;
	color: #404040;
	font-size: inherit;
	line-height: normal;
	vertical-align: middle;
	background-color: #fdfdfd;
	cursor: pointer;
	border: 1px solid #626262;
	border-radius: 0.25em;
}

.form_imgBtn input[type="file"] {
	/* 파일 필드 숨기기 */
	position: absolute;
	width: 1px;
	height: 1px;
	padding: 0;
	margin: -1px;
	overflow: hidden;
	clip: rect(0, 0, 0, 0);
	border: 0;
}

#img {
	border-radius: 50%;
	width: 8rem;
	height: 8rem;
	border: 1px solid;
	margin-right: 5em;
}

.btn_box {
	display: flex;
    flex-direction: unset;
    justify-content: flex-end;
    align-items: end;
    margin-top: 3em;
    text-align: center;
}

.submitBtn {
	width: 3em;
	height: 2em;
	padding: 0.25em;
}
</style>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<script type="text/javascript">
$(document).ready(function() {
			
			<%--프로필 사진 미리보기--%>
			$("#imgInput").on("change", function handleImgFileSelect(e) {
			    let files = e.target.files;
			    let filesArr = Array.prototype.slice.call(files);
			    filesArr.forEach(function (f) {
			      if (!f.type.match("image.*")) {
			        alert("확장자는 이미지 확장자만 가능합니다.");
			        return;
			      }
			      let reader = new FileReader();
			      reader.onload = function (e) {
			        $("#img").attr("src", e.target.result);
			      };
			      reader.readAsDataURL(f);
			    });
			  });
		     
			
			
			<%--아이디 중복 체크 --%>
			$('#idChkBtn').on('click', function(){
				const userId = $('#userId1').val();
				
				if(userId== "") {
					$('#resultId').text('아이디는 필수 입력 항목입니다.');
                	$('#resultId').css('color', 'red');
                	$('#isSubmitBtn').attr("disabled",true);
				}  else {
					idchkProcess('${pageContext.request.contextPath}/checkId.do', userId);	
				};

			});
			
			const getAjax2 = function(url, userId) {
				return new Promise( (resolve, reject) => {
					$.ajax({
						url: url,
						method: 'POST',
						dataType: 'json',
						data: {
							userId: userId
						},
						//콜백함수
						success: function(data) {
							resolve(data);
						},
						error : function(e) {
							reject(e);
						}
					});
				});
			};
			
			async function idchkProcess(url, userId) {
				console.log('userId = ' , userId);
				try {
					const result = await getAjax2(url, userId);
					console.log("result : ", result.isUserId);
					
					
					if(result.isUserId == 'true') {
	                	$('#resultId').text('이미 사용중인 아이디 입니다.');
	                	$('#resultId').css('color', 'red');
	                	
	                	$('#isSubmitBtn').attr("disabled",true);
					} else {
	                	$('#resultId').text('사용 가능한 아이디입니다.');
	                	$('#resultId').css('color', 'blue');
	                	$('#isSubmitBtn').attr("disabled",false);
					}
					} catch (error)  {
						console.log("error : ", error);	
					
					}
				}
						
						
						
			<%--닉네임 중복 체크 --%>
			$('#userNickBtn').on('click', function() {
				const userNick = $('#userNick').val();
				console.log("zz" + userNick);
				if(userNick == "") {
					$('#result').text('닉네임은 필수 입력 항목입니다.');
                	$('#result').css('color', 'red');
                	$('#isSubmitBtn').attr("disabled",true);
				}  else {
					sendProcess('${pageContext.request.contextPath}/checkNick.do', userNick);		
				};
				
				
			});
			
			 const getAjax1 = function(url, userNick) {
		            return new Promise( (resolve, reject) => {
		                $.ajax({                        
		                    url: url,
		                    method: 'POST',
		                    dataType: 'json',
		                    data: {
		                    	userNick: userNick           
		                    },
		                    // 콜백함수
		                    success: function(data) {
		                    	resolve(data);
		                   
		                    }, 
		                    error: function(e) {                    	
		                        reject(e);
		                    }
		                });
		            });
		        };   
			
			
			async function sendProcess(url, userNick) {
				console.log('userNick : ', userNick);
	            try {
	                const result = await getAjax1(url, userNick);	               
	                console.log("result : ", result.isUserNick);    
	                if(result.isUserNick == 'true') {
	                	$('#result').text('이미 사용중인 닉네임입니다.');
	                	$('#result').css('color', 'red');
	                	$('#isSubmitBtn').attr("disabled",true);
	                }  else {
	                	$('#result').text('사용 가능한 닉네임입니다.');
	                	$('#result').css('color', 'blue');
	                	$('#isSubmitBtn').attr("disabled",false);
	                }
	            } catch (error) {
	                console.log("error : ", error);   
	            }
	        }
			
			<%-- 생년월일 선택 부분 구현--%>
			var now = new Date();
			var birthYear = now.getFullYear();
			var birthMonth = (now.getMonth() + 1) > 9 ? ''+(now.getMonth() + 1): '0'+(now.getMonth() + 1);
			var birthDate = (now.getDate()) > 9 ? ''+(now.getDate()) : '0'+ (now.getDate());
			
			for(var i = 1900; i <= birthYear; i++) {
				
				$('#birthYear').append('<option value ="'+ i +'" >' + i + '년</option>')
			}
			for(var i = 1; i <= 12; i++) {
				if (i < 10) {
					$('#birthMonth').append("<option value='0" + i + "'>0" + i+ "</option>");
						} else {
							$('#birthMonth').append("<option value='" + i + "'>" + i+ "</option>");
						}
					}
				
				
				
			for (var i = 1; i <= 31; i++) {
				if (i < 10) {
					$("#birthDate").append(
							"<option value='0" + i + "'>0" + i
									+ "</option>");
				} else {
					$("#birthDate").append(
							"<option value='" + i + "'>" + i
									+ "</option>");
				}
			}
			$("#birthYear > option[value="+birthYear+"]").attr("selected", "true");
			$("#birthMonth > option[value="+birthMonth+"]").attr("selected", "true");
			$("#birthDate > otpion[value="+birthDate+"]").attr("selected", "true");

			
			<%--입력한 비밀번호 재확인 --%>
			
			$('#pwChkBtn').on('click', function() {
				var p1 = document.getElementById('userPwd1').value;
				var p2 = document.getElementById('userPwdCheck1').value;
				if(p1 != p2) {
					pwdcheck.innerText="비밀번호가 일치하지 않습니다. 다시 입력해주세요.";
					pwdcheck.style.color="red";
					$('#isSubmitBtn').attr("disabled",true);
				} else {
					pwdcheck.innerText="비밀번호가 일치합니다.";
					pwdcheck.style.color="blue";
					$('#isSubmitBtn').attr("disabled",false);
				};	
			});
			
			
				<%--전체 유효성 검증 --%>
				$('#isSubmitBtn').on('click', function() {
					
					if($('#userId1').val() == "" || $('#userId1').val() == null ||
					   $('#userPwd1').val() == "" || $('#userPwd1').val() == null ||
					   $('#userEmail').val() == "" || $('#userEmail').val() == null ||
					  // $("#imgInput").val() == "" || $("#imgInput").val() == null ||
					   $('#contact2').val() == "" || $('#contact2').val() == null || 
					   $('#contact3').val() == "" || $('#contact3').val() == null ||
					   $('#userNick').val() == "" || $('#userNick').val() == null ||
					   $('#userName').val() == "" || $('#userName').val() == null )
					  	 {
						console.log($('#userId1').val());
						console.log($('#userPwd1').val());
						console.log($('#userEmail').val());
						console.log($('#imgInput').val());
						console.log($('#contact2').val());
						console.log($('#contact3').val());
						console.log($('#userNick').val());
						console.log($('#userName').val());
						alert("필수 항목을 입력하지 않았습니다. 다시 확인해주세요.");
					} else {
						$('#inputLoginData').submit();	
					}
				})
		});
		
		</script>



<title>회원 가입 폼</title>
</head>
<body>
	<section>
		<div class="join_notice">
			<h1 class="join_title">회원 가입</h1>
			<div class="join_devide"></div>
			<p class="join_text">철수와 영화에 오신 것을 환영합니다.<br>회원 가입을 위해서 아래 내용을 작성해주세요</p>
		</div>

		<form action="${pageContext.request.contextPath}/joinUser"
			method="POST" enctype="multipart/form-data" id="inputLoginData">
			<div class="join_box">
				<div class="join_left">
					<div class="input_section">
						<h3 class="input_title">ID</h3>
						<input class="input_box" type="text" name="userId1" id="userId1">
						<button type="button" id="idChkBtn" name="idChkBtn">확인</button>
					</div>
					<div id="resultId"></div>
					<div class="input_section">
						<h3 class="input_title">Password</h3>
						<input class="input_box" type="password" name="userPwd1"
							id="userPwd1">
					</div>
					<div class="input_section">
						<h3 class="input_title">Password 확인</h3>
						<input class="input_box" type="password" name="userPwdCheck"
							id="userPwdCheck1">
						<button type="button" id="pwChkBtn">확인</button>
					</div>
					<div>
						<span id="pwdcheck"></span>
					</div>
					<div>
						<span class="pwdformchk"></span>
					</div>
					<div class="input_section">
						<h3 class="input_title">E-mail</h3>
						<input class="input_box" type="text" name="userEmail"
							id="userEmail">
					</div>
					<div class="input_section">
						<h3 class="input_title">생년월일</h3>
						<div class="input_box input_select">
							<select id="birthYear" name="birthYear"></select> 
							<select id="birthMonth" name="birthMonth"></select> 
							<select id="birthDate" name="birthDate"></select>
						</div>
					</div>
					<div class="input_section">
						<h3 class="input_title">연락처</h3>
						<div class="input_box input_select">
							<select id="contact1" name="contact1">
								<option value="010">010</option>
							</select> 
							<input type="text" id="contact2" name="contact2"> 
							<input type="text" id="contact3" name="contact3">
						</div>
				
					</div>
					<div class="input_section">
						<h3 class="input_title">닉네임 설정</h3>
						<input class="input_box" type="text" name="userNick" id="userNick">
						<button type="button" id="userNickBtn">확인</button>
						<div id="result"></div>
					</div>
				</div>
				<div class="join_right">
					<div class="input_profile">
						<img id="img">
						<div class="form_imgBtn">
							<label for="imgInput">이미지 등록</label> <input type="file"
								class="form_imgInput" id="imgInput" name="imgInput" />
						</div>
					</div>

					<div class="input_section">
						<h3 class="input_title">이름</h3>
						<input class="input_box" type="text" name="userName" id="userName">
					</div>
					<div class="input_section">
						<h3 class="input_title">성별</h3>
						<div class="input_radio">
							<label for="pickGender">남</label> 
							<input type="radio" name="pickGender" id="pickMale" value="M" checked> 
							<label for="pickFemale">여</label> 
							<input type="radio" name="pickGender" id="pickfeMale" value="F">
						</div>
					</div>
					<div class="btn_box">
						<button class="submitBtn" type="button">취소</button>
						<button class="submitBtn" id="isSubmitBtn" type="button">확인</button>
					</div>
				</div>
			</div>

		</form>
	</section>

</body>
</html>