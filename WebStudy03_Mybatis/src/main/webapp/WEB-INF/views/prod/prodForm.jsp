<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	th{
		text-align: center;
	}
	td{
		padding-bottom: 2px;
	}
</style>
<form method="post" enctype="multipart/form-data">
	<table class="col-md-6">
		<tr>
			<th><label for="prodName">상품명</label></th>
			<td>
				<input type="text" name="prodName" id="prodName" class="form-control" required
					value="${prod.prodName}" />
				<span class="error">${errors.prodName}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodLgu">상품분류</label></th>
			<td>
				<select name="prodLgu" id="prodLgu" class="form-select" required>
					<option value>상품분류</option>
					<c:forEach items="${lprodList }" var="lprod">
						<option label="${lprod.lprodNm }" value="${lprod.lprodGu }" />
					</c:forEach>
				</select>
				<span class="error">${errors.prodLgu}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodBuyer">제조사</label></th>
			<td>
				<select name="prodBuyer" id="prodBuyer" class="form-select" required>
					<option value>제조사</option>
					<c:forEach items="${buyerList }" var="buyer">
						<option class="${buyer.buyerLgu	 }" label="${buyer.buyerName }" value="${buyer.buyerId }" />
					</c:forEach>
				</select>
				<span class="error">${errors.prodBuyer}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodCost">구매가</label></th>
			<td>
				<input type="number" name="prodCost" id="prodCost" class="form-control" required
					value="${prod.prodCost}" />
				<span class="error">${errors.prodCost}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodPrice">판매가</label></th>
			<td>
				<input type="number" name="prodPrice" id="prodPrice" class="form-control" required
					value="${prod.prodPrice}" />
				<span class="error">${errors.prodPrice}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodSale">세일가</label></th>
			<td>
				<input type="number" name="prodSale" id="prodSale" class="form-control" required
					value="${prod.prodSale}" />
				<span class="error">${errors.prodSale}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodOutline">요약정보</label></th>
			<td>
				<input type="text" name="prodOutline" id="prodOutline" class="form-control" required
					value="${prod.prodOutline}" />
				<span class="error">${errors.prodOutline}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodDetail">상세정보</label></th>
			<td>
				<input type="text" name="prodDetail" id="prodDetail" class="form-control"
					value="${prod.prodDetail}" />
				<span class="error">${errors.prodDetail}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodImage">이미지</label></th>
			<td>
				<input type="file" name="prodImage" id="prodImage" class="form-control" required/>
				<span class="error">${errors.prodImg}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodTotalstock">총재고</label></th>
			<td>  
				<input type="number" name="prodTotalstock"
				id="prodTotalstock" class="form-control" required
				value="${prod.prodTotalstock}" />
				<span class="error">${errors.prodTotalstock}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodInsdate">입고일</label></th>
			<td>
				<input type="date" name="prodInsdate" id="prodInsdate" class="form-control"
					value="${prod.prodInsdate}" />
				<span class="error">${errors.prodInsdate}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodProperstock">적정재고</label></th>
			<td>
				<input type="number" name="prodProperstock" id="prodProperstock" class="form-control" required
				value="${prod.prodProperstock}" />
				<span class="error">${errors.prodProperstock}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodSize">크기</label></th>
			<td>
				<input type="text" name="prodSize" id="prodSize" class="form-control"
					value="${prod.prodSize}" />
				<span class="error">${errors.prodSize}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodColor">색상</label></th>
			<td>
				<input type="text" name="prodColor" id="prodColor" class="form-control"
					value="${prod.prodColor}" />
				<span class="error">${errors.prodColor}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodDelivery">배송방법</label></th>
			<td>
				<input type="text" name="prodDelivery" id="prodDelivery" class="form-control"
					value="${prod.prodDelivery}" />
				<span class="error">${errors.prodDelivery}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodUnit">단위</label></th>
			<td>
				<input type="text" name="prodUnit" id="prodUnit" class="form-control"
					value="${prod.prodUnit}" />
				<span class="error">${errors.prodUnit}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodQtyin">입고량</label></th>
			<td>
				<input type="number" name="prodQtyin" id="prodQtyin" class="form-control"
					value="${prod.prodQtyin}" />
				<span class="error">${errors.prodQtyin}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodQtysale">판매량</label></th>
			<td>
				<input type="number" name="prodQtysale" id="prodQtysale" class="form-control"
					value="${prod.prodQtysale}" />
				<span class="error">${errors.prodQtysale}</span>
			</td>
		</tr>
		<tr>
			<th><label for="prodMileage">마일리지</label></th>
			<td>
				<input type="number" name="prodMileage" id="prodMileage" class="form-control"
					value="${prod.prodMileage}" />
				<span class="error">${errors.prodMileage}</span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="row g-2 d-flex justify-content-center mt-1">
					<div class="col-auto">
						<button type="submit" class="btn btn-primary">저장</button>
					</div>
					<div class="col-auto">
						<button type="reset" class="btn btn-warning">취소</button>
					</div>
					<div class="col-auto">	
						<a class="btn btn-secondary" href="<c:url value='/prod/prodList.do'/>">목록으로</a>
					</div>		
				</div>
			</td>
		</tr>
	</table>
</form>
<script>
$("select[name=prodLgu]").on("change", function(event){
	let lgu = $(this).val();
	let $options = $("select[name=prodBuyer]").find("option");
	$options.hide();
	$options.filter((i,e)=>i==0).show();
	if(lgu){
		$options.filter(`.\${lgu}`).show();
	}else{
		$options.show();
	}
});
</script>