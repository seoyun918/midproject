package soju.fan.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import soju.service.FanServiceImpl;
import soju.service.IFanService;
import soju.vo.TicketVO;

/**
 * Servlet implementation class checkTicket
 */
@WebServlet("/checkTicket.do")
public class checkTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		int memNum = Integer.parseInt(request.getParameter("memNum"));
		int artNum = Integer.parseInt(request.getParameter("artNum"));
		
		TicketVO vo = new TicketVO();
		
		 vo.setMem_num(memNum);
		 vo.setArt_num(artNum);
		
		 IFanService service = FanServiceImpl.getInstance();
		         
		 TicketVO check = service.checkTicket(vo);
		 
		 Gson gs = new Gson();
		 
		 String json = gs.toJson(check);
		 System.out.println(json);
		 
		 out.write(json);
		 response.flushBuffer();
		 
		 
		 
		 
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
