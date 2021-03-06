package soju.main.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import soju.service.INoticeService;
import soju.service.NoticeServiceImpl;
import soju.vo.NoticeVO;

@WebServlet("/NoticeList.do")
public class NoticeList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		int spage = Integer.parseInt(page);
		
		INoticeService service = NoticeServiceImpl.getNoticeService();
		
		Map<String, Integer> map = service.pageInfo(spage);
		
		java.util.List<NoticeVO> list = service.selectByPage(map);
		
		request.setAttribute("noticelist", list);
		request.setAttribute("tpage", map.get("tpage"));
		request.setAttribute("spage", map.get("spage"));
		request.setAttribute("epage", map.get("epage"));
		
		
		request.getRequestDispatcher("json/notice/noticeList.jsp").forward(request, response);
	}
	
}
