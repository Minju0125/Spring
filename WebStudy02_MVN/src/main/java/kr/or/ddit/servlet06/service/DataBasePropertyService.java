package kr.or.ddit.servlet06.service;

import java.util.List;

import kr.or.ddit.vo.DataBasePropertyVO;

public interface DataBasePropertyService {
	public List<DataBasePropertyVO> retrieveDBPropertyList();
}
