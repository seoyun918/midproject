package soju.artist.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import soju.service.BoardServiceImpl;
import soju.service.IBoardService;
import soju.vo.ReplyVO;


@WebServlet("/ArtReplyInsert.do")
public class ArtReplyInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ArtReplyInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		ReplyVO vo = new ReplyVO();
		
	
		try {
			BeanUtils.populate(vo, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
		
		IBoardService service = BoardServiceImpl.getInstance();
		
		Map<String, Integer> map = new HashMap<>();
		
		map.put("bd_Num1", vo.getBd_num());
		map.put("bd_Num2", vo.getBd_num());
		
		int rnum = service.insertReply(vo, map);
		
		request.setAttribute("res", rnum);
		
		request.getRequestDispatcher("feed/result.jsp").forward(request, response);
	}

}
