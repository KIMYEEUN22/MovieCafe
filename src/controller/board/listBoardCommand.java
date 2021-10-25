package controller.board;

import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.board.BoardVo;
import domain.category.CategoryVo;
import model.dao.board.BoardDao;
import model.service.board.BoardService;

public class listBoardCommand implements Command {

	private static final int POST_PER_PAGE = 10;
	private static final int PAGE_BLOCK = 5;
	
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
int currentPage = 0;
		
		try {
			currentPage = Integer.parseInt(req.getParameter("currentPage"));
		} catch (Exception e) {
			// curruntPage�� �������� �ʴ� ���
			currentPage = 1;
		}	
			//���� �������� ������ �Խñ� DB���� �Խñ��� ���� �� ��ȣ�� ���Ѵ�.
			int startRow = (currentPage -1)*POST_PER_PAGE;
		
			System.out.println("startRow = " + startRow);
			
			//3. DB���� �Խñ� ����� ��ȸ�Ѵ�.
			List<BoardVo> boardList = BoardService.getInstance().retrieveBoardList(startRow, POST_PER_PAGE);
			
			System.out.println("bordlistSize = " + boardList.size());
			
			//4. request ������ "boards"�Ӽ��̸����ΰԽñ� ����� �����Ѵ�.
			req.setAttribute("boards", boardList);
		
			
			//3. BLOCK�� ���Ѵ�.
			int currentBlock = currentPage % PAGE_BLOCK == 0 ?  currentPage / PAGE_BLOCK  : currentPage / PAGE_BLOCK +1;
			
			System.out.println(currentBlock);
			
			//4. ���� �������� ���� ������ ����� ���� ������ ��ȣ�� ������ ��ȣ�� ���Ѵ�.
			int startPage = 1 + (currentBlock -1) * PAGE_BLOCK;
			int endPage = startPage + (PAGE_BLOCK -1 );
			
			//5. �� �Խñ� ���� ���Ѵ�.
			int totalPostCount = BoardService.getInstance().retrieveTotalPostCount();
			
			System.out.println(totalPostCount);
			
			//6. �� ������ ���� ���Ѵ�.
			int totalPage = totalPostCount % POST_PER_PAGE == 0 ? totalPostCount / POST_PER_PAGE :
																	totalPostCount / POST_PER_PAGE +1;
																	
			if(endPage > totalPage) {
				endPage =  totalPage;
			}
			
			//7. request ������ ������ ������ �����Ѵ�.
			req.setAttribute("pageBlock", PAGE_BLOCK);
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("totalPostCount", totalPostCount);
			req.setAttribute("postSize", POST_PER_PAGE);
			
			return new ActionForward("listBoard.jsp?currnetPage =" + currentPage,false);
		
	
}
}