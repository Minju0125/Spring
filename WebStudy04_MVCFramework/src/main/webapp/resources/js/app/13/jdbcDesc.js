/**
 * 
 */
$(function(){
	let settings = { //url을 쓰지 않으면 여기까지오는데 사용된 url
		dataType:"json",
		success:function(resp){
			let dataList = resp.dataList;
			let trTags = "";
			if(dataList.length>0){ //어떤 경우에도 dataList 는 존재함
				$.each(dataList, function(idx, vo){
					trTags += `
						<tr>
							<td>${vo.propertyName}</td>
							<td>${vo.propertyValue}</td>
							<td>${vo.description}</td>
						</tr>
					`;
				});
			}else{
				trTags += "<tr><td colspan='3'>조회 결과 없음</td></tr>";
			}
			$(listBody).html(trTags);		
		}
	};
	$.ajax(settings);
});