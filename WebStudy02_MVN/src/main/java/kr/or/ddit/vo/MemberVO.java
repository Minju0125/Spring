package kr.or.ddit.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = "memId")
@ToString
public class MemberVO implements Serializable{
	private transient String memId;
	@ToString.Exclude
	@JsonIgnore
	private String memPass;
	private String memName;
	@ToString.Exclude
	@JsonIgnore
	private transient String memRegno1;
	@ToString.Exclude
	@JsonIgnore
	private transient String memRegno2;
	private String memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private String memMemorialday;
	private Integer memMileage;
	private String memDelete;
}
