package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	private static CommandFactory factory;
	
	private Map<String, String> map = new HashMap<String, String>();
	
	// constructor
	private CommandFactory() {
		// ��ȭ ��� ��ȸ ��û
		map.put("/main.do", "controller.movie.MovieListCommand");
		
		// ��ȭ ���� ��û
		map.put("/removeMovie.do", "controller.movie.RemoveMovieCommand");
		
		//���̵� �ߺ�Ȯ�� ��û
		map.put("/checkId.do", "controller.member.CheckIdCommand");
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
