<!DOCTYPE html>
<html lang='ko'>
    <head>
        <meta charset='UTF-8'>
        <title>������</title>
    </head>
    <body>
        <div id="navibox">
            <button type="button" id="sendFormBtn">��������</button>
            <button type="button" id="recieveMsglistBtn">����������</button>
            <button type="button" id="recieveMsglistBtn">����������</button>
        </div>
        <div id="content">
            <form id="MsgboxList" action="${pageContext.request.contextPath }/sendMsg.do" method="POST">
		        <table border="1">
		        	<tr>
		                <td>${recieveId }</td>
		                <td>${sos }zz</td>
		            </tr>
		            
		        </table>
       	 </form>
        </div>


    </body>
</html>