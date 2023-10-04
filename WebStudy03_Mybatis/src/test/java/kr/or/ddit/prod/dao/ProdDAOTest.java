package kr.or.ddit.prod.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.ProdVO;

class ProdDAOTest {
	ProdDAO dao = new ProdDAOImpl();
	@Test
	void testSelectProd() {
		ProdVO prodVO = dao.selectProd("P101000001");
		assertNotNull(prodVO);
	}

}
