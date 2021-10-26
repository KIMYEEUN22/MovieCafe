package controller.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.dao.message.ReceiveMSgDao;

public class CheckReceiveIdController implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String receiveId = request.getParameter("userId");
		
		//DB���� �Է¹��� ���̵� ��ȸ�� ���� return���ֱ�
		int resultCount = ReceiveMSgDao.getInstance().selectId(receiveId);
		System.out.println(resultCount);
		request.setAttribute("resultCount", resultCount);	
		return new ActionForward("/message/countReceiveId.jsp", false);
		
		
		
	}//execute() end
	
}//class end
