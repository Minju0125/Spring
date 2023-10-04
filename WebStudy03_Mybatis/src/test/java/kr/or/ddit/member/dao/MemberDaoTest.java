package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.MemberVO;

class MemberDaoTest {

	MemberDao dao = new MemberDaoImpl();

	@Test
	void testSelectMemberForAuth() {
		MemberVO inputData = new MemberVO();
		inputData.setMemId("a001");
		inputData.setMemPass("asdfasdf");
		MemberVO saved = dao.selectMemberForAuth(inputData);
		assertNotNull(saved);
	}

	@Test
	void testSelectMemberList() {
		List<MemberVO> memList = dao.selectMemberList(paging);
		assertNotNull(memList);
		assertNotEquals(0, memList);
	}

	@Test
	void testSelectMember() {
		MemberVO member = dao.selectMember("a001");
		assertNotNull(member);
		member.getProdSet().forEach((pv) -> {
			System.out.println(pv.getProdName() + "," + pv.getLprod().getLprodNm());
		});
	}
}
