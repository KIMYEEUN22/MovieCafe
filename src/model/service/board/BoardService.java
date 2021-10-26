package model.service.board;

import java.util.ArrayList;

import domain.board.BoardVo;
import domain.board.RecomVo;
import model.dao.board.BoardDao;
import model.dao.board.RecomDao;


public class BoardService {

	//�̱��� ����
	private static BoardService service;
	
	private BoardService() {
		
	}
	
	public static BoardService getInstance() {
		if (service == null) {
			service = new BoardService();
		}
		return service;
	}
	
	
	//�Խñ� ����� ��ȸ�ϴ�.
	public ArrayList<BoardVo> retrieveBoardList(int cateNo,int startRow, int postSize) throws Exception {
		BoardDao boardDao = BoardDao.getInstance();
		
		//return boardDao.selectBoardList(cateNo,startRow, postSize);
		//�Ʒ��� ������
		ArrayList<BoardVo> boards = boardDao.selectBoardList(cateNo,startRow, postSize);
		System.out.println("retrieve ����: "+boards.size());
		return boards;
	}
	
	//�� �Խñ� ���� ���ϴ�.
		public int retrieveTotalPostCount(int cate_no) throws Exception{
			return BoardDao.getInstance().selectTotalPostCount(cate_no);
		}
		
		/*
		//�˻������ �� �Խñ� ���� ���ϴ�
		
		public int retrieveFindTotalPostCount(int cate_no) throws Exception{
			return BoardDao.getInstance().selectTotalPostCount(cate_no);
		}
		*/
		//�Խñ��� �˻��ϴ�.(keyfield ���� ��)
		public ArrayList<BoardVo> findBoardList(int cateNo, String keyfield, String keyword, int startRow, int postSize) throws Exception {
			ArrayList<BoardVo> boards = BoardDao.getInstance().selectBoardList(cateNo,keyfield, keyword,startRow, postSize);
			System.out.println("findboard��� "+boards.size());
			//return BoardDao.getInstance().selectBoardList(cateNo, keyfield, keyword);
			return boards;
		}
		//�Խñ��� �˻��ϴ�.(��ü �˻���)
				public ArrayList<BoardVo> findBoardList(int cateNo, String keyword, int startRow, int postSize) throws Exception {
					ArrayList<BoardVo> boards = BoardDao.getInstance().selectBoardList(cateNo, keyword,startRow, postSize);
					System.out.println("all findboard��� "+boards.size());
					//return BoardDao.getInstance().selectBoardList(cateNo, keyfield, keyword);
					return boards;
				}
				
				//�Խñ��� �˻��ϴ�.(���Ӹ��� �˻�)
				public ArrayList<BoardVo> findHorseBoardList(int cateNo, String keyfield, String keyword, int startRow, int postSize) throws Exception {
					ArrayList<BoardVo> boards = BoardDao.getInstance().selectHorseBoardList(cateNo,keyfield, keyword,startRow, postSize);
					System.out.println("findHorseboard��� "+boards.size());
					//return BoardDao.getInstance().selectBoardList(cateNo, keyfield, keyword);
					return boards;
				}
				
				//�Խñ��� ����ȸ�ϴ�.
				public BoardVo detailBoard(int boardNo) throws Exception{
					BoardDao boardDao = BoardDao.getInstance();
					BoardVo board =boardDao.selectBoard(boardNo);
					System.out.println("service �󼼺��� �Ϸ�. ��� ������ "+board.getCommentList().size());
					return board;
				}
				
				//�Խñ��� ��õ�ϴ�. recomBoard
				public void recomBoard(RecomVo recom) throws Exception{
					RecomDao recomDao = RecomDao.getInstance();
					recomDao.insertRecommend(recom);
					System.out.println("service ��õ �Ϸ� ");
					
				}
				
}
