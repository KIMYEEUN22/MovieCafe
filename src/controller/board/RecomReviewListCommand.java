package controller.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.board.BoardVo;
import model.dao.board.BoardDao;
import model.dao.board.CommentDao;
import model.dao.board.RecomDao;

public class RecomReviewListCommand implements Command{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//��õ ���� �� �������� �Խñ� ��� ��ȸ ��û ó��
			
		BoardDao boardDao = BoardDao.getInstance();		
				ArrayList<BoardVo> boards = boardDao.selectRecomRevList();
				ArrayList<BoardVo> noticeBoards = boardDao.selectNoticeList();
				
				RecomDao recomDao = RecomDao.getInstance();
				
				for(BoardVo board : boards) {
					int cnt = recomDao.selectRecomCnt(board.getBoardNo());
					board.setRecomCount(cnt);
				}
				
				for(BoardVo board : noticeBoards) {
					int cnt = recomDao.selectRecomCnt(board.getBoardNo());
					board.setRecomCount(cnt);
				}
				
				//��� ���� ���ϴ�.
				CommentDao commDao = CommentDao.getInstance();
				
				for (BoardVo board : boards) {
					int cnt = commDao.selectCommCnt(board.getBoardNo());
					board.setCommentCount(cnt);
				}
					
				for (BoardVo board : noticeBoards) {
					int cnt = commDao.selectCommCnt(board.getBoardNo());
					board.setCommentCount(cnt);
				}
				request.setAttribute("boards", boards);
				request.setAttribute("noticeBoards", noticeBoards);
				return new ActionForward("/board/recomReview.jsp", false);
	
	}
	
}
