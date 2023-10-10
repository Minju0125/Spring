package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.MemberVO;

class MemberDaoImplTest {
	
	MemberDao dao = new MemberDaoImpl();
	
	//@Test
	void testSelectMemberForAuth() {
		MemberVO inputData = new MemberVO();
		inputData.setMemId("a001");
		inputData.setMemPass("asdfasdf");
		MemberVO saved = dao.selectMemberForAuth(inputData);
		assertNotNull(saved);
	}
	
	@Test
	void testSelectMemberList() {
		List<MemberVO> memList =  dao.selectMemberList();
		assertNotNull(memList);
		assertNotEquals(0, memList);
	}
}
