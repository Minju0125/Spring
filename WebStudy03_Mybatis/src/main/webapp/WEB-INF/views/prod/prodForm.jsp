<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	

<h4>신규 상품 등록</h4>
<form method="post">
	<table>
			<tr>
				<th>상품명</th>
				<td><input type="text" name="prodName" class="form-control"
					required value="${prod.prodName}" /><span class="error">${errors.prodName}</span></td>
			</tr>
			<tr>
				<th>상품분류</th>
				<td>
					<select name="prodLgu">
						<option value>전체</option>
						<c:forEach items="${lprodList}" var="lprod">
							<option value="${lprod.lprodGu }" label="${lprod.lprodNm }"/>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>제조사</th>
				<td>
					<select name="prodBuyer">
	                  <option value>제조사</option>               
	                  <c:forEach items="${buyerList}" var="buyer">
	                     <option class="${buyer.buyerLgu }" value="${buyer.buyerId }" label="${buyer.buyerName}"></option>
	                  </c:forEach>
	               </select>
				</td>
			</tr>
			<tr>
				<th>구매가</th>
				<td><input type="number" name="prodCost" class="form-control"
					required value="${prod.prodCost}" /><span class="error">${errors.prodCost}</span></td>
			</tr>
			<tr>
				<th>판매가</th>
				<td><input type="number" name="prodPrice" class="form-control"
					required value="${prod.prodPrice}" /><span class="error">${errors.prodPrice}</span></td>
			</tr>
			<tr>
				<th>세일가</th>
				<td><input type="number" name="prodSale" class="form-control"
					required value="${prod.prodSale}" /><span class="error">${errors.prodSale}</span></td>
			</tr>
			<tr>
				<th>요약정보</th>
				<td><input type="text" name="prodOutline" class="form-control"
					required value="${prod.prodOutline}" /><span class="error">${errors.prodOutline}</span></td>
			</tr>
			<tr>
				<th>상세정보</th>
				<td><input type="text" name="prodDetail" class="form-control"
					value="${prod.prodDetail}" /><span class="error">${errors.prodDetail}</span></td>
			</tr>
			<tr>
				<th>이미지</th>
				<td><input type="text" name="prodImg" class="form-control"
					required value="${prod.prodImg}" /><span class="error">${errors.prodImg}</span></td>
			</tr>
			<tr>
				<th>총재고</th>
				<td><input type="number" name="prodTotalstock"
					class="form-control" required value="${prod.prodTotalstock}" /><span
					class="error">${errors.prodTotalstock}</span></td>
			</tr>
			<tr>
				<th>입고일</th>
				<td><input type="date" name="prodInsdate" class="form-control"
					value="${prod.prodInsdate}" /><span class="error">${errors.prodInsdate}</span></td>
			</tr>
			<tr>
				<th>적정재고</th>
				<td><input type="number" name="prodProperstock"
					class="form-control" required value="${prod.prodProperstock}" /><span
					class="error">${errors.prodProperstock}</span></td>
			</tr>
			<tr>
				<th>크기</th>
				<td><input type="text" name="prodSize" class="form-control"
					value="${prod.prodSize}" /><span class="error">${errors.prodSize}</span></td>
			</tr>
			<tr>
				<th>색상</th>
				<td><input type="text" name="prodColor" class="form-control"
					value="${prod.prodColor}" /><span class="error">${errors.prodColor}</span></td>
			</tr>
			<tr>
				<th>배송방법</th>
				<td><input type="text" name="prodDelivery" class="form-control"
					value="${prod.prodDelivery}" /><span class="error">${errors.prodDelivery}</span></td>
			</tr>
			<tr>
				<th>단위</th>
				<td><input type="text" name="prodUnit" class="form-control"
					value="${prod.prodUnit}" /><span class="error">${errors.prodUnit}</span></td>
			</tr>
			<tr>
				<th>입고량</th>
				<td><input type="number" name="prodQtyin" class="form-control"
					value="${prod.prodQtyin}" /><span class="error">${errors.prodQtyin}</span></td>
			</tr>
			<tr>
				<th>판매량</th>
				<td><input type="number" name="prodQtysale" class="form-control"
					value="${prod.prodQtysale}" /><span class="error">${errors.prodQtysale}</span></td>
			</tr>
			<tr>
				<td colspan="1">
					<input type="submit" value="저장" class="btn btn-primary">
					<input type="button" value="취소" class="btn btn-warning"> 
					<input type="button" value="목록으로" class="btn btn-dark"> 
				</td>
			</tr>
	</table>
</form>

<script>
$("select[name=prodLgu]").on("change", function(event){
   let lgu = $(this).val();
   let options = $("select[name=prodBuyer]").find("option");
   $(options).hide();
   //$("select[name=prodBuyer]").find("option").hide();
   $(options).filter((i,e)=>i==0).show();
   //$("select[name=prodBuyer]").find("option:first").show();
   
   if(lgu){
      $(options).filter(`.\${lgu}`).show();
      //$("select[name=prodBuyer]").find(`option.\${lgu}`).show();
   }else{
      $(options).show();
      //$("select[name=prodBuyer]").find(`option`).show();   
   }
});

</script>
