package controller.member;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.member.UserInfoVo;
import model.service.member.UserService;

public class LoginCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			try {
				String userId = request.getParameter("userId");
				String userPwd = request.getParameter("userPwd");
				
				System.out.println("userId : " + userId);
				System.out.println("userPwd : " + userPwd);
				
				
				// �α��� Ȯ�� �κ�
				UserService service = UserService.getInstance();
				
				UserInfoVo userInfoVo = new UserInfoVo(userId, userPwd);
				// ȸ��, ���̵� ������ 1, ������ 0
				int isMember = service.loginUser(userInfoVo);
				System.out.println(isMember);
				
				// �α��� Ȯ�� �κ� ��				
				
				// ������ ���� ��� ���ǿ��� ȸ�� ���� ������ ��
				if(isMember == 1) {
					HttpSession session = request.getSession();
					UserInfoVo user = service.retrieveIdRankNick(userId);
					
					
					session.setAttribute("userInfo", user);
					
					session.setMaxInactiveInterval(30 * 60);
					System.out.println("����");
					return new ActionForward("/indexControl.jsp?contentTemplate=main", false);
					
					
				} else {
					System.out.println("����");
					return new ActionForward("/indexControl.jsp?contentTemplate=main", false);
					
				}				
				
				
			} catch(Exception e) {
				e.getStackTrace();
				request.setAttribute("exception", e);
				RequestDispatcher dis = request.getRequestDispatcher("");
				dis.forward(request, response);
				return null;
				
			}
			// ���� ��.
	}

}
