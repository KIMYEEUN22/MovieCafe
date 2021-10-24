package controller.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.message.MsgService;

public class SendMsgRemoveController implements Command {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//�޼��� ���� ��û
		try {
		MsgService service = MsgService.getInstance();
		//���� ��û�� ���� ��ȣ
		String[] removeNos = request.getParameterValues("removeCheckBox");
		for(String no : removeNos) {
		int sendMsgNo = Integer.parseInt(no);
		service.removeMsg(sendMsgNo);
		}//for end
		
		} catch (Exception e) {
			throw e;
		}//end
		
		return new ActionForward("/sendMsgList.do", true);
		
	}//execute() end
}// class end
