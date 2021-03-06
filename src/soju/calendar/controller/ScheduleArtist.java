package soju.calendar.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import soju.service.CalendarServiceImpl;
import soju.service.ICalendarService;

/**
 * Servlet implementation class ScheduleArtist
 */
@WebServlet("/scheduleArtist.do")
public class ScheduleArtist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String agcId = request.getParameter("agcId");
		//System.out.println(agcId);
		ICalendarService service = CalendarServiceImpl.getInstance();
		ArrayList<HashMap<String, Object>> artistList = service.artistList(agcId);
		request.setAttribute("res", artistList);
		request.getRequestDispatcher("json/calender/calArtist.jsp").forward(request, response);;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
