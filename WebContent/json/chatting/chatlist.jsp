<%@page import="soju.vo.ChatVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    List<ChatVO> list = (List<ChatVO>)request.getAttribute("list");
    %>
    [
    <%
  
    for(int i=0; i<list.size();i++){
    	ChatVO vo = list.get(i);
    	if(i>0) out.print(",");
    	
    %>
    	{
    		"art_name" : "<%=vo.getArt_name() %>",
    		"chat_cont" : "<%=vo.getChat_cont() %>",
    		"chat_num" : "<%=vo.getChat_num() %>",
    		"chat_time" : "<%=vo.getChat_time() %>",
    		"mem_nick" : "<%=vo.getMem_nick() %>",
    		"photo" : "<%= vo.getPhoto() %>"
   		}
    <%
    }
    %>
    ]