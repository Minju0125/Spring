package kr.or.ddit.adrs.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.adrs.dao.AddressDAO;
import kr.or.ddit.adrs.dao.AddressDAOImpl;
import kr.or.ddit.vo.AddressVO;

class AddressServiceImplTest {
	AddressService service = new AddressServiceImpl();
	
	@Test
	void testCreateAddress() {
		AddressVO vo = new AddressVO();
		vo.setMemId("a001");
		vo.setAdrsName("테스트");
		vo.setAdrsHp("000-000-0000");
		vo.setAdrsAdd("대전 오류");
		boolean result = service.createAddress(vo);
		assertEquals(true, result);
	}

	@Test
	void testRetriveAddressList() {
		List<AddressVO> adrsList = service.retriveAddressList("a001");
		//assertNotNull(list);
		assertDoesNotThrow(()->{
			return service.retriveAddressList("b001");
		});
		assertNotNull(adrsList);
	}

	@Test
	void testModifyAddress() {
		AddressVO vo = new AddressVO();
		vo.setAdrsNo(47);
		vo.setAdrsName("하하하");
		vo.setAdrsHp("333-333-3333");
		vo.setAdrsAdd("대전 용두");
		boolean result = service.modifyAddress(vo);
		assertEquals(true, result);
	}

	@Test
	void testRemoveAddress() {
		boolean result = service.removeAddress(57);
		assertEquals(true, result);
	}
}
