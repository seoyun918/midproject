package soju.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import soju.dao.DiQnaDaoImpl;
import soju.dao.IDiQnaDao;
import soju.vo.Direct_QnaVO;



public class DiQnaServiceImpl implements IDiQnaService {

	private IDiQnaDao dao;
	private static IDiQnaService service;
	
	private DiQnaServiceImpl() {
		dao = DiQnaDaoImpl.getDiQnaDao();
	}
	
	public static IDiQnaService getDiQnaService() {
		if(service == null) service = new DiQnaServiceImpl();
		return service;
	}

	//1:1 글쓰기
	@Override
	public int insertDiQna(Direct_QnaVO vo) {
		int num = 0;
		
		try {
			num = dao.insertDiQna(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	
	//내가 쓴 문의 목록
	@Override
	public List<Direct_QnaVO> myQnaList(String dqID) {
		List<Direct_QnaVO> list = null;
		
		try {
			list = dao.myQnaList(dqID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int myQnainsertReply(Direct_QnaVO vo) {
		int num = 0;
		
		try {
			num = dao.myQnainsertReply(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public List<Direct_QnaVO> myQnaReplyList(int bonum) {
		List<Direct_QnaVO> list = null;
		
		try {
			list = dao.myQnaReplyList(bonum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<Direct_QnaVO> adminQnaList() {
		List<Direct_QnaVO> list = null;
		
		try {
			list = dao.adminQnaList();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<Direct_QnaVO> adminQnaList2() {
		List<Direct_QnaVO> list = null;
		
		try {
			list = dao.adminQnaList2();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	




	

	

	

}
