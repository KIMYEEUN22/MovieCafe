package controller.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.board.BoardVo;
import model.service.board.BoardService;

public class ListBoardCommand implements Command {
	private static final int POST_PER_PAGE = 10; // ���������� ������ �Խñ� ����
	private static final int PAGE_BLOCK = 5; // ���������� ��� �������� �����ٰ�����

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//����¡ ó��
		//int cateNo = Integer.parseInt(request.getParameter("cateNo"));
		int cateNo = 2;
				//1. ���� ������ ��ȣ�� ���Ѵ�.
				int currentPage = 0;
				try {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				} catch (Exception e) {
					currentPage = 1;
				}
				
				//2. ���� �������� ������ �Խñ��� ���� �� ��ȣ�� ���Ѵ�.
				int startRow = (currentPage - 1) * POST_PER_PAGE;	//mysql�� ���� 0���� �����ϴϱ� �� �ڿ� +1���ص���
				
				//3. DB���� �Խñ� ����� ��ȸ�Ѵ�.
				ArrayList<BoardVo> boards = BoardService.getInstance().retrieveBoardList(cateNo,startRow, POST_PER_PAGE);
				//ArrayList<BoardVo> boards = BoardService.getInstance().retrieveBoardList(2,startRow, POST_PER_PAGE);
				//�����
				System.out.println("retrieve��� "+boards.size());
				//4. request ������ "boards"��� �Ӽ��̸����� �Խñ� ����� �����Ѵ�.
				//�ֳĸ� �̰� listBoard.jsp���� �����ְ� �Ⱦ� �Ӽ��̴ϱ� requset������ ����
				request.setAttribute("boards", boards);
				
				//5. block�� ���Ѵ�.
				int currentBlock = currentPage % PAGE_BLOCK == 0 ? currentPage / PAGE_BLOCK : currentPage /PAGE_BLOCK + 1; 	
				//?���������� ���ΰ�� �� : ��  �� ���̽���.
				
				//6. ���� �������� ���� ����������� ���� ������ ��ȣ�� ������ ��ȣ�� ���Ѵ�.
				int startPage = 1+ (currentBlock - 1) * PAGE_BLOCK;
				int endPage = startPage + (PAGE_BLOCK - 1);
				
				//7. �� �Խñ� ���� ���Ѵ�.
				int totalPostCount = BoardService.getInstance().retrieveTotalPostCount(cateNo);
				System.out.println(totalPostCount);
				//8. �� ������ ���� ���Ѵ�.
				int totalPage = totalPostCount % POST_PER_PAGE == 0 ? totalPostCount / POST_PER_PAGE : 
																		totalPostCount / POST_PER_PAGE + 1 ;
				
				if(endPage > totalPage) {
					endPage = totalPage;
				}
				
				//9. request ������ ������ ������ �����Ѵ�.
				request.setAttribute("cateNo", cateNo);
				request.setAttribute("pageBlock", PAGE_BLOCK);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("totalPostCount", totalPostCount);
				request.setAttribute("postSize", POST_PER_PAGE);
				// list.board.jsp�� �̵�~
				return new ActionForward("/board/listBoard.jsp?currentPage="+currentPage, false);		
			}
			

}
