package controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.board.CommentVo;
import model.dao.board.CommentDao;

public class WriteCommentCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		try {
			//String userId = "test_user03";
			String comContent = request.getParameter("comContent");
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			System.out.println("��� �� �Խñ� ��ȣ��"+boardNo);
			CommentDao commentDao = CommentDao.getInstance();
			System.out.println("��� ������ :"+comContent);
			commentDao.insertComment(new CommentVo(userId, comContent, boardNo));
			
			List<CommentVo> commentList = commentDao.selectCommentList(boardNo);			
			request.setAttribute("commentList", commentList);

			return new ActionForward("/board/listComment.jsp", false);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
