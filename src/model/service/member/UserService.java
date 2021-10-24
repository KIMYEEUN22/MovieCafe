package model.service.member;

import java.sql.Connection;

import domain.member.UserInfoVo;
import model.dao.member.DBConn;
import model.dao.member.UserDao;

public class UserService {
	
	private static UserService service;
	
	private UserService() {
		
	}
	
	public static UserService getInstance() {
		if(service == null) {
			service = new UserService();
		}
		return service;
	}
		//ȸ�� ���� ��� ����
		public void registUser(UserInfoVo user) throws Exception {
			
			UserDao userDao = UserDao.getInstance();
			userDao.insertUser(user);
		}
		
		// ȸ�� ���̵� ��ȸ ����
		public boolean checkId(String userId) throws Exception {
				
			
			UserDao userDao = UserDao.getInstance();
			int checkid = userDao.existId(userId);
			if (checkid != 0) {
				return true;
			} else {
				return false;
			}
			
			
		}
			
		
	}
	
	
	


	
