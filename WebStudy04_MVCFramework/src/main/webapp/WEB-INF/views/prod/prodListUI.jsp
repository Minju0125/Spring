<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<table class="table table-bordered">
	<thead>
		<tr>
			<th>일련번호</th>
			<th>상품명</th>
			<th>상품분류명</th>
			<th>제조사명</th>
			<th>판매가</th>
			<th>세일가</th>
			<th>마일리지</th>
			<th>구매자수</th>
		</tr>
	</thead>
	<tbody id="listBody">
		
	</tbody>
	<tfoot>
		<tr>
			<td colspan="8">
				<span id="pagingArea"></span>
				<div id="searchUI"  class="row g-3 d-flex justify-content-center">
					<div class="col-auto">
						<select name="prodLgu" class="form-select">
							<option value>상품분류</option>
							<c:forEach items="${lprodList }" var="lprod">
								<option label="${lprod.lprodNm }" value="${lprod.lprodGu }" />
							</c:forEach>
						</select>
					</div>
					<div class="col-auto">
						<select name="prodBuyer" class="form-select">
							<option value>제조사</option>
							<c:forEach items="${buyerList }" var="buyer">
								<option class="${buyer.buyerLgu	 }" label="${buyer.buyerName }" value="${buyer.buyerId }" />
							</c:forEach>
						</select>
					</div>
					<div class="col-auto">
						<input type="text" name="prodName" placeholder="상품명" class="form-control"/>
					</div>
					<div class="col-auto">
						<input type="button" value="검색" id="searchBtn" class="btn btn-primary"/>
						<a href="<c:url value='/prod/prodInsert.do'/>" class="btn btn-success">신규상품 등록</a>
					</div>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form action="<c:url value='/prod/ajax/prodListData.do'/>" id="searchForm" class="border">
	<h4>전송 UI</h4>
	<input type="text" name="prodLgu" readonly="readonly" placeholder="prodLgu"/>
	<input type="text" name="prodBuyer" readonly="readonly" placeholder="prodBuyer"/>
	<input type="text" name="prodName" readonly="readonly" placeholder="prodName"/>
	<input type="text" id="currpage" name="page" readonly="readonly" placeholder="page"/>
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
$(":input[name=prodLgu]").val("${paging.detailCondition.prodLgu}").trigger("change");
$(":input[name=prodBuyer]").val("${paging.detailCondition.prodBuyer}");
$(":input[name=prodName]").val("${paging.detailCondition.prodName}");

function makeTrTag(prod){
	let cPath = document.body.dataset.contextPath;
	let prodViewURL = `\${cPath}/prod/prodView.do?what=\${prod.prodId}`;
	let trTag =`
			<tr data-prod-id="\${prod.prodId }">
				<td>\${prod.rnum}</td>
				<td>
					<a href="\${prodViewURL }">\${prod.prodName }</a>
				</td>
				<td>\${prod.lprod.lprodNm }</td>
				<td>\${prod.buyer.buyerName }</td>
				<td>\${prod.prodPrice }</td>
				<td>\${prod.prodSale }</td>
				<td>\${prod.prodMileage }</td>
				<td>\${prod.memCount }</td>
			</tr>
		`;
		return trTag;
}

$(searchForm).on("submit", function(event){
	event.preventDefault();
	let url = this.action;
	let data = $(this).serialize();
	$.getJSON(`\${url}?\${data}`)
		.done(function(resp){
			console.log(resp.paging);
			let prodList = resp.paging.dataList;
			let trTags = null;
			if(prodList.length > 0){
				$.each(prodList, function(idx, prod){
					trTags += makeTrTag(prod); 
				});
				$(pagingArea).html(resp.paging.pagingHTML);
			}else{
				trTags += `
					<tr>
						<td colspan="8">상품 없음.</td>
					</tr>
				`;
				$(pagingArea).empty();
			}
			$(listBody).html(trTags);
			
		});
}).submit();

function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();
	searchForm.page.value = "";
}
$(searchUI).on("click", "#searchBtn", function(event){
	let inputs = $(this).parents("#searchUI").find(":input[name]");
	$.each(inputs, function(idx, ipt){
		let name = ipt.name;
		let value = $(ipt).val();
		$(searchForm).find(`:input[name=\${name}]`).val(value);
	});
	$(searchForm).submit();
});
</script>



