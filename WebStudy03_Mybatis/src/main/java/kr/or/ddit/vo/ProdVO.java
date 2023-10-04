package kr.or.ddit.vo;

import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
/**
 * 하나의 상품에 대한 정보를 캡슐화하기 위한 도메인 레이어
 *
 */

@Data
@EqualsAndHashCode(of="prodId")
public class ProdVO {
	
   private int memCount;
   
   
   private String prodId;
   private String prodName;
   private String prodLgu;
   private String prodBuyer;
   private Integer prodCost;
   private Integer prodPrice;
   private Integer prodSale;
   private String prodOutline;
   private String prodDetail;
   private String prodImg;
   private Integer prodTotalstock;
   private String prodInsdate;
   private Integer prodProperstock;
   private String prodSize;
   private String prodColor;
   private String prodDelivery;
   private String prodUnit;
   private Integer prodQtyin;
   private Integer prodQtysale;
   private Integer prodMileage;
   
   private LprodVO lprod;   // has a (1:1 관계)
                     //   상품 하나가 분류 하나에 소속되어있기 때문에 1:1 관계
   private BuyerVO buyer;
   
   private List<CartVO> cartList;
}