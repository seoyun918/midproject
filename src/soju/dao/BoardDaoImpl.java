package soju.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import soju.util.SqlMapClientFactory;
import soju.vo.ArtistVO;
import soju.vo.Bd_likeVO;
import soju.vo.BoardVO;
import soju.vo.CategoryVO;
import soju.vo.FileVO;
import soju.vo.ReplyVO;
import soju.vo.ReportVO;
import soju.vo.Rp_likeVO;
import soju.vo.SubscribeVO;

public class BoardDaoImpl implements IBoardDao{
	private static IBoardDao dao;
	private SqlMapClient smc;
	
	private BoardDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static IBoardDao getInstance() {
		if(dao == null) dao = new BoardDaoImpl();
		
		return dao;
	}
	
	@Override
	public List<BoardVO> getBoardAll(int num) throws SQLException {
		return smc.queryForList("board.getBoardAll", num);
	}

	@Override
	public List<FileVO> getFiles() throws SQLException {
		return smc.queryForList("board.getFiles");
	}

	@Override
	public List<BoardVO> getDetail(int num) throws SQLException {
		return smc.queryForList("board.getDetail", num);
	}

	@Override
	public List<FileVO> getDetailFiles(int num) throws SQLException {
		return smc.queryForList("board.getDetailFiles", num);
	}

	@Override
	public int insertBoard(BoardVO bvo) throws SQLException {
		return (int) smc.insert("board.insertBoard", bvo);
	}

	@Override
	public int addFiles(FileVO fvo) throws SQLException {
		return (int) smc.insert("board.addFiles", fvo);
	}

	@Override
	public int deleteBoard(int num) throws SQLException {
		return smc.delete("board.deleteBoard", num);
	}

	@Override
	public int deleteFiles(int num) throws SQLException {
		return smc.delete("board.deleteFiles", num);
	}

	@Override
	public int updateBoard(BoardVO vo) throws SQLException {
		return smc.update("board.updateBoard", vo);
	}

	@Override
	public int insertReply(ReplyVO vo) throws SQLException {
		return (int) smc.insert("board.insertReply", vo);
	}

	@Override
	public List<ReplyVO> getReply(int num) throws SQLException {
		return smc.queryForList("board.getReply", num);
	}

	@Override
	public void updateReplyCount(Map<String, Integer> map) throws SQLException {
		smc.update("board.updateReplyCount", map);
	}

	@Override
	public int deleteReply(int num) throws SQLException {
		return smc.delete("board.deleteReply", num);
	}

	@Override
	public int updateReply(ReplyVO vo) throws SQLException {
		return smc.update("board.updateReply", vo);
	}

	@Override
	public int report(ReportVO vo) throws SQLException {
		return (int) smc.insert("board.report", vo);
	}

	@Override
	public int checkBoardReport(ReportVO vo) throws SQLException {
		return (int) smc.queryForObject("board.checkBoardReport", vo);
	}

	@Override
	public int checkReplyReport(ReportVO vo) throws SQLException {
		return (int) smc.queryForObject("board.checkReplyReport", vo);
	}

	@Override
	public int getCateNum(CategoryVO vo) throws SQLException {
		return (int) smc.queryForObject("board.getCateNum", vo);
	}

	@Override
	public ArtistVO getArtist(int num) throws SQLException {
		return (ArtistVO) smc.queryForObject("artist.getArtistInfo", num);
	}

	@Override
	public int checkSub(SubscribeVO vo) throws SQLException {
		return (int) smc.queryForObject("board.checkSub", vo);
	}

	@Override
	public void insertSub(SubscribeVO vo) throws SQLException {
		smc.insert("board.insertSub",vo);
	}

	@Override
	public int bd_like(Bd_likeVO vo) throws SQLException {
		return (int) smc.insert("board.bd_like",vo);
	}

	@Override
	public int rp_like(Rp_likeVO vo) throws SQLException {
		return (int) smc.insert("board.rp_like",vo);
	}

	@Override
	public List<Integer> checkBdLike(String id) throws SQLException {
		return smc.queryForList("board.checkBdLike", id);
	}

	@Override
	public List<Integer> checkRpLike(String id) throws SQLException {
		return smc.queryForList("board.checkRpLike", id);
	}

	@Override
	public int bd_likeD(Bd_likeVO vo) throws SQLException {
		return smc.delete("board.bd_likeD", vo);
	}

	@Override
	public int rp_likeD(Rp_likeVO vo) throws SQLException {
		return smc.delete("board.rp_likeD", vo);
	}

	@Override
	public void updateBdLikeCnt(Map<String, Integer> map) throws SQLException {
		smc.update("board.updateBdLikeCnt", map);
	}

	@Override
	public void updateRpLikeCnt(Map<String, Integer> map) throws SQLException {
		smc.update("board.updateRpLikeCnt", map);
	}

	@Override
	public int cntUpSub(int num) throws SQLException {
		return smc.update("board.cntUpSub",num);
	}


	

}
