<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<a href="<c:url value='/prod/prodInsert.do'/>" class="btn btn-primary">신규 상품 등록</a>

<table class="table table-bordered">
   <thead>
      <tr>
         <th>일련번호</th> 
<!--             rownum -->
         <th>상품명</th>
         <th>상품분류명</th>
         <th>제조사명</th>
         <th>판매가</th>
         <th>세일가</th>
         <th>마일리지</th>
         <th>구매자수</th>
      </tr>
   </thead>
   <tbody>
      <c:set value="${paging.dataList }" var="prodList"></c:set>
      <c:if test="${not empty prodList }">
         <c:forEach var="prod" items="${prodList}">
            <tr>
               <td>${prod.rnum }</td>
               <td>${prod.prodName }</td>
               <td>${prod.lprod.lprodNm }</td>
               <td>${prod.buyer.buyerName }</td>
               <td>${prod.prodCost }</td>
               <td>${prod.prodSale }</td>
               <td>${prod.prodMileage }</td>
               <td>${prod.memCount }</td>
            </tr>
         </c:forEach>
      </c:if>
      <c:if test="${empty prodList }">
         <td colspan="8">검색된 상품 없음</td>
      </c:if>
   </tbody>
   <tfoot>
         <tr>
         <td colspan="8">
            ${paging.pagingHTML }
            <div id="searchUI" class='pagination justify-content-center'>
            <select name="prodLgu">
               <option value>상품분류</option>
                  <c:forEach items="${lprodList}" var="lprod">
                     <option value="${lprod.lprodGu }">${lprod.lprodNm } </option>
                  </c:forEach>
            </select>
               <select name="prodBuyer">
                  <option value>제조사</option>               
                  <c:forEach items="${buyerList}" var="buyer">
                     <option class="${buyer.buyerLgu }" value="${buyer.buyerId }" label="${buyer.buyerName}"></option>
                  </c:forEach>
               </select>
               <input type="text" name="prodName" placeholder="상품명" />               
               <input type="button" value="검색" id="searchBtn" />
            </div>
         </td>
      </tr>
   </tfoot>
</table>
<form id="searchForm" class='pagination justify-content-center'>
   <input type="text" name="prodLgu" readonly="readonly" placeholder="prodLgu"/>
   <input type="text" name="prodBuyer" readonly="readonly" placeholder="prodBuyer"/>
   <input type="text" name="prodName" readonly="readonly" placeholder="prodName"/>
   <input type="text" name="page" readonly="readonly" placeholder="page"/>
</form>
<script>
   $("select[name=prodLgu]").on("change", function(event){
	   let lgu = $(this).val();
	   let options = $("select[name=prodBuyer]").find("option");
	   $(options).hide();
	   $(options).filter((i,e)=>i==0).show();
	   
	   // $("select[name=prodBuyer]").find("option").hide();
	   //$("select[name=prodBuyer]").find("option:first").show(); //prompt text
	   if(lgu){
		   $(options).filter(`.\${lgu}`).show();
		  // $("select[name=prodBuyer]").find(`option.\${lgu}`).show();
	   }else{
		   $(options).show();
		   //$("select[name=prodBuyer]").find(`option`).show();
	   }
   })
   $(':input[name=prodLgu]').val("${paging.detailCondition.prodLgu}").trigger("change");
   $(':input[name=prodBuyer]').val("${paging.detailCondition.prodBuyer}");
   $(':input[name=prodName]').val("${paging.detailCondition.prodName}");
   
   function fn_paging(page){
	   $(":input[name=page]").val(page);
	      $('#searchForm').submit();
   }
   $('#searchBtn').on("click", function(){
      let inputs = $(this).parents("#searchUI").find(':input[name]');
      $.each(inputs, function(i,v){
         let name = v.name;
         let value = $(v).val();
         $('#searchForm').find(`:input[name=\${name}]`).val(value);
      })
      $('#searchForm').submit();
   })
   
   $('#insertBtn').on("click", function(){
	   location.href= "${pageContext.request.contextPath}/prod/prodInsert.do";
   })
   
</script>