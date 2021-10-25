package controller.message;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.message.ReceiveMsgVo;
import model.service.message.MsgService;

public class ReceivceMsgListController implements Command {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//���� ���� ��� ��û
		//�α��� ��� �����Ǹ� 
		//HttpSession session = request.getSession();
		//String userId = (String)session.getAttribute("userId");
		//���ÿ��� �׽�Ʈ
		String userId = "test_user02";
		
		//MsgService ��ü ����
		MsgService service = MsgService.getInstance();
		//���� �� �޼��� ��� ��ȸ method ȣ��
		ArrayList<ReceiveMsgVo>	receiveMsgList =  service.retrieveReceiveMsgList(userId);
		
		//request������ receiveMsgList�̸����� ���Կ� ���� ���� ���ε�
		request.setAttribute("userId", userId);
		request.setAttribute("receiveMsgList", receiveMsgList);

		return new ActionForward("/message/receiveMsgList.jsp", false);
		
	}//execute() end
}// class end
