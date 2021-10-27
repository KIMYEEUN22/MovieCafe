package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

public class ReviewWriteFormCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		forward.setPath("/board/reviewWriteForm.jsp");
		return forward;
		
		
		
	}

	
	
	
}
