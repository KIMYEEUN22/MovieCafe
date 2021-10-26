package model.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.board.RecomVo;
import model.DBConn;

public class RecomDao {
	private static RecomDao recomDao;

	private RecomDao() {

	}

	public static RecomDao getInstance() {
		if (recomDao == null) {
			recomDao = new RecomDao();
		}
		return recomDao;
	}
	
	
	//게시글을 추천하다
	
		public void insertRecommend(RecomVo recom) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {

				conn = DBConn.getConnection();

				StringBuffer sql = new StringBuffer();
				sql.append("INSERT INTO recommend (user_id, board_no) ");
				sql.append("VALUES (?, ?) ");
				pstmt = conn.prepareStatement(sql.toString());

				pstmt.setString(1, recom.getRecommender());
				pstmt.setInt(2, recom.getBoardNo());
				
				pstmt.executeUpdate();

				System.out.println("추천 insert완료" + recom.getRecommender());
			} catch (SQLException e) {
				throw e;
			} finally {
				try {
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e2) {
					throw e2;
				}
			}

		}
		
		
		//게시글의 추천수를 구하다,.selectRecomCnt
		public int selectRecomCnt(int boardNo) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int cnt = 0;
			try {

				conn = DBConn.getConnection();

				StringBuffer sql = new StringBuffer();
				sql.append("select count(*)  ");
				sql.append("from recommend left join board	");
				sql.append("using(board_no) ");
				sql.append("where board_no = ?");
				pstmt = conn.prepareStatement(sql.toString());

				pstmt.setInt(1, boardNo);
				
				rs = pstmt.executeQuery();

				while(rs.next()) {
				cnt = rs.getInt(1);
				}
				System.out.println(boardNo+"추천수 조회 완료" + cnt);
			} catch (SQLException e) {
				throw e;
			} finally {
				try {
					if(rs != null) rs.close();
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e2) {
					throw e2;
				}
			}
			return cnt;
		}
		
}
