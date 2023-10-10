/**
 * 
 */
$(function(){
	const cPath = $(this.body).data("contextPath");
	let makeTrTag = function(adrsVO){
		let trTag = `
			<tr data-adrs-no="${adrsVO.adrsNo}">
				<td>${adrsVO.adrsName}</td>
				<td>${adrsVO.adrsHp}</td>
				<td>${adrsVO.adrsAdd}</td>
				<td><input type="button" value="삭제" class="delBtn"/></td>
			</tr>
		`;
		return trTag;
	};
	const baseUrl = `${cPath}/adrs/address`;
	$.getJSON(baseUrl, function(resp){
		let adrsList = resp.adrsList;
		trTags = "";
		if(adrsList?.length > 0){
			$.each(adrsList, function(){
				trTags += makeTrTag(this);
			});
		}else{
			trTags += `
				<tr>
					<td colspan='3'>주소록 없음.</td>
				</tr>
			`;
		}// if~else end
		
		$(listBody).html(trTags);
		
	}); // getJSON end
	
	$(adrsForm).on("submit", function(event){
		event.preventDefault();
		let url = this.action;
		let method = this.method;
		let data = $(this).serializeJSON();
		let json = JSON.stringify(data);
		let settings = {
			url:baseUrl,
			method: method,
			data:json,
			headers:{
				"Content-Type":"application/json;charset=UTF-8"
			},
			dataType:"json"
		};
		$.ajax(settings)
			.done(function(resp){
				if(resp.success){
					let trTag = makeTrTag(resp.originalData);
					$(listBody).prepend(trTag);
					adrsForm.reset();
				}else{
					alert(resp.message);
				}
			});
		return false;
	});
	
	$(listBody).on("click", ".delBtn", function(){
		let flag = confirm("삭제 할텨?");
		if(!flag) return false;
		
		let adrsTr = $(this).parents("tr:first");
		let $adrsTr = $(adrsTr);
		let adrsNo = $adrsTr.data("adrsNo");
		let url = `${baseUrl}/${adrsNo}`;
		let settings = {
			url:url,
			method:"delete",
			dataType:"json"
		};
		$.ajax(settings)
			.done(function(resp){
				if(resp.success){
					$adrsTr.remove();
				}else{
					alert(resp.message);
				}
			});
	});
});

























