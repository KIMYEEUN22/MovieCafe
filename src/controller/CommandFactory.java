package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	private static CommandFactory factory;
	
	private Map<String, String> map = new HashMap<String, String>();
	

	// constructor
	private CommandFactory() {
		map.put("/board/listBoard.do","controller.board.listBoardCommand");
		
		//�˻�
		map.put("/board/searchBoard.do","controller.board.searchBoardCommand");
		
		//�󼼺���
		map.put("/board/detailBoard.do","controller.board.detailBoardCommand");
		
		//�۾��� �������û
		map.put("/board/writeBoardForm.do", "controller.board.WriteFormCommand");
	
		//�۾��� ��û
		map.put("/board/writeBoard.do", "controller.board.WriteBoardCommand");
		
		//�Խñ� ���� ��û
		map.put("/modifyBoardForm.do", "controller.board.ModifyBoardFormCommand");
		
		//�Խñ� ����
		map.put("/removeBoard.do","controller.board.removeBoardCommand");
		
		
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
