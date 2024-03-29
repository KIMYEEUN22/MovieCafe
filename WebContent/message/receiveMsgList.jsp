<%--receiveMsgList.jsp --%>
<%@page import="domain.message.SendMessageVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

    
<!DOCTYPE html>
<html>
 <head>
        <meta charset='UTF-8'>
        <title>받은 쪽지</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

          <style>
 #removeSendMsgBtn,
 #sendFormBtn,
#sendMsglistBtn,
#recieveMsglistBtn,
#sendMsgBtn {

  height: 2rem;
  margin: 0 0.3em;
  font-size: 18px;
  color: #5b5b5b;
  background-color: #fdfdfd;
  border: 2px solid #b1b1b1;
  border-radius: 0.25em;
  transition: all 150ms ease-in;
  cursor: pointer;
}

#removeSendMsgBtn:hover,
#sendFormBtn:hover,
#sendMsglistBtn:hover,
#recieveMsglistBtn:hover,
#sendMsgBtn:hover {

  background-color: #e2e2e2;
}

#sendMsgList {
  border: #eab48a 4px solid;
  border-radius: 0.5em;

}

.reciveId {
  width: 60%;
  height: 1.7rem;
  line-height: 1.7rem;
}
#plusFrom {
  cursor: pointer;
}

#recieveId,
.content {

  font-size: 16px;

  padding: 0.5em;
  border: 1px solid #b1b1b1;
}
#sendMsgContent {
  width: 300px;
  height: 200px;
}

#removeSendMsgBtn{
	margin-left: 350px;
	color: red;
	
}

section{
margin-left: auto;
margin-right: auto;
}


.bottom {
  text-align: end;
}
</style>
    
    

    </head>
    <body>
    <section>
        <div id="navibox">
            <button type="button" id="sendFormBtn" onclick="location='${pageContext.request.contextPath }/sendMsgForm.do'">쪽지쓰기</button>
            <button type="button" id="sendMsglistBtn" onclick="location='${pageContext.request.contextPath }/sendMsgList.do'">보낸쪽지함</button>
            <button type="button" id="recieveMsglistBtn" onclick="location='${pageContext.request.contextPath }/receiveMsgList.do'">받은쪽지함</button>
        </div>
        <form action="${pageContext.request.contextPath }/removeReceiveMsg.do" method="GET" id="removeReceiveMsgForm">
        <div id="content">
            <div>
		        <table border="1" id="sendMsgList">
		        	<thead>
		              <tr>		              	
		              	<td style="text-align: center;">보낸사람</td>
		              	<td style="text-align: center; width: 100px">내용</td> 
		              	<td>작성시간</td>  
		              	<c:if test="${not empty requestScope.receiveMsgList }"> 
		              	<td><input type="checkbox" id="allChecked">삭제</td>
		              	</c:if>
		              </tr>
		             </thead>
		             <tbody>
				<c:if test="${empty requestScope.receiveMsgList }">
					<tr>
						<td colspan="4">받은 쪽지가 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${not empty requestScope.receiveMsgList }">				
					<c:forEach var="receiveMsg" items="${requestScope.receiveMsgList }" varStatus="loop">	
						<c:url var="detailReceiveMsgUrl" value="/detailReceiveMsg.do">
							<c:param name="receiveMsgNo" value="${receiveMsg.receiveMsgNo }"/><%-- 받은 메세지 번호 --%>
							<c:param name="isRead" value="${receiveMsg.isRead }"/><%-- 읽음확인 정보 --%>
							<c:param name="receiveId" value="${sessionScope.userInfo.userId }"/><%-- 주소록에 수정할 읽은 사람 추후 session으로 바꿔주기 --%>
						</c:url>					
						<tr>				
							<td>
							${receiveMsg.writerId }
							</td>
							<%-- 내용이 10글자가 넘으면 --%>
							<c:if test="${fn:length(pageScope.receiveMsg.receiveMsgContent) >= 11 }">
							<td><a href="${detailReceiveMsgUrl } ">${fn:substring(pageScope.receiveMsg.receiveMsgContent,0,10) }...</a></td>			
							</c:if>					
							<%-- 내용이 10글자가 안넘으면 --%>
							<c:if test="${fn:length(pageScope.receiveMsg.receiveMsgContent) <= 10 }">
							<td><a href="${detailReceiveMsgUrl } ">${fn:substring(pageScope.receiveMsg.receiveMsgContent,0,10) }</a></td>
							</c:if>
							<td>${fn:substring(pageScope.receiveMsg.msgWdate,0,10) }</td>																		<%-- 나중에 session으로 --%>
							<td><input type="checkbox" name="removeCheckBox" value="${pageScope.receiveMsg.receiveMsgNo },${pageScope.receiveMsg.isRead},${requestScope.userId}"></td>			
							<c:if test="${pageScope.receiveMsg.isRead == 0 }">
							<td style="color: orange;">읽지않음 </td>
							</c:if>
							<c:if test="${pageScope.receiveMsg.isRead == 1 }">
							<td>읽음</td>
							</c:if>
						</tr>					
					</c:forEach>
						
				</c:if>
			</tbody>
		        </table>
		        <c:if test="${not empty requestScope.receiveMsgList }"> 
		     <div id="etc">
       	 		<button type="submit" id="removeSendMsgBtn" style="margin-left: 300px">삭제</button>
       	 	</div>
       	 		</c:if>
       	 		<%-- 페이징 처리 --%>
       	 		<div id="paging"  style="text-align:  center; width: 300px">
       	<c:set var="pageBlock" value="${requestScope.pageBlock }" scope="page"/>
		<c:set var="startPage" value="${requestScope.startPage }" scope="page"/>
		<c:set var="endPage" value="${requestScope.endPage }" scope="page"/>
		<c:set var="totalPage" value="${requestScope.totalPage }" scope="page"/>
		<c:set var="currentPage" value="${param.currentPage }" scope="page"/>
		
		<c:if test="${startPage > pageBlock }">
			<c:url var="PrevUrl" value="/receiveMsgList.do">
				<c:param name="currentPage" value="${startPage - pageBlock}"/>
			</c:url>
			<a href="${PrevUrl }">◁</a>
		</c:if>
		
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i == currentPage }">
				&nbsp;${i }&nbsp;
			</c:if>
			<c:if test="${i != currentPage }">
				<c:url var="url" value="/receiveMsgList.do">
					<c:param name="currentPage" value="${i }"/>
				</c:url>
				<a href="${url }">&nbsp;${i}&nbsp;</a>
			</c:if>
		</c:forEach>
		<c:if test="${endPage < totalPage }">
			<c:url var="nextUrl" value="/receiveMsgList.do">
				<c:param name="currentPage" value="${endPage + 1}"/>
			</c:url>
			<a href="${nextUrl }">▷</a>
		</c:if>
       	 		</div>
       		</div>
		</div>
		</form>
		<script>
		<%-- 전체삭제 클릭시  --%>
		$('#allChecked').on('click',function(){
			//allChecked가 체크될시
			if($('#allChecked').is(':checked')){
				//removeCheckBox의 체크를 true로
				 $("input[name=removeCheckBox]").prop("checked",true);
			}else{
				//removeCheckBox의 체크를 false로
				 $("input[name=removeCheckBox]").prop("checked",false);
			}
			
			
		});
		
		</script>
		</section>
    </body>
</html>