package kr.or.ddit.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.validate.grouphint.DeleteGroup;
import kr.or.ddit.validate.grouphint.InsertGroup;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 한사람의 회원의 정보를 캡슐화하기 위한 도메인 레이어.
 * Data Mapper를 이용해 다중 엔터티를 조회는 단계
 * 1. 주엔터티를 기준으로 엔터티간 관계 파악
 * 		1:1 ex) 하나의 상품과 그 상품의 제조사 정보 PROD(1) : BUYER(1)
 * 		1:N ex) 한사람의 구매정보 MEMBER(1) : PROD(N)
 * 2. 각 엔터티의 조회 결과를 바인딩할 vo 에 엔터티갑의 관계를 HAS 관계로 반영
 * 		1:1 - has A ex) ProdVO has a BuyerVO
 * 		1:N - has Many ex) MemberVO has many ProdVO
 * 		ex) 하나의 제조사와 그 제조사의 제조 상품 목록(PROD)을 조회.
 * 			BUYER(1) : PROD(N) -> BuyerVO has many ProdVO
 * 		ex) 하나의 제조사 (BUYER)와 그 제조사의 분류(LPROD)명을 조회
 * 			BUYER(1) : LPROD(1) -> BuyerVO has a LprodVO
 * 3. 조회결과 바인딩시, resultType 대신 resultMap 을 이용해 수동 바인딩 설정.
 * 		has many : collcetion (ofType)
 * 		has a : association (javaType)
 * 
 */
@Getter
@Setter
@EqualsAndHashCode(of = "memId")
@ToString
public class MemberVO implements Serializable {
	
	private int rnum;
	
	//case1 : 가입,등록,수정
	//case2 : 가입,등록
	//case3 : 가입
	
	//case1 : 가입,등록,수정
	@NotBlank(groups= {Default.class, DeleteGroup.class}) // 등록,수정,탈퇴시 입력
	private String memId; 
	@NotBlank(groups= {Default.class, DeleteGroup.class})
	@Size(min = 4, max = 12,groups= {Default.class, DeleteGroup.class})
	@ToString.Exclude
	@JsonIgnore
	private transient String memPass; // 가입,수정,탈퇴시 입력(이중인증)
	
	//case3 : 가입
	@NotBlank(groups = InsertGroup.class) // 가입시만 검증을 표현
	@Size(max = 10, groups = InsertGroup.class)
	private String memName; // 수정의대상x -> 클라이언트가 이 값을 입력하지 않을수도? (가입처리시만 동작)
							// group constraint
	@Size(min = 6, max = 6, groups = InsertGroup.class)
	@ToString.Exclude
	@JsonIgnore
	private transient String memRegno1;
	@Size(min = 7, max = 7, groups = InsertGroup.class)
	@ToString.Exclude
	@JsonIgnore
	private transient String memRegno2;
	private LocalDate memBir;

	//case2 : 가입,등록 -> 그룹 필요 없음 (default 에 가입, 등록 있기 때문)
	@NotBlank
	private String memZip;
	@NotBlank
	private String memAdd1;
	@NotBlank
	private String memAdd2;
	@Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{4}")
	private String memHometel;
	@Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{4}")
	private String memComtel;
	@Pattern(regexp = "010-\\d{3,4}-\\d{4}")
	private String memHp;
	@NotBlank
	@Email
	@Size(min = 8)
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private LocalDate memMemorialday;

	@Min(0)
	private Integer memMileage;
	
	
	private boolean memDelete; //오라클에서는 불린 표현할 때, null 값이라면 false로 (값을 가지고 있으면 true)
	private int prodCount;
	
	private Set<CartVO> cartSet; //has many
	
	private byte[] memImg;
	
	public String getMemImgBase64(){
		//이미지가 있을 때만 인코딩 
		if(memImg!=null) 
			return Base64.getEncoder().encodeToString(memImg); //바이트계열을 받아서 문자열로 만들어줌
		else 
			return null;
	}
}