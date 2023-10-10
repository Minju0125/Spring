package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfo;

/**
 * 사용자 인증정보와 회원 정보를 관리하기 위한 Persistence Layer
 *
 */
public interface MemberDao {
	/**
	 * id / pasword 를 기반으로 사용자의 기본 정보를 조회
	 * 
	 * @param inputData 검색조건으로 사용할 id/password
	 * @return 검색 결과 객체로 존재하지 않는 경우, null 반환.
	 */
	public MemberVO selectMemberForAuth(MemberVO inputData);

	/**
	 * 신규 회원 등록
	 * 
	 * @param member
	 * @return 등록 성공( >= 1)
	 */
	public int insertMember(MemberVO member);

	/**
	 * 회원 정보 상세 조회
	 * 
	 * @param memId
	 * @return 존재하지 않으면, null 반환
	 */
	public MemberVO selectMember(@Param("memId") String memId);
	
	/**
	 * totalRecord 조회
	 * @param paging TODO
	 * @return
	 */
	public int selectTotalRecord(PaginationInfo<MemberVO> paging);

	/**
	 * 회원 목록 조회 (추후, 검색과 페이징 지원 예정)
	 * @param paging TODO
	 * 
	 * @return
	 */
	public List<MemberVO> selectMemberList(PaginationInfo paging);

	/**
	 * 회원 정보 수정
	 * 
	 * @param member
	 * @return 수정 성공( >= 1)
	 */
	public int updateMember(MemberVO member);

	/**
	 * 회원 정보 삭제
	 * 
	 * @param memId
	 * @return 삭제 성공 ( >= 1)
	 */
	public int deleteMember(String memId);
}