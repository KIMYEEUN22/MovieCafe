package controller.board;

import java.util.Collection;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Part;

import domain.board.BoardFileVo;
import domain.board.BoardVo;
import model.service.board.BoardService;

@MultipartConfig(fileSizeThreshold = 1024, maxFileSize = -1L, maxRequestSize = -1L, location = "/temp")
@WebServlet(urlPatterns = {"/tipBoardWrite"})
public class TipBoardWriteServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");
			
			Collection<Part> parts = request.getParts();
			
			BoardVo board = new BoardVo();
			
			for (Part part : parts) {
				
				if (!part.getHeader("Content-Disposition").contains("filename=")) {
					
					String name = part.getName();
					System.out.println(parts);
					if (name.equals("boardTitle")) {
						board.setBoardTitle(request.getParameter(name));
					}else if (name.equals("userId")) {
						board.setUserId(request.getParameter(name));
					}else if (name.equals("horseNo")) {
						board.setHorseNo(Integer.parseInt(request.getParameter(name)));
					}else if (name.equals("boardContent")) {
						board.setBoardContent(request.getParameter(name));
					}else if (name.equals("boardNotice")) {
						board.setBoardNotice(Integer.parseInt(request.getParameter(name)));
					}else if (name.equals("apiX")) {
						board.setApiX(request.getParameter(name));
					}else if (name.equals("apiY")) {
						board.setApiY(request.getParameter(name));
					}
					
				}else {
					if(part.getSize() != 0) {
						BoardFileVo boardFile = util.board.FileUtils.uploadFile(part);
						board.addBoardFile(boardFile);
					}
				}
				
			}
			
			BoardService boardService = BoardService.getInstance();
			boardService.registerBoardLocation(board);
			
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}

}
