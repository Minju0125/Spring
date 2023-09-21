package kr.or.ddit.adrs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//static import :내가 가지고 있는 메소드인 것처럼 사용가능함
import static org.junit.jupiter.api.Assertions.fail; 

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.AddressVO;

class AddressDAOImplTest {

	AddressDAO dao = new AddressDAOImpl();
	
	@Test
	void testInsertAddress() {
		AddressVO vo = new AddressVO();
		vo.setMemId("a001");
		vo.setAdrsName("테스트");
		vo.setAdrsHp("000-000-0000");
		vo.setAdrsAdd("대전 오류");
		int rowcnt = dao.insertAddress(vo);
		assertEquals(1, rowcnt);
	}
	
	@Test
	void testSelectAddressList() {
		List<AddressVO> adrsList = dao.selectAddressList("a001");
		
		//결과값을 예상할 때 사용하는 메소드 : assert- 계열
		assertNotNull(adrsList); //내 예상은 "null 아님"
		assertNotEquals(0, adrsList.size()); //예상은 adrsList.size()가 0이 아님
	}
	
	@Test
	void testDeleteAddress() {
		int rowcnt = dao.deleteAddress(56);
		assertEquals(1, rowcnt);
	}
	
	@Test
	void testUpdateAddress() {
		AddressVO vo = new AddressVO();
		vo.setAdrsNo(47);
		vo.setAdrsName("하하하");
		vo.setAdrsHp("333-333-3333");
		vo.setAdrsAdd("대전 용두");
		int rowcnt = dao.updateAddress(vo);
		assertEquals(1, rowcnt);
	}

}
