package soju.dao;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import soju.util.SqlMapClientFactory;
import soju.vo.AgencyVO;
import soju.vo.ArtistVO;
import soju.vo.Login_infoVO;
import soju.vo.ManagerVO;
import soju.vo.MemberVO;

public class LoginDaoImpl implements ILoginDao{

	private SqlMapClient smc;
	private static ILoginDao dao;
	
	private LoginDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static ILoginDao getLoginDao() {
		if(dao==null) dao = new LoginDaoImpl();
		return dao;
	}
	
	@Override
	public MemberVO findMember(MemberVO vo) throws SQLException {
		MemberVO findUser = (MemberVO)smc.queryForObject("login.findMember", vo);
		return findUser;
	}

	@Override
	public int ResetPass(MemberVO vo) throws SQLException {
		int cnt = smc.update("login.resetPass", vo);
		return cnt;
	}
	
	@Override
	public int ResetPass2(MemberVO vo) throws SQLException {
		int cnt = smc.update("login.resetPass2", vo);
		return cnt;
	}

	@Override
	public int checkMember(Login_infoVO vo) throws SQLException {
		return (int) smc.queryForObject("login.checkMember", vo);
	}

	@Override
	public MemberVO getLoginInfo(MemberVO vo) throws SQLException {
		return (MemberVO) smc.queryForObject("login.getMemberInfo", vo);
	}

	@Override
	public ArtistVO getLoginInfo(ArtistVO vo) throws SQLException {
		return (ArtistVO) smc.queryForObject("login.getArtistInfo", vo);
	}

	@Override
	public AgencyVO getLoginInfo(AgencyVO vo) throws SQLException {
		return (AgencyVO) smc.queryForObject("login.getAgencyInfo", vo);
	}

	@Override
	public ManagerVO getLoginInfo(ManagerVO vo) throws SQLException {
		return (ManagerVO) smc.queryForObject("login.getManagerInfo", vo);
	}

	

}
