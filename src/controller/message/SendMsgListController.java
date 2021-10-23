package controller.message;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.message.SendMessageVo;
import model.dao.message.AddressDao;
import model.service.message.MsgService;

public class SendMsgListController implements Command {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		 //���ǿ��� id���� �������� 
		//HttpSession session = request.getSession();
		//String userId = session.getParameter("userId");
		
		//���ÿ��� �׽�Ʈ�ϱ�
		//String userId = request.getParameter("userId");
		String userId = "test_user01";
		
		MsgService service = MsgService.getInstance();
		//������ �޼��� ������ ��ü
		 ArrayList<SendMessageVo> sendMsgList = service.retrieveSendMsgList(userId); 
		
		//request������ sendMsgList�̸����� ������ ������ ��ü ���ε�
		request.setAttribute("sendMsgList", sendMsgList);
		
		return new ActionForward("/message/sendMsgList.jsp", false);
		
	}//execute() end
}// class end
