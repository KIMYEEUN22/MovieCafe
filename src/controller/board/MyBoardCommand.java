package controller.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.board.BoardVo;
import model.dao.board.BoardDao;
import model.dao.board.CommentDao;
import model.dao.board.RecomDao;
import model.service.board.BoardService;

public class MyBoardCommand implements Command{
	private static final int POST_PER_PAGE = 10; // ���������� ������ �Խñ� ����
	private static final int PAGE_BLOCK = 5; // ���������� ��� �������� �����ٰ�����
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		//���� ���Ľñ� ��� ��ȸ ��û ó��///
		// 1. ���� ������ ��ȣ�� ���Ѵ�.
				int currentPage = 0;
				try {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				} catch (Exception e) {
					currentPage = 1;
				}

				// 2. ���� �������� ������ �Խñ��� ���� �� ��ȣ�� ���Ѵ�.
				int startRow = (currentPage - 1) * POST_PER_PAGE; // mysql�� ���� 0���� �����ϴϱ� �� �ڿ� +1���ص���

				BoardDao boardDao = BoardDao.getInstance();	
				System.out.println(userId+"�� �Խñ� ��ȸ");
						ArrayList<BoardVo> boards = boardDao.selectMyBoardList(userId, startRow, POST_PER_PAGE);
						
						RecomDao recomDao = RecomDao.getInstance();
						
						for(BoardVo board : boards) {
							int cnt = recomDao.selectRecomCnt(board.getBoardNo());
							board.setRecomCount(cnt);
						}
						
						
						
						//��� ���� ���ϴ�.
						CommentDao commDao = CommentDao.getInstance();
						
						for (BoardVo board : boards) {
							int cnt = commDao.selectCommCnt(board.getBoardNo());
							board.setCommentCount(cnt);
						}
						
						request.setAttribute("boards", boards);
						// 5. block�� ���Ѵ�.
						int currentBlock = currentPage % PAGE_BLOCK == 0 ? currentPage / PAGE_BLOCK : currentPage / PAGE_BLOCK + 1;
						// ?���������� ���ΰ�� �� : �� �� ���̽���.

						// 6. ���� �������� ���� ����������� ���� ������ ��ȣ�� ������ ��ȣ�� ���Ѵ�.
						int startPage = 1 + (currentBlock - 1) * PAGE_BLOCK;
						int endPage = startPage + (PAGE_BLOCK - 1);

						// 7. �� �Խñ� ���� ���Ѵ�.
						int totalPostCount = BoardService.getInstance().retrieveTotalMyPostCount(userId);
						System.out.println(totalPostCount);
						// 8. �� ������ ���� ���Ѵ�.
						int totalPage = totalPostCount % POST_PER_PAGE == 0 ? totalPostCount / POST_PER_PAGE
								: totalPostCount / POST_PER_PAGE + 1;

						if (endPage > totalPage) {
							endPage = totalPage;
						}

						// 9. request ������ ������ ������ �����Ѵ�.

						
						request.setAttribute("pageBlock", PAGE_BLOCK);
						request.setAttribute("startPage", startPage);
						request.setAttribute("endPage", endPage);
						request.setAttribute("totalPage", totalPage);
						request.setAttribute("totalPostCount", totalPostCount);
						request.setAttribute("postSize", POST_PER_PAGE);
						
						return new ActionForward("/indexControl.jsp?contentTemplate=/board/listBoard&currentPage=" + currentPage, false);
	
	}
	
}
