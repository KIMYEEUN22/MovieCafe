package controller.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.message.ReceiveMsgVo;
import model.dao.message.AddressDao;
import model.service.message.MsgService;

public class ReceivceMsgDetailController implements Command {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//���� �� ���� �󼼺��� ��û
		//���Կ� ���� ��ȣ
		int receiveMsgNo = Integer.parseInt(request.getParameter("receiveMsgNo"));
		System.out.println(receiveMsgNo);
		//�޼��� Ȯ�� ����
		int isRead = Integer.parseInt(request.getParameter("isRead"));
		System.out.println(isRead);
		//address�� ���ſ��� �ٲ� id
		String receiveId = request.getParameter("receiveId");
		System.out.println(receiveId);
		
		
		try {
			MsgService service = MsgService.getInstance(); //service ��ü ����
			
		
		//���� ������ Ȯ������ �ʾҴٸ� DB�� Ȯ������ update
		if(isRead == 0) {
			service.updateRead(receiveMsgNo, receiveId);
		}//if end
		
		//���� ������ �������� request������ ���ε�
		ReceiveMsgVo rmv = service.retrieveReceiveMsg(receiveMsgNo);
		rmv.setReceiveMsgNo(receiveMsgNo); //���� ���� ��ȣ
		request.setAttribute("receiveMsg", rmv);
		
		} catch (Exception e) {
			throw e;
			
		}//end
		
		
		return new ActionForward("/message/receiveMsgDetail.jsp", false);
		
	}//execute() end
}// class end
