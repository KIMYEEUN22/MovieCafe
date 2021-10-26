package controller.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.message.MsgService;

public class ReceiveMsgRemoveController implements Command {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//�޼��� ���� ��û
		try {
		MsgService service = MsgService.getInstance();
		//���� ��û�� ���� ��ȣ �迭
		String[] removeNos = request.getParameterValues("removeCheckBox");
		
		for(String Nos : removeNos) {	
		int no =  Integer.parseInt(Nos.substring(0, Nos.indexOf(","))); //���� ��û�� ���� �� ���� ��ȣ	
		int isRead = Integer.parseInt(Nos.substring(Nos.indexOf(",") + 1, Nos.lastIndexOf(","))); //���� ��û�� ���� �� ������ ���� ����
		String receiveId = Nos.substring(Nos.lastIndexOf(",") + 1, Nos.length());
		System.out.println("no : " + no + " isRead : " + isRead);
		if(isRead == 1) {	
			service.removeReceiveMsg(no);
		}else {
		//���� '��������' ���¿��ٸ� address ���̺� update�ϰ� ����
			service.ReadUpdateRemove(no, receiveId);
			
		}//if end
		
		}//for end
		
		
		
		} catch (Exception e) {
			throw e;
		}//end
		
		return new ActionForward("/receiveMsgList.do", true);
		
	}//execute() end
}// class end
