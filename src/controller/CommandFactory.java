package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	private static CommandFactory factory;
	
	private Map<String, String> map = new HashMap<String, String>();
	
	// constructor
	private CommandFactory() {
		//�������� �� ��û
		map.put("/sendMsgForm.do", "controller.message.SendMsgFormController");
		//�������� ��û
		map.put("/sendMsg.do", "controller.message.SendMsgController");
		//���� �޽��� ��� ��û
		map.put("/sendMsgList.do", "controller.message.SendMsgListController");
		//���� �޽��� ���� ��û
		map.put("/removeSendMsg.do", "controller.message.SendMsgRemoveController");
		//���� �޼��� �󼼺��� ��û
		map.put("/detailSendMsg.do", "controller.message.SendMsgDetailController");
		//���� �޼��� ��� ��û
		map.put("/receiveMsgList.do", "controller.message.ReceivceMsgListController");
		//���� �޼��� �󼼺��� ��û
		map.put("/detailReceiveMsg.do", "controller.message.ReceivceMsgDetailController");
		//���� �޼��� ���� ��û
		map.put("/removeReceiveMsg.do", "controller.message.ReceiveMsgRemoveController");
		//���� �޼��� �󼼺��� ���������� ���� ��û
		map.put("/removeDetailSendMsg.do", "controller.message.RemoveDetailSendMsgController");
		//���� �޼��� �󼼺��� ���������� ���� ��û
		map.put("/removeDetailReceiveMsg.do", "controller.message.RemoveDetailReceiveMsgController");
		//�񵿱�ó���� ������� Ȯ�� ��û
		map.put("/CheckReceiveId.do", "controller.message.CheckReceiveIdController");
	}
	
	public static CommandFactory getInstance() {
		if(factory == null) {
			factory = new CommandFactory();
		}
		return factory;
	}
	
	public Command createCommand(String commandURI) throws Exception {
		String commandClass = map.get(commandURI);
		if(commandClass == null) {
			return null;
		}
		try {
			Class<?> cls = Class.forName(commandClass);
			Constructor<?> constructor = cls.getConstructor(null);
			Command command = (Command) constructor.newInstance();
			return command;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
