/**
 * 
 */

$(function(){
	const cPath = $(this.body).data("contextPath"); //상수
	let makeTags = function(adrsVO){
		let trTag = `
			<tr>
				<td>${adrsVO.adrsName}</td>
				<td>${adrsVO.adrsHp}</td>
				<td>${adrsVO.adrsAdd}</td>
			</tr>	
			`;
		return trTag;
	};
	let url = `${cPath}/adrs/address`; //절대경로
	$.getJSON(url, function(resp){
		let adrsList = resp.adrsList;
		trTags = "";
		//if(adrsList && adrsList.length)
		if(adrsList?.length > 0){ //있으면서, length가 0보다 크면
			$.each(adrsList, function(){
				trTags += makeTags(this);
			});	
		}else{
			trTags += `
					<tr>
						<td colspan='3'>주소록 없음</td>
					</tr>
				`;
		}//if~else end
		$(listBody).html(trTags);
	}); //getJson end
});
 