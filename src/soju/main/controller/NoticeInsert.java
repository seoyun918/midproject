package soju.main.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import soju.service.INoticeService;
import soju.service.NoticeServiceImpl;
import soju.vo.NoticeVO;

@WebServlet("/NoticeInsert.do")
public class NoticeInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		NoticeVO vo = new NoticeVO();
		
		try {
			BeanUtils.populate(vo, request.getParameterMap());
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		
		INoticeService service = NoticeServiceImpl.getNoticeService();
		
		int num = service.insertNotice(vo);
		
		request.setAttribute("res", num);
		
		request.getRequestDispatcher("json/notice/result.jsp").forward(request, response);
		
		
		
		
	}

}
