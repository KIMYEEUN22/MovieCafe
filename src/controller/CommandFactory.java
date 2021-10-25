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
		
		// ��ȭ �� ��ȸ ��û
		map.put("/detailMovie.do", "controller.movie.DetailMovieCommand");
		
		// ��ȭ ���� ���� �� ��û
		map.put("/modifyMovieForm.do", "controller.movie.ModifyMovieFormCommand");
		
		// ������ �ۼ� �� ��û
		map.put("/registerGuanramForm.do", "controller.movie.RegisterGuanramFormCommand");
		
		// ������ �ۼ� ��û
		map.put("/registerGuanram.do", "controller.movie.RegisterGuanramCommand");
		
		// ������ ���� ��û
		map.put("/removeGuanram.do", "controller.movie.RemoveGuanramCommand");
		
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
