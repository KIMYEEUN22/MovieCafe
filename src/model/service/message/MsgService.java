package model.service.message;

import java.sql.Connection;
import java.util.ArrayList;

import domain.message.ReceiveMsgVo;
import domain.message.SendMessageVo;
import model.DBConn;
import model.dao.message.AddressDao;
import model.dao.message.ReceiveMSgDao;
import model.dao.message.SendMsgDao;

public class MsgService {
	//�̱������� 
	private static MsgService msgservice;
	
	private MsgService() {
		
	}
	
	public static MsgService getInstance() {
		if(msgservice == null) {
			msgservice = new MsgService();
		}//if end
		return msgservice;
	}//getInstance() end
	
	//���� ���� ����ϱ�
	public void registerMsg(SendMessageVo msgVo) throws Exception {
		ArrayList<String> addrs = new ArrayList<String>();	//����������� ������ ArrayList
		Connection conn = null;
		boolean isSucess = false;	//Ʈ����� ó���� ���� ��
		try {
			conn = DBConn.getConnection();
			
			conn.setAutoCommit(false); //Ʈ����� ����
			
			SendMsgDao sendmsgDao = SendMsgDao.getInstance(); //SendMsgDao ��ü����
			//������ ���� ���� DB�� ����
			int sendMsgNo = sendmsgDao.insertMessage(conn, msgVo);
			
			
			//���� ������� �����ϱ�
			addrs = msgVo.getAddress();
			AddressDao addrDao = AddressDao.getInstance(); //AddressDao ��ü����
			
			for(String addr : addrs) {
				addrDao.insertAddr(conn, sendMsgNo, addr);
			}//for end
			
			//�޴»�� �����Կ� ���� ����ϱ�
			ReceiveMSgDao rmsdao = ReceiveMSgDao.getInstance(); //ReceiveMSgDao ��ü����
			for(String addr : addrs) {
				rmsdao.insertMessage(conn, msgVo.getSendMsgContent(), addr, sendMsgNo, msgVo.getWriterId());
			}//for end
			
			isSucess = true;
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if(conn != null) {
					if(isSucess) {
						conn.commit();
						System.out.println("��");
					}else{
						conn.rollback();
						System.out.println("�ȵ�");
					}//if end
					conn.close();
				}//if end
			} catch (Exception e2) {
				throw e2;
			}// end
		
		}// end
		
		
	}//registerMsg() end
	
	//���� �� ���� ��ȸ�ϱ�
	public ArrayList<SendMessageVo> retrieveSendMsgList(String userId) throws Exception {
		ArrayList<SendMessageVo> sendMsgList = new ArrayList<SendMessageVo>(); //��ȯ�� �޼��� ������ ���� ArrayList
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			SendMsgDao sendmsgDao = SendMsgDao.getInstance(); //SendMsgDao ��ü����
			//�޼��� ��ȸ method
			sendMsgList = sendmsgDao.selectSendmsg(conn, userId);
			
		} catch (Exception e) {
			throw e;
		}finally {
			if(conn != null) conn.close();
		}// end
		
		return sendMsgList;
	}//retrieveSendMsgList() end
	
	//�������� �����ϰ� ���� �ּҷ� ����
	public void removeMsg(int sendMsgNo) throws Exception {
		Connection conn = null;
		boolean isSucess = false;	//Ʈ����� ó���� ���� ��
		try {
			conn = DBConn.getConnection();
			
			conn.setAutoCommit(false); //Ʈ����� ����
			
			//�ּҷϿ��� ����
			AddressDao addrDao = AddressDao.getInstance(); //AddressDao ��ü����
			addrDao.deleteAddress(conn, sendMsgNo);
			
			
			SendMsgDao sendmsgDao = SendMsgDao.getInstance(); //SendMsgDao ��ü����
			//������ ���� ���� ����
			sendmsgDao.deleteSendMsg(conn, sendMsgNo);
			
			
			
			isSucess = true;
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if(conn != null) {
					if(isSucess) {
						conn.commit();
						System.out.println("��");
					}else{
						conn.rollback();
						System.out.println("�ȵ�");
					}//if end
					conn.close();
				}//if end
			} catch (Exception e2) {
				throw e2;
			}// end
		
		}// end
		
	}//removeMsg() end
	
	//���� �� ����, ���ſ��� ��ȸ�ϱ�
	public SendMessageVo retrieveSendMsg(int sendMsgNo) throws Exception {
		SendMessageVo sendMs = new SendMessageVo(); //��ȯ�� �޼��� ������ ���� ��ü
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			SendMsgDao sendmsgDao = SendMsgDao.getInstance(); //SendMsgDao ��ü����
			//�޼��� ��ȸ method
			sendMs = sendmsgDao.selectSendMsg(conn, sendMsgNo);
			
		} catch (Exception e) {
			throw e;
		}finally {
			if(conn != null) conn.close();
		}// end
		
		return sendMs;
	}//retrieveSendMsg() end
	
	
	//���� �� �޼��� ��� ��ȸ
	public ArrayList<ReceiveMsgVo> retrieveReceiveMsgList(String userId) throws Exception {
		ArrayList<ReceiveMsgVo> receiveMsgList = new ArrayList<ReceiveMsgVo>(); //��ȯ�� �޼��� ������ ���� ArrayList
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			ReceiveMSgDao receiveMsgDao = ReceiveMSgDao.getInstance(); //ReceiveMSgDao ��ü����
			//���� �� �޼��� ��ȸ method
			receiveMsgList = receiveMsgDao.selectReceiveMsgList(conn, userId);
			
		} catch (Exception e) {
			throw e;
		}finally {
			if(conn != null) conn.close();
		}// end
		
		return receiveMsgList;
	}//retrieveSendMsgList() end
	
	//���� �� �޼��� ����
	public void removeReceiveMsg(int receiveMsgNo) throws Exception {
		Connection conn = null;
		
		try {
			//ReceiveMSgDao ��ü ����
			ReceiveMSgDao rmd = ReceiveMSgDao.getInstance();
			//���Կ� �޼����� ����
			rmd.deleteReceiveMsg(conn, receiveMsgNo);
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e2) {
				throw e2;
			}
		}// end
		
		
	}//removeReceiveMsg() end
	
	//�������� �������� �ٲٰ� ���Կ� �޼��� �����ϱ�
	public void ReadUpdateRemove(int receiveMsgNo, String receiveId) throws Exception {
		Connection conn = null;
		boolean isSucess = false;	//Ʈ����� ó���� ���� ��
		try {
		conn = DBConn.getConnection();
		conn.setAutoCommit(false); //Ʈ����� ����
		
		AddressDao addrDao = AddressDao.getInstance(); //AddressDao ��ü ����
		ReceiveMSgDao rmd = ReceiveMSgDao.getInstance(); //ReceiveMSgDao ��ü ����
		//����Ȯ�� ���¸� �������� ����
		addrDao.Readed(conn, receiveMsgNo, receiveId);
		//���� ��û�� ���� �� ���� ��ȣ �´� ���� ����
		rmd.deleteReceiveMsg(conn, receiveMsgNo);
		
		isSucess = true;
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if(conn != null) {
					if(isSucess) {
						conn.commit();
						System.out.println("��");
					}else{
						conn.rollback();
						System.out.println("�ȵ�");
					}//if end
					conn.close();
				}//if end
			} catch (Exception e2) {
				throw e2;
			}// end
		}//end
		
	}//ReadUpdate() end
	
	//���� �� �޼��� �� ��ȸ�ϰ� �������� update
		public ReceiveMsgVo retrieveReceiveMsg(int receiveMsgNo) throws Exception {	
			Connection conn = null;
			try {
				conn = DBConn.getConnection();
				conn.setAutoCommit(false); // Ʈ����� ����
				ReceiveMSgDao receiveMsgDao = ReceiveMSgDao.getInstance(); //ReceiveMSgDao ��ü����
				//���� �� �޼��� ��ȸ method
				ReceiveMsgVo rmv = receiveMsgDao.selectReceiveMsg(conn, receiveMsgNo);
				return rmv;
			} catch (Exception e) {
				throw e;
			}finally {
				try {
				if(conn != null) conn.close();
				} catch (Exception e2) {
				throw e2;
				}// end
			}// end
		}//retrieveSendMsg() end
	
	//���� ���¸� true�� ����
	public void updateRead(int sendMsgNo, String receiveId) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			
			AddressDao addrDao = AddressDao.getInstance();
			addrDao.Readed(conn, sendMsgNo, receiveId);
			
		} catch (Exception e) {
			throw e;
		}finally {
			try {	
				if(conn != null) conn.close();			
			} catch(Exception e2) {
				throw e2;
			}// end
		} //end
	}//updateRead() end
	
	
	
}// class end
