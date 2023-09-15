package pmproject.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import pmproject.dao.EPDAO;
import pmproject.dao.MemberDAO;
import pmproject.vo.MemberVO;
import pmproject.vo.TransferVO;

public class EPServiceImp implements EPService{

	private EPDAO epDao;
	private MemberDAO memberDao;
	
	private final String MYBATIS_CONFIG_PATH = "pmproject/config/mybatis-config.xml";
	
	public EPServiceImp() {
		try {
			InputStream is = Resources.getResourceAsStream(MYBATIS_CONFIG_PATH);
			SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
			SqlSession session = sf.openSession(true);
			epDao = session.getMapper(EPDAO.class);
			memberDao = session.getMapper(MemberDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<MemberVO> getEPList() {
		List<MemberVO> epList = epDao.selectEPList();
		if(epList == null) {
			return null;
		}
		for(MemberVO tmp : epList) {
			epList.get(epList.indexOf(tmp)).setEp_dept(memberDao.selectDept(epList.get(epList.indexOf(tmp)).getEp_dm_num()));
			epList.get(epList.indexOf(tmp)).setEp_pos(memberDao.selectPos(epList.get(epList.indexOf(tmp)).getEp_po_num()));
			epList.get(epList.indexOf(tmp)).setEp_st(memberDao.selectSt(epList.get(epList.indexOf(tmp)).getEp_st_num()));
		}
		
		return epDao.selectEPList();
	}

	@Override
	public boolean deleteEP(MemberVO ep) {
		if(ep == null || ep.getEp_name() == null) {
			return false;
		}
		
		int stNum = 2;

		if(epDao.deleteEP(ep.getEp_id(), stNum)) {
			LocalDate date = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String today = date.format(formatter);
			epDao.insertTm(ep.getEp_id(), today);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateEP(MemberVO ep) {
		if(ep == null || ep.getEp_name() == null) {
			return false;
		}
		
		MemberVO dbEp = epDao.selectEP(ep.getEp_id());
	
		if(dbEp == null) {
			return false;
		}

		dbEp.setEp_name(ep.getEp_name());
		dbEp.setEp_email(ep.getEp_email());
		dbEp.setEp_phone_num(ep.getEp_phone_num());
		dbEp.setEp_dm_num(ep.getEp_dm_num());
		dbEp.setEp_po_num(ep.getEp_po_num());
		dbEp.setEp_st_num(ep.getEp_st_num());
		dbEp.setEp_leave(ep.getEp_leave());
		dbEp.setEp_salary(ep.getEp_salary());
		
		if(epDao.updateEP(dbEp) != 0) {
			return true;
		}
		return false;
	}
}