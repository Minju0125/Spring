package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Base64;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 하나의 상품에 대한 정보를 캡슐화하기 위한 도메인 레이어.
 *
 */
@Data
@EqualsAndHashCode(of="prodId")
public class ProdVO implements Serializable{
   
   //페이징
   private int rnum;
   
   private int prodCount;
   
   private int memCount;
   
   @NotBlank(groups=UpdateGroup.class)
   private String prodId;
   @NotBlank(groups=InsertGroup.class)
   private String prodName;
   @NotBlank(groups=InsertGroup.class)
   private String prodLgu;
   @NotBlank(groups=InsertGroup.class)
   private String prodBuyer;
   @NotNull
   private Integer prodCost;
   @NotNull
   private Integer prodPrice;
   private Integer prodSale;
   private String prodOutline;
   private String prodDetail;
   
   @NotBlank(groups=InsertGroup.class) //등록시 필수, 수정시 선택사항
   private String prodImg; //optional data => insert group
   
   @NotNull
   @Min(0)
   private Integer prodTotalstock;
   private String prodInsdate;
   @NotNull
   @Min(0)
   private Integer prodProperstock;
   private String prodSize;
   private String prodColor;
   private String prodDelivery;
   private String prodUnit;
   private Integer prodQtyin;
   private Integer prodQtysale;
   private Integer prodMileage;
   
   private LprodVO lprod;//has a (1:1 관계)
   
   private BuyerVO buyer; // has a 
   
   //하나의 상품에 대한 여러건의 구매기록
   private Set<CartVO> cartList;// has many
  
}