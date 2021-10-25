package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	//�̱��� ����
		private static CommandFactory factory;
		private Map<String, String> map = new HashMap<String, String>();
		
		private CommandFactory() {
			//ȸ�� ����ȸ��û
			map.put("/modifyUserForm.do", "controller.member.DetailUserCommand");	
			
			//�н����� Ȯ�� �䫊
			map.put("/pwdCheck.do", "controller.member.PwdCheckCommand");
			
			//�г����ߺ�üũ
			map.put("/checkNick.do", "controller.member.CheckNickNameCommand");
			
			//ȸ�� ����Ż���û
			map.put("/userDelete.do", "controller.member.DeleteUserCommand");
		}
		
		
		public static CommandFactory getInstance() {
			if(factory == null) {
				factory = new CommandFactory();
			}
			return factory;
		}
		
		
		public Command createCommand(String commandURI) throws Exception {
			String commandClass = map.get(commandURI);
			if (commandClass == null) {
				return null;
			}
			
			try {	
				// ���� Ŭ���� �ε� �� �ν��Ͻ� ����  
				Class<?> cls = Class.forName(commandClass);
				Constructor<?> constructor = cls.getConstructor(null);
				Command command = (Command)constructor.newInstance();
				return command;
			} catch (Exception e) {
				throw e;
			}
			
		}	
	}
