<%@page import="soju.vo.NoticeVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<NoticeVO> list = (List<NoticeVO>)request.getAttribute("noticelist");
	Integer spage = (Integer)request.getAttribute("spage");
	Integer tpage = (Integer)request.getAttribute("tpage");
	Integer epage = (Integer)request.getAttribute("epage");
	
%>
{
	"tp" : "<%= tpage %>",
	"sp" : "<%= spage %>",
	"ep" : "<%= epage %>",
	"datas" : [
	<%
		for(int i = 0; i <list.size(); i++){
			NoticeVO vo = list.get(i);
			if(i>0) out.print(",");
	%>
		{
			    "num" : "<%= vo.getNtc_num() %>",
			"subject" : "<%= vo.getNtc_title() %>",
			"content" : "<%= vo.getNtc_cont().replaceAll("\r", "").replaceAll("\n", "<br>") %>",
			  "wdate" : "<%= vo.getNtc_date().substring(0,10) %>",
			 "writer" : "<%= vo.getMng_num() %>"
		}
<% 
	}
%>
]
}
