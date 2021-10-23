package model.dao.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import domain.message.SendMessageVo;

public class AddressDao {
	
	private static AddressDao addrdao;
	
	private AddressDao() {}
	
	public static AddressDao getInstance() {
		if(addrdao == null){
			addrdao = new AddressDao();
		}//if end
		
		return addrdao;
	}//getInstance() end
	
	//����� ���� ���� ����� ���
	public void insertAddr(Connection conn, int sendMsgNo ,String receiveId) throws Exception {
		
		PreparedStatement pstmt = null;

		try {
		
		StringBuffer sql = new StringBuffer();
		
		//���� ���� ����
		sql.append(" INSERT INTO address(send_msg_no, receive_id) ");
		sql.append(" VALUES (?, ?) ");
		
		pstmt = conn.prepareStatement(sql.toString());
		
		pstmt.setInt(1, sendMsgNo);
		pstmt.setString(2, receiveId);
		
		pstmt.executeUpdate();
	
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				
				if(pstmt != null) pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}//end
		}// end
			
	}//insertAddr() end
	
	//�޼����� ���� ����� ��ȸ
	public ArrayList<String> selectAddr(Connection conn, int sendMsgNo) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> receiveIds = new ArrayList<String>();
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT receive_id ");
			sql.append(" FROM address ");
			sql.append(" WHERE send_msg_no = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, sendMsgNo);
			
			rs = pstmt.executeQuery();
			//rs���� �� ��������
			while(rs.next()) {
			String receiveId = rs.getString(1);
			
			receiveIds.add(receiveId);
			}//if end
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			} catch (Exception e2) {
				throw e2;
			}// end
		}// end
		
		return receiveIds;
		
	}//selectMsg() end
	
}// class end
