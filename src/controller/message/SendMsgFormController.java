package controller.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.member.UserInfoVo;

public class SendMsgFormController implements Command {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		UserInfoVo user = (UserInfoVo)session.getAttribute("userInfo");
		String userId = user.getUserId();
		
		
		return new ActionForward("/message/sendMsgForm.jsp", false);
		
	}//execute() end
}// class end
