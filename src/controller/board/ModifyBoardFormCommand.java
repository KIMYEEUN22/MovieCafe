package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.board.BoardVo;
import model.service.board.BoardService;

public class ModifyBoardFormCommand implements Command{

	
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		int cateNo = Integer.parseInt(req.getParameter("cateNo"));
		System.out.println("카테번호"+cateNo);
		BoardService boardService = BoardService.getInstance();
		BoardVo board = boardService.detailBoard(boardNo);
		
		HttpSession session = req.getSession();
		session.setAttribute("board", board);
		session.setAttribute("cateNo", cateNo);
		return new ActionForward("/indexControl.jsp?contentTemplate=/board/modifyBoardForm", false);
	}
	
}
