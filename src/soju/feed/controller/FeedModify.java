package soju.feed.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import soju.service.FeedServiceImpl;
import soju.service.IFeedService;
import soju.vo.BoardVO;


@WebServlet("/FeedModify.do")
public class FeedModify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//가져올 값  bd_num, cont
		BoardVO vo = new BoardVO();
		vo.setBd_num(Integer.parseInt(request.getParameter("num")));
		vo.setBd_cont(request.getParameter("modc"));
		
		
		IFeedService service = FeedServiceImpl.getService();
		
		int cnt = service.modifyContent(vo);
		
		request.setAttribute("res", cnt);
		request.getRequestDispatcher("json/feed/result.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
