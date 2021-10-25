package model.service.member;

import java.sql.Connection;

import domain.member.UserInfoVo;
import model.DBConn;
import model.dao.member.UserDao;


public class UserService {
	// single pattern
		private static UserService service;

		private UserService() {

		}

		public static UserService getInstance() {
			if (service == null) {
				service = new UserService();
			}
			return service;
		}
	
	
	
	//ȸ�� �������� ��ȸ�Ѵ�.
	public UserInfoVo retrieveUser(String userId) throws Exception {
		UserDao userDao = UserDao.getInstance();
		return userDao.selectUser(userId);
	}
	
	//ȸ���� ������ �����Ѵ�.
	public void modifyUser(UserInfoVo user) throws Exception {
		boolean isSuccess = false;
		Connection conn = null;
		try {
			conn = DBConn.getConnection();//�����ͺ��̽�
			conn.setAutoCommit(false);

			UserDao userDao = UserDao.getInstance();
			userDao.updateUser(user, conn);

			isSuccess = true;

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null) {
					if (isSuccess) {
						conn.commit();
					} else {
						conn.rollback();
					}
				}

			} catch (Exception e2) {
				throw e2;
			}
		}
	}
	//ȸ���� ����Ż�� �ϸ� Ż�������� Ż��¥�� ������Ʈ�Ѵ�.
	public void removeUser(String userId) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();//�����ͺ��̽�
			UserDao userDao = UserDao.getInstance();
			userDao.deleteUser(userId, conn);
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null) conn.close();
				
			} catch (Exception e2) {
				throw e2;
			}
		}
	}
	//�г����ߺ��˻縦 �ϴ�.
	public boolean checkNickName(String userNick) throws Exception {
		UserDao userDao = UserDao.getInstance();
		return userDao.confirmNickName(userNick);
		
}
}
