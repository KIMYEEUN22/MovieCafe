
package model.service.member;

import java.sql.Connection;
import java.util.ArrayList;

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
  
  //관리자가 회원 정보 조회를 한다.
	public ArrayList<UserInfoVo> retrieveUserList(int startRow, int postSize) throws Exception {
		UserDao userDao = UserDao.getInstance();
		return userDao.selectUserList(startRow, postSize);
	}

	

	// 총 회원의 수를 구한다.
	public int retrieveUserTotalCount() throws Exception {
		return UserDao.getInstance().selectUserTotalCount();
	}

		//회원 정보 등록 서비스
		public void registUser(UserInfoVo user) throws Exception {
			
			UserDao userDao = UserDao.getInstance();
			userDao.insertUser(user);
		}
		
		// 아이디 중복검사를 하다.
		public boolean checkId(String userId) throws Exception {
				UserDao userDao = UserDao.getInstance();
				return userDao.existId(userId);

		}
		
		
		// 회원의 아이디, 회원등급, 닉네임을 조회한다.
		public UserInfoVo retrieveIdRankNick(String userId) throws Exception {
				UserDao userDao = UserDao.getInstance();
				return userDao.selectUserIdNickRank(userId);
		}
		
		
		// 로그인
		public int loginUser(UserInfoVo userInfoVo) throws Exception {
			
			try {
				UserDao userDao = UserDao.getInstance();
				
				int checkIdPwd = userDao.selectCountUser(userInfoVo);
				
				System.out.println("checkIdPwd = " + checkIdPwd);
				// checkIdPwd = 1 -> 로그인 가능
				// checkIdPwd = 0 -> 로그인 불가 = 계정 없음
				return checkIdPwd;
				
			} catch (Exception e) {
				throw e;
			} 
	
		}	

	
	//회원 상세정보를 조회한다.
	public UserInfoVo retrieveUser(String userId) throws Exception {
		UserDao userDao = UserDao.getInstance();
		return userDao.selectUser(userId);
	}
	
	//회원의 정보를 변경한다.
	public void modifyUser(UserInfoVo user) throws Exception {
		boolean isSuccess = false;
		Connection conn = null;
		try {
			conn = DBConn.getConnection();//데이터베이스
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
	//회원이 자진탈퇴를 하면 탈퇴유형과 탈퇴날짜를 업데이트한다.
	public void removeUser(String userId) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();//데이터베이스
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
	//닉네임중복검사를 하다.
	public boolean checkNickName(String userNick) throws Exception {
		UserDao userDao = UserDao.getInstance();
		return userDao.confirmNickName(userNick);
		
}
}

