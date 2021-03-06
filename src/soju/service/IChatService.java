package soju.service;

import java.util.List;

import soju.vo.ChatVO;


public interface IChatService {
	//채팅리스트
	public List<ChatVO> chatList(String art_name);
	
	//채팅 db에 넣기
	public int insertChat(ChatVO vo);
	
	//채팅 삭제
	public int deleteChat();
	
	//채팅리스트
	public List<ChatVO> chatListArt(String nowTime);
}
