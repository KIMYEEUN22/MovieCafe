package controller.message;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import controller.ActionForward;
import controller.Command;
import domain.message.AddressVo;
import domain.message.SendMessageVo;
import model.service.message.MsgService;

public class SendMsgController implements Command {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//�Խñ� ���� ��û Ŀ�ǵ�
		
		//HttpSession session = request.getSession();
		//String writer = session.getAttribute("userId"); //�α��� ������ ����� ����
		request.setCharacterEncoding("utf-8"); //���� �� ���߿� �߰��ϱ�
		
		String writer = request.getParameter("userid"); //�׽�Ʈ��
		String receiveId = request.getParameter("reciveId"); //�޴»�� ���̵�
		String sendMsgContent = request.getParameter("sendMsgContent"); //�߽��� ���� ����

		SendMessageVo msgVo = new SendMessageVo();
		ArrayList<String>addrs = new ArrayList<String>(); //����������� ������ ArrayList
		
		
		msgVo.setWriterId(writer);	//�ۼ���
		msgVo.setSendMsgContent(sendMsgContent); //������ �޼���
		//���߿� �ݺ������� �ٲٱ�
		addrs.add(receiveId);
		
		msgVo.setAddress(addrs);
		
		MsgService service = MsgService.getInstance();
		
		service.registerMsg(msgVo);
		
		
		
		
		
		
		
		return new ActionForward("/message/sendMsgList.jsp", true);
		
		
		
		
	}//execute() end
}// class end
