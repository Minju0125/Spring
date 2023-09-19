package kr.or.ddit.servlet06.service;

import java.time.LocalDateTime;
import java.util.List;

import kr.or.ddit.servlet06.dao.DataBasePropertyDAO;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyServiceImpl implements DataBasePropertyService {

	private DataBasePropertyDAO dao = new DataBasePropertyDAO();
	
	@Override
	public List<DataBasePropertyVO> retrieveDBPropertyList() {
		List<DataBasePropertyVO> list = dao.selectDBPropertyList();
		//call by reference 구조
		for(DataBasePropertyVO vo : list) {
			String rawData = vo.getDescription();
			vo.setDescription(String.format("%s, %s", rawData, LocalDateTime.now())); //information 생성
		}
		return list;
	}
}