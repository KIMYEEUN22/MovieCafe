


package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	// 싱글톤 패턴
	private static CommandFactory factory;
	private Map<String, String> map = new HashMap<String, String>();

	private CommandFactory() {
    
    map.put("/board/listBoard.do", "controller.board.listBoardCommand");

		// 게시판리스트
		// 영화리뷰 게시판 게시글 목록 보기 조회 요청
		map.put("/board/listBoard_MovieReview.do", "controller.board.ListBoardCommand");
		// 시사회정보 게시판 게시글 목록 보기 조회 요청
		map.put("/board/listBoard_sisa.do", "controller.board.ListBoardCommand");
		// 영화관람팁 게시판 게시글 목록 보기 조회 요청
		map.put("/board/listBoard_Tip.do", "controller.board.ListBoardCommand");
		// 티켓마켓 게시판 게시글 목록 보기 조회 요청
		map.put("/board/listBoard_Ticket.do", "controller.board.ListBoardCommand");
		// 이벤트 게시판 게시글 목록 보기 조회 요청
		map.put("/board/listBoard_Event.do", "controller.board.ListBoardCommand");
		// 새싹게시판 게시글 목록 보기 조회 요청
		map.put("/board/listBoard_NewMem.do", "controller.board.ListBoardCommand");

		// 검색
		// 게시글 검색 요청
		map.put("/board/searchBoard.do", "controller.board.SearchBoardCommand");
		// 게시글 상세조회ㅐ 요청
		map.put("/detailBoard.do", "controller.board.DetailBoardCommand");

		// 댓글
		// 댓글 작성 요청
		map.put("/board/writeComment.do", "controller.board.WriteCommentCommand");
		// 댓글 삭제 요청
		map.put("/board/removeComment.do", "controller.board.RemoveCommentCommand");
		// 댓글 수정 요청
		map.put("/board/modifyComment.do", "controller.board.ModifyCommentCommand");

		// 추천 리뷰와 공지사항 조회 요청(메인)
		map.put("/board/recomReview.do", "controller.board.RecomReviewListCommand");

		// 부가기능
		// 게시글 추천 요청
		map.put("/board/recomBoard.do", "controller.board.recomBoardCommand");
		// 게시글 신고 요청
		map.put("/board/reportBoard.do", "controller.board.ReportBoardCommand");

		//게시판 상세보기
		// 게시판 상세보기
		map.put("/board/detailBoard.do", "controller.board.detailBoardCommand");
		// 나의 게시물 보기 요청
		map.put("/board/myBoard.do", "controller.board.MyBoardCommand");
		// 게시글 상세조회ㅐ 요청
		map.put("/board/detailBoard.do", "controller.board.DetailBoardCommand");

		
		// 영화관람 팁 상세보기
		map.put("/board/tipDetailBoard.do", "controller.board.TipDetailBoardCommand");

		// 게시판 작성
		map.put("/board/tipWrite.do", "controller.board.WriteFormCommand");

		// 리뷰게시판
		map.put("/board/reviewWriteForm.do", "controller.board.ReviewWriteFormCommand");

		// 시사회정보게시판
		map.put("/board/previewWriteForm.do", "controller.board.PreviewWriteCommand");

		// 등업게시판
		map.put("/board/rankupWriteForm.do", "controller.board.RankupWriteFormCommand");

		// 티켓게시판
		map.put("/board/ticketWriteForm.do", "controller.board.TicketWriteFormCommand");

		// 이벤트게시판
		map.put("/board/eventWriteBoardForm.do", "controller.board.EventWriteFormCommand");

		// �۾��� ��û
		map.put("/board/writeBoard.do", "controller.board.WriteBoardCommand");

		// 게시판 수정
		map.put("/board/modifyBoardForm.do", "controller.board.ModifyBoardFormCommand");

		// 팁게시판 수정
		map.put("/tipModifyBoardForm.do", "controller.board.TipModifyBoardFormCommand");

		// 게시판 삭제
		map.put("/removeBoard.do", "controller.board.removeBoardCommand");

		// 파일삭제
		map.put("/removeFile.do", "controller.board.RemoveFileCommand");

		// �����Խ���
		map.put("/board/tipBoardForm.do", "controller.board.TipBoardFormCommand");
		// 회원 상세조회요청
		map.put("/modifyUserForm.do", "controller.member.DetailUserCommand");

		// 패스워드 확인 요청
		map.put("/pwdCheck.do", "controller.member.PwdCheckCommand");

		// 닉네임중복체크
		map.put("/checkNick.do", "controller.member.CheckNickNameCommand");

		// 회원 자진탈퇴요청
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

		// 쪽지쓰기 폼 요청
		map.put("/sendMsgForm.do", "controller.message.SendMsgFormController");

		// 쪽지쓰기 요청
		map.put("/sendMsg.do", "controller.message.SendMsgController");

		// 보낸 메시지 목록 요청
		map.put("/sendMsgList.do", "controller.message.SendMsgListController");

		// 보낸 메시지 삭제 요청
		map.put("/removeSendMsg.do", "controller.message.SendMsgRemoveController");

		// 보낸 메시지 상세보기 요청
		map.put("/detailSendMsg.do", "controller.message.SendMsgDetailController");

		// 받은 메시지 목록 요청
		map.put("/receiveMsgList.do", "controller.message.ReceivceMsgListController");

		// 받은 메시지 상세보기 요청
		map.put("/detailReceiveMsg.do", "controller.message.ReceivceMsgDetailController");

		// 받은 메시지 삭제 요청
		map.put("/removeReceiveMsg.do", "controller.message.ReceiveMsgRemoveController");

		// 보낸 메시지 상세보기 페이지에서 삭제 요청
		map.put("/removeDetailSendMsg.do", "controller.message.RemoveDetailSendMsgController");

		// 받은 메시지 상세보기 페이지에서 삭제 요청
		map.put("/removeDetailReceiveMsg.do", "controller.message.RemoveDetailReceiveMsgController");

		// 아이디 중복확인 요청
		map.put("/checkId.do", "controller.member.CheckIdCommand");

		// 로그인 요청 처리
		map.put("/login.do", "controller.member.LoginCommand");

		// 회원 가입 폼 요청
		map.put("/joinUserForm.do", "controller.member.JoinUserFormCommand");

		// 비동기처리로 보낸사람 확인 요청
		map.put("/CheckReceiveId.do", "controller.message.CheckReceiveIdController");

		// 사용자정보조회
		map.put("/listUser.do", "controller.member.ListUserCommand");

		// ID/PWD 찾기에서 아이디 반환 요청
		map.put("/returnId.do", "controller.member.returnIdCommand");

		// ID/PWD 찾기에서 비밀번호 반환 요청
		map.put("/returnPw.do", "controller.member.ReturnPwdCommand");

		// 로그아웃
		map.put("/logout.do", "controller.member.LogoutCommand");

	}

	public static CommandFactory getInstance() {
		if (factory == null) {
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
			Command command = (Command) constructor.newInstance();
			return command;
		} catch (Exception e) {
			throw e;
		}

	}
}

