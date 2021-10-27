package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

public class PreviewWriteCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		ActionForward forward = new ActionForward();
	//	return new ActionForward("/indexControl.jsp?contentTemplate=/board/previewWriteForm", false);
		return new ActionForward("/board/previewWriteForm.jsp", false);
	}

	
}
