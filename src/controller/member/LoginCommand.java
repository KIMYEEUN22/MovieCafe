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
				System.out.println("userId" + userId);
		
				UserService service = UserService.getInstance();
				
				UserInfoVo userInfoVo = new UserInfoVo(userId, userPwd);
			
				int isMember = service.loginUser(userInfoVo);
				
				if(isMember == 1) {
					
					HttpSession session = request.getSession();
					UserInfoVo user = service.retrieveIdRankNick(userId);				
					
					session.setAttribute("userInfo", user);
					session.setMaxInactiveInterval(30 * 60);
					return new ActionForward("/main.do", true);					
					
				} else {
					response.setContentType("text/html; charset=UTF-8");
					
					PrintWriter out = response.getWriter();
					out.println("<script>alert('아이디/비밀번호가 잘못되었습니다. 다시 확인하고 입력해주세요.'); history.go(-1);</script>");
					out.flush();
					return new ActionForward("/member/loginUserForm.jsp", true);			
				}								
				
			} catch(Exception e) {
				e.getStackTrace();
				request.setAttribute("exception", e);
				RequestDispatcher dis = request.getRequestDispatcher("");
				dis.forward(request, response);
				return null;
				
			}
		
	}

}
