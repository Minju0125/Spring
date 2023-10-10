<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<table class="table table-bordered">
	<tr>
		<th>상품코드</th>
		<td>${prod.prodId }</td>
	</tr>
	<tr>
		<th>상품명</th>
		<td>${prod.prodName }</td>
	</tr>
	<tr>
		<th>상품분류</th>
		<td>${prod.lprod.lprodNm }</td>
	</tr>
	<tr>	
		<th>제조사</th>
		<td>
			<table class="table table-bordered">
				<thead class="table-dark">
					<tr>
						<th>제조사명</th>
						<th>소재지</th>
						<th>담당자명</th>
						<th>거래은행명</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="buyer" value="${prod.buyer }" />
					<tr>
						<td>${buyer.buyerName }</td>
						<td>${buyer.buyerAdd1 }</td>
						<td>${buyer.buyerCharger }</td>
						<td>${buyer.buyerBank }</td>
					</tr>
				</tbody>
			</table>
		</td>
	</tr>
	<tr>
		<th>구매가</th>
		<td>${prod.prodCost }</td>
	</tr>
	<tr>
		<th>판매가</th>
		<td>${prod.prodPrice }</td>
	</tr>
	<tr>
		<th>세일가</th>
		<td>${prod.prodSale }</td>
	</tr>
	<tr>
		<th>요약정보</th>
		<td>${prod.prodOutline }</td>
	</tr>
	<tr>
		<th>상세정보</th>
		<td>${prod.prodDetail }</td>
	</tr>
	<tr>
		<th>이미지</th>
		<td>
			<img src="<c:url value='/resources/prodImages/${prod.prodImg }'/>">
		</td>
	</tr>
	<tr>
		<th>총재고</th>
		<td>${prod.prodTotalstock }</td>
	</tr>
	<tr>
		<th>입고일</th>
		<td>${prod.prodInsdate }</td>
	</tr>
	<tr>
		<th>적정재고</th>
		<td>${prod.prodProperstock }</td>
	</tr>
	<tr>
		<th>크기</th>
		<td>${prod.prodSize }</td>
	</tr>
	<tr>
		<th>색상</th>
		<td>${prod.prodColor }</td>
	</tr>
	<tr>
		<th>배송방법</th>
		<td>${prod.prodDelivery }</td>
	</tr>
	<tr>
		<th>단위</th>
		<td>${prod.prodUnit }</td>
	</tr>
	<tr>
		<th>입고량</th>
		<td>${prod.prodQtyin }</td>
	</tr>
	<tr>
		<th>판매량</th>
		<td>${prod.prodQtysale }</td>
	</tr>
	<tr>
		<th>마일리지</th>
		<td>${prod.prodMileage }</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:url value="/prod/prodUpdate.do" var="prodUpdateUrl">
				<c:param name="what" value="${prod.prodId }"/>
			</c:url>
			<a class="btn btn-primary" href="${prodUpdateUrl}">상품수정</a>
		</td>
	</tr>
</table>
<h4>구매자 정보</h4>
<table class="table table-bordered">
	<thead class="table-dark">
		<tr>
			<th>구매자이름</th>
			<th>휴대폰</th>
			<th>이메일</th>
			<th>거주지</th>
			<th>마일리지</th>
			<th>구매일자</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="cartList" value="${prod.cartList }" />
		<c:if test="${not empty  cartList}">
			<c:forEach items="${cartList }" var="cart">
				<c:set var="member" value="${cart.member }" />
				<tr>
					<td>${member.memName }</td>
					<td>${member.memHp }</td>
					<td>${member.memMail }</td>
					<td>${member.memAdd1 }</td>
					<td>${member.memMileage }</td>
					<td>${cart.cartDate }</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty  cartList}">
			<tr>
				<td colspan="6">구매자 없음.</td>
			</tr>
		</c:if>
	</tbody>
</table>