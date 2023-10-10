package kr.or.ddit.member.service;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfo;
import lombok.extern.slf4j.Slf4j;
@Slf4j
class MemberServiceImplTest {

	MemberService service = new MemberServiceImpl();
	
	@Test
	void testCreateMember() {
		fail("Not yet implemented");
	}

	@Test
	void testRetrieveMember() {
		fail("Not yet implemented");
	}

	@Test
	void testRetrieveMemberList() {
		PaginationInfo paging = new PaginationInfo();
		paging.setCurrentPage(2);
		List<MemberVO> dataList = service.retrieveMemberList(paging);
		log.info("paging : {}", paging);
		log.info("dataList's size : {}", dataList);
	}

	@Test
	void testModifyMember() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveMember() {
		fail("Not yet implemented");
	}

}
