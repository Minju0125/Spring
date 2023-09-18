/**
 * 
 */
//calForm.operator.value = "${param.operator}";
//el 이나 서버사이드 코드를 넣을 수 없음
$(function(){ //document load 작업이 끝났을 때 // $(document).on("load|ready", function(){})
	let selectValue =$(calForm.operator).data("initValue");
	$(calForm.operator).val(selectValue);
	
	$(calForm).on("submit", function(event){
		event.preventDefault();
		let url = this.action;
		let method = this.method;
		console.log(method);
		let data = $(this).serializeJSON(); //js object
		console.log("serializeJSON 함수의 결과, ", data);
		let json = JSON.stringify(data);
		console.log("data marshalling : ", json)
		let settings = {
				url : url,
				method : method,
				data : json,			
				contentType : "application/json; charset=UTF-8",
				dataType : "json", 
				success : function(resp) {
					//처리 성공시만, 여기로
					$(resultArea).html(resp.calVO.expression);
				},
				error : function(jqXhr, status, error) {
					console.log("jqXhr : ", jqXhr);
					console.log("status : ", status);
					console.log("errer : ", error);
				}
			};
			$.ajax(settings);
		return false;
	});
});