package soju.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import soju.vo.ArtistVO;
import soju.vo.Bd_likeVO;
import soju.vo.BoardVO;
import soju.vo.CategoryVO;
import soju.vo.FileVO;
import soju.vo.ReplyVO;
import soju.vo.ReportVO;
import soju.vo.Rp_likeVO;
import soju.vo.SubscribeVO;

public interface IBoardDao {
	
	public int getCateNum(CategoryVO vo) throws SQLException;
	
	public ArtistVO getArtist(int num) throws SQLException;
	
	// 사진 전체보기 리스트 - 제목,날짜
	public List<BoardVO> getBoardAll(int num) throws SQLException;
	
	// 사진 상세보기에 필요한 정보
	public List<BoardVO> getDetail(int num) throws SQLException;
	
	// 사진 전체보기 리스트 - 썸네일
	public List<FileVO> getFiles() throws SQLException;
	
	// 게시글 하나에 달린 파일들 가져오기
	public List<FileVO> getDetailFiles(int num) throws SQLException;
	
	// 게시글 추가
	public int insertBoard(BoardVO vo) throws SQLException;
	
	// 파일 추가
	public int addFiles(FileVO vo) throws SQLException;
	
	// 게시글 삭제
	public int deleteBoard(int num) throws SQLException;
	
	// 파일 삭제
	public int deleteFiles(int num) throws SQLException;
	
	// 게시글 수정
	public int updateBoard(BoardVO vo) throws SQLException;
	
	// 댓글 등록
	public int insertReply(ReplyVO vo) throws SQLException;
	
	// 댓글 가져오기
	public List<ReplyVO> getReply(int num) throws SQLException;
	
	// 댓글수 업데이트
	public void updateReplyCount(Map<String, Integer> map) throws SQLException;
	
	// 댓글 삭제
	public int deleteReply(int num) throws SQLException;
	
	// 댓글 수정
	public int updateReply(ReplyVO vo) throws SQLException;
	
	// 신고하기
	public int report(ReportVO vo) throws SQLException;
	
	// 게시글 신고여부
	public int checkBoardReport(ReportVO vo) throws SQLException;
	
	// 댓글 신고여부
	public int checkReplyReport(ReportVO vo) throws SQLException;
	
	public int checkSub(SubscribeVO vo) throws SQLException;
	
	public void insertSub(SubscribeVO vo) throws SQLException;
	
	public int bd_like(Bd_likeVO vo) throws SQLException;
	
	public int rp_like(Rp_likeVO vo) throws SQLException;
	
	public int bd_likeD(Bd_likeVO vo) throws SQLException;
	
	public int rp_likeD(Rp_likeVO vo) throws SQLException;
	
	public List<Integer> checkBdLike(String id) throws SQLException;
	
	public List<Integer> checkRpLike(String id) throws SQLException;
	
	public void updateBdLikeCnt(Map<String, Integer> map) throws SQLException;
	
	public void updateRpLikeCnt(Map<String, Integer> map) throws SQLException;
	
	public int cntUpSub(int num) throws SQLException;
	
}
