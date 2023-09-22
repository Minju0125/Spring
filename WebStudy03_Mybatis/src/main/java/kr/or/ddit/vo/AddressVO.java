package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="adrsNo")
public class AddressVO implements Serializable{
	private int adrsNo; //primary key=> null x
	private String memId;
	private String adrsName;
	private String adrsHp;
	private String adrsAdd;
}
