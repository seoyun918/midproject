<%@page import="soju.vo.ArtistVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArtistVO vo = (ArtistVO)request.getAttribute("artist");
%>
{
	"art_num" : "<%= vo.getArt_num() %>",
	"art_id" : "<%= vo.getArt_id() %>",
	"art_name" : "<%= vo.getArt_name() %>",
	"gr_name" : "<%= vo.getGr_name() %>",
	"art_photo" : "<%= vo.getArt_photo() %>",
	"agc_num" : "<%= vo.getAgc_num() %>",
	"sub_cnt" : "<%= vo.getSub_cnt() %>"
}