
<%@page import="soju.vo.TicketListVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%
   //ArtBoardList서블릿에서 request의 자료 공유
   
   List<TicketListVO> list = (List<TicketListVO>)request.getAttribute("ticketList");
%> 
[
 <%
    for(int i = 0; i < list.size(); i++){
    	TicketListVO vo = list.get(i);
    	if(i > 0)out.print(",");
  %>
    {
     "tk_num" : "<%= vo.getTic_num() %>",
     "tk_sdate"  :"<%= vo.getTic_sdate() %>",
     "tk_edate"  :"<%= vo.getTic_edate() %>",
     "tk_state"  :"<%= vo.getTic_state() %>",
     "art_Name":"<%= vo.getArt_name() %>"
     
    }
 <%
    }
 %>
]


