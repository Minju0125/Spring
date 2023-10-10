<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<table class="table table-bordered">
      <tr data-bs-toggle="modal" data-bs-target="#exampleModal">
         <th>비밀번호</th>
         <td><input type="text"/></td>
      </tr>
</table>

 <!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
<script>
	//EDB (Event Driven Development)
	$(exampleModal).on("show.bs.modal", function(event){ //모달이 뜨기 시작할 때 이벤트처리
	  let $modal = $(this);
	  let trTag = event.relatedTarget; //tr태그
	  let who = $(trTag).data("memId");
	//   location.href = "${pageContext.request.contextPath}/member/memberView.do?who="+who;
	  let url = "${pageContext.request.contextPath}/member/memberView.do?who="+who;
	  $.get(url)   //ajax의 method : get과 똑같음
	      .done(function(resp){   //success와 똑같은 역할
	    	  $modal.find(".modal-body").html(resp);
	      });
	}).on("hidden.bs.modal", function(){
		$(this).find(".modal-body").empty();
	});
</script>

