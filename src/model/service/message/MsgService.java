package model.service.message;

import java.sql.Connection;
import java.util.ArrayList;

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
	public void retrieveMsg(SendMessageVo msgVo) throws Exception {
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
				rmsdao.insertMessage(conn, msgVo.getSendMsgContent(), addr, sendMsgNo);
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
		
		
	}//retriveMsg() end
	
	
	
	
}// class end
