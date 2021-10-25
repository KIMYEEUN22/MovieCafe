package controller.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.message.MsgService;

public class RemoveDetailSendMsgController implements Command {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//���� ���� �󼼺��� ���������� ���� ��û
		try {
			 int sendMsgNo = Integer.parseInt(request.getParameter("sendMsgNo"));
			 System.out.println(sendMsgNo);
			 //����
			 MsgService service = MsgService.getInstance();
			 service.removeSendMsg(sendMsgNo);
			 
		} catch (Exception e) {
			throw e;
		}// end
		
		 
		
		return new ActionForward("/sendMsgList.do", false);
		
	}//execute() end
}// class end
