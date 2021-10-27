package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

public class WriteFormCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		
		return new ActionForward("/board/tipWriteForm.jsp", false);
	}

	
}
