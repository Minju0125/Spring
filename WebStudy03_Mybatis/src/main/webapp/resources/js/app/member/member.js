/**
 * 
 */

$(function(){
	$("#insertBtn").on('click', function(){
		const cPath = $(this).data("contextPath");
		var param = $("form").serializeJSON();
		let json = JSON.stringify(param);
		
		let settings = {
			url : `${cPath}/member/memberInsert.do`,
			data : json,
			contentType: 'application/json',
			method : "post",
			success : function(resp){
				alert(resp + "성공/실패")
			}
		}
		console.log(param)
		console.log(json)
		$.ajax(settings)
	})
	
	function memPassValidation(){
		prompt("dfsdfsdf")
	}
})	
