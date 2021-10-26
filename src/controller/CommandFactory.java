
package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	//싱글톤 패턴
		private static CommandFactory factory;
		private Map<String, String> map = new HashMap<String, String>();
		
		private CommandFactory() {
			//회원 상세조회요청
			map.put("/modifyUserForm.do", "controller.member.DetailUserCommand");	
			
			//패스워드 확인 요청
			map.put("/pwdCheck.do", "controller.member.PwdCheckCommand");
			
			//닉네임중복체크
			map.put("/checkNick.do", "controller.member.CheckNickNameCommand");
			
			//회원 자진탈퇴요청
			map.put("/userDelete.do", "controller.member.DeleteUserCommand");
      
			// 관람평 추천 요청
			map.put("/upLikeGuanram.do", "controller.movie.ModifyGuanramLikeCommand");
      
			// 영화 목록 조회 요청
			map.put("/main.do", "controller.movie.MovieListCommand");
		
			// 영화 삭제 요청
			map.put("/removeMovie.do", "controller.movie.RemoveMovieCommand");
		
			// 영화 상세 조회 요청
			map.put("/detailMovie.do", "controller.movie.DetailMovieCommand");
		
			// 영화 정보 수정 폼 요청
			map.put("/modifyMovieForm.do", "controller.movie.ModifyMovieFormCommand");
		
			// 관람평 작성 폼 요청
			map.put("/registerGuanramForm.do", "controller.movie.RegisterGuanramFormCommand");
		
			// 관람평 작성 요청
			map.put("/registerGuanram.do", "controller.movie.RegisterGuanramCommand");
		
			// 관람평 삭제 요청
			map.put("/removeGuanram.do", "controller.movie.RemoveGuanramCommand");
      
			//쪽지쓰기 폼 요청
			map.put("/sendMsgForm.do", "controller.message.SendMsgFormController");
      
			//쪽지쓰기 요청
			map.put("/sendMsg.do", "controller.message.SendMsgController");
      
			//보낸 메시지 목록 요청
			map.put("/sendMsgList.do", "controller.message.SendMsgListController");
      
			//보낸 메시지 삭제 요청
			map.put("/removeSendMsg.do", "controller.message.SendMsgRemoveController");
      
			//보낸 메시지 상세보기 요청
			map.put("/detailSendMsg.do", "controller.message.SendMsgDetailController");
      
			//받은 메시지 목록 요청
			map.put("/receiveMsgList.do", "controller.message.ReceivceMsgListController");
      
			//받은 메시지 상세보기 요청
			map.put("/detailReceiveMsg.do", "controller.message.ReceivceMsgDetailController");
      
			//받은 메시지 삭제 요청
			map.put("/removeReceiveMsg.do", "controller.message.ReceiveMsgRemoveController");
      
			//보낸 메시지 상세보기 페이지에서 삭제 요청
			map.put("/removeDetailSendMsg.do", "controller.message.RemoveDetailSendMsgController");
      
			//받은 메시지 상세보기 페이지에서 삭제 요청
			map.put("/removeDetailReceiveMsg.do", "controller.message.RemoveDetailReceiveMsgController");     
   
			//아이디 중복확인 요청
			map.put("/checkId.do", "controller.member.CheckIdCommand");
      
			//로그인 요청 처리
			map.put("/login.do", "controller.member.LoginCommand");
		  
			// 회원 가입 폼 요청
			map.put("/joinUserForm.do", "controller.member.JoinUserFormCommand");
      
      //비동기처리로 보낸사람 확인 요청
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
			if (commandClass == null) {
				return null;
			}
			
			try {	
				// 동적 클래스 로딩 후 인스턴스 생성  
				Class<?> cls = Class.forName(commandClass);
				Constructor<?> constructor = cls.getConstructor(null);
				Command command = (Command)constructor.newInstance();
				return command;
			} catch (Exception e) {
				throw e;
			}
			
		}	
	}

