<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<head>
<meta charset='UTF-8'>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<title>게시글 쓰기 폼</title>
<style>
body {
	margin: 10px auto;
	width: 500px;
	padding-top: 50px;
	padding-bottom: 30px;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<script>
	$(document).ready(function() {
        		
    	$('#horseNo').on('change', function() {
        	const result = $(this).find('option:selected').val();
        			
          	if(result == 10) {
           		$('#map').show();
            	$('#sample5_address').show();
            	$('#address').show();            			
            } else {
            	$('#map').hide();
            	$('#sample5_address').hide();
            	$('#address').hide();
            }
        });       		
        		
    });
        	
        	
    window.addEventListener('load', function() {
    	const saveBtn = document.getElementById('saveBtn');
    	
    	saveBtn.addEventListener('click', function() {
    	const form = document.getElementById('form');
    	form.submit();
    	}); 
    });

           
</script>
</head>

<body>
	<article>
		<div class="container" role="main">
			<h1>게시판 작성</h1>
			<%-- 게시글작성 --%>
			<form name="form" id="form" role="form" method="POST" action="${pageContext.request.contextPath}/tipBoardWrite"
			 enctype="multipart/form-data">
				<%-- 세션에 있는 아이디  --%>
				<input type="hidden" name = "userId" value="test_user01">
				<%-- 말머리(나중에 수정) --%>
				<div class="mb-3">
					<label>말머리</label> <select name="horseNo" id="horseNo">
						<option value="10" id="discount">영화관 추천</option>
						<option value="11">할인정보</option>
					</select>
				</div>
				<div class="mb-3">
					<input type="text" class="form-control" name="boardTitle"
						id="boardTitle" placeholder="제목을 입력해주세요">
				</div>
				<div class="mb-3">
					<input type="text" class="form-control" name="boardContent"
						id="boardContent" placeholder="내용을 입력해주세요" style="height: 300px;">
				</div>
				<div class="mb-3">
					<label>파일</label> <input type="file" class="form-control"
						name="fileList" multiple>
				</div>
				<div>
					<button type="button" class="btn btn-sm btn-primary" id="saveBtn">글쓰기</button>
					<button type="button" class="btn btn-sm btn-primary" id="listBtn" onclick="location='listBoard.do'">목록</button>
				</div>
				
				<%-- 관리자 아이디 체크박스 --%>
				<input type="hidden" id="apiX" name="apiX"> 
				<input type="hidden" id="apiY" name="apiY">
				
				<div>
			<input type="checkbox" var="1" value ="1" name="boardNotice"><label>공지</label>
			</div>
				
			</form>
			
			


			<input type="text" id="sample5_address" placeholder="주소"> <input
				type="button" id="address" onclick="sample5_execDaumPostcode()"
				value="주소 검색"><br>
			<div id="map"
				style="width: 300px; height: 300px; margin-top: 10px; display: none"></div>

			<script
				src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
			<script
				src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9a78a37acbaa267bade844724b94c806&libraries=services"></script>
			<script>
		    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
		        mapOption = {
		            center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
		            level: 5 // 지도의 확대 레벨
		        };
		
		    //지도를 미리 생성
		    var map = new daum.maps.Map(mapContainer, mapOption);
		    //주소-좌표 변환 객체를 생성
		    var geocoder = new daum.maps.services.Geocoder();
		    //마커를 미리 생성
		    var marker = new daum.maps.Marker({
		        position: new daum.maps.LatLng(37.537187, 127.005476),
		        map: map
		    });
		
		
		    function sample5_execDaumPostcode() {
		       
		    	new daum.Postcode({
		            oncomplete: function(data) {
		                var addr = data.address; // 최종 주소 변수
		
		                // 주소 정보를 해당 필드에 넣는다.
		                document.getElementById("sample5_address").value = addr;
		                // 주소로 상세 정보를 검색
		                geocoder.addressSearch(data.address, function(results, status) {
		                    // 정상적으로 검색이 완료됐으면
		                    if (status === daum.maps.services.Status.OK) {
		
		                        var result = results[0]; //첫번째 결과의 값을 활용
		
		                        // 해당 주소에 대한 좌표를 받아서
		                        var coords = new daum.maps.LatLng(result.y, result.x);
		                        // 지도를 보여준다.
		                        mapContainer.style.display = "block";
		                        map.relayout();
		                        // 지도 중심을 변경한다.
		                        map.setCenter(coords);
		                        // 마커를 결과값으로 받은 위치로 옮긴다.
		                        marker.setPosition(coords);
		                        
		                        document.getElementById("apiX").value = result.x;
		                        document.getElementById("apiY").value = result.y;
		                    }
		                });
		                
		            }
		        }).open();   	
    	
    		}
		</script>
	</div>
	</article>
</body>
</html>