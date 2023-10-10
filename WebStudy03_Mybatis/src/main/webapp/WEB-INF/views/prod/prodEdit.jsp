<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<form method="post" enctype="multipart/form-data">
<input type="hidden" name="prodId" class="form-control" value="${prod.prodId}" readonly >
<table class="table table-bordered" action="${pageContext.request.contextPath}/prod/prodUpdate.do">
	<tr>
		<th>상품명</th>
		<td>
			<input type="text" name="prodName" class="form-control" value="${prod.prodName}" >
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
		<th>구매가</th>
		<td>
			<input type="text" name="prodCost" class="form-control" value="${prod.prodCost}" >
						<span class="error">${errors.prodCost}</span>
		</td>
	</tr>
	<tr>
		<th>판매가</th>
		<td>
			<input type="text" name="prodPrice" class="form-control" value="${prod.prodPrice}" >
					<span class="error">${errors.prodPrice}</span>
		</td>
	</tr>
	<tr>
		<th>세일가</th>
		<td>
			<input type="text" name="prodSale" class="form-control" value="${prod.prodSale}" >
						<span class="error">${errors.prodSale}</span>
		</td>
	</tr>
	<tr>
		<th>요약정보</th>
		<td>
			<input type="text" name="prodOutline" class="form-control" value="${prod.prodOutline}" >
						<span class="error">${errors.prodOutline}</span>
		</td>
	</tr>
	<tr>
		<th>상세정보</th>
		<td>
			<input type="text" name="prodDetail" class="form-control" value="${prod.prodDetail}" >
						<span class="error">${errors.prodDetail}</span>
		</td>
	</tr>
	<tr>
		<th>이미지</th>
		<td>
			<c:if test="${prod.prodImg ne null }">
				<img src="<c:url value='/resources/prodImages/${prod.prodImg }'/>">
			</c:if>
		
		
				<input type="file" name="prodImage" id="prodImage" class="form-control"/>
						<span class="error">${errors.prodImg}</span>
		</td>
	</tr>
	<tr>
		<th>총재고</th>
		<td>
			<input type="text" name="prodTotalstock" class="form-control" value="${prod.prodTotalstock}" >
						<span class="error">${errors.prodTotalstock}</span>
		
		</td>
	</tr>
	<tr>
		<th>입고일</th>
		<td>
			<input type="date" name="prodInsdate" class="form-control" value="${prod.prodInsdate}" >
						<span class="error">${errors.prodInsdate}</span>
		
		</td>
	</tr>
	<tr>
		<th>적정재고</th>
		<td>
			<input type="text" name="prodProperstock" class="form-control" value="${prod.prodProperstock}" >
						<span class="error">${errors.prodProperstock}</span>
		
		</td>
	</tr>
	<tr>
		<th>크기</th>
		<td>
			<input type="text" name="prodSize" class="form-control" value="${prod.prodSize}" >
						<span class="error">${errors.prodSize}</span>
		
		</td>
	</tr>
	<tr>
		<th>색상</th>
		<td>
			<input type="text" name="prodColor" class="form-control" value="${prod.prodColor}" >
						<span class="error">${errors.prodColor}</span>
		
		</td>
	</tr>
	<tr>
		<th>배송방법</th>
		<td>
			<input type="text" name=prodDelivery class="form-control" value="${prod.prodDelivery}" >
						<span class="error">${errors.prodDelivery}</span>
		
		</td>
	</tr>
	<tr>
		<th>단위</th>
		<td>
			<input type="text" name=prodUnit class="form-control" value="${prod.prodUnit}" >
						<span class="error">${errors.prodUnit}</span>
		
		</td>
	</tr>
	<tr>
		<th>입고량</th>
		<td>
			<input type="text" name=prodQtyin class="form-control" value="${prod.prodQtyin}" >
						<span class="error">${errors.prodQtyin}</span>
		
		</td>
	</tr>
	<tr>
		<th>판매량</th>
		<td>
			<input type="text" name=prodQtysale class="form-control" value="${prod.prodQtysale}" >
						<span class="error">${errors.prodQtysale}</span>
		
		</td>
	</tr>
	<tr>
		<th>마일리지</th>
		<td>
			<input type="text" name=prodMileage class="form-control" value="${prod.prodMileage}" >
						<span class="error">${errors.prodMileage}</span>
		
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="전송" class="btn btn-primary" />
			<input type="reset" value="취소" 	class="btn btn-warning" />
		</td>
	</tr>
</table>
</form>  
<script>
let $prodBuyer = $("select[name=prodBuyer]").val("${prod.prodBuyer}");
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
}).val("${prod.prodLgu}").change();
</script>
