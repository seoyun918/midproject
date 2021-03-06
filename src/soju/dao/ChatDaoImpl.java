package soju.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import soju.util.SqlMapClientFactory;
import soju.vo.ChatVO;



public class ChatDaoImpl implements IChatDao{
	private SqlMapClient smc;
	private static IChatDao dao;
	
	private ChatDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static IChatDao getInstance() {
		if(dao == null) dao = new ChatDaoImpl();
		return dao;
	}

	@Override
	public List<ChatVO> chatList(String art_name) throws SQLException {
		List<ChatVO> chatlist = null;
		chatlist = smc.queryForList("chat.chatList", art_name);
		return chatlist;
	}

	@Override
	public int insertChat(ChatVO vo) throws SQLException {
		int res = 0;

		if(smc.insert("chat.insertChat", vo)==null) {
			res=1;
		}		
		
		return res;
	}

	@Override
	public int deleteChat() throws SQLException {
		
		return smc.delete("chat.deleteChat");
	}

	@Override
	public List<ChatVO> chatListArt(String nowTime) throws SQLException {
		List<ChatVO> chatlist = null;
		chatlist = smc.queryForList("chat.chatList", nowTime);
		return chatlist;
	}
}
