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
		let data = $(this).serialize(); //query String
		let settings = {
				url : url,
				method : method,
				data : data,			
				dataType : "json", //Accept request header : Content-Type response header
				success : function(resp) {
					let expr = null;
					if(resp.calVO){
						expr = resp.calVO.expression;
					}else{
						expr = JSON.stringify(resp.errors); //자바스크립트 객체를 다시 json(문자열 기반)으로
					}
					$(resultArea).html(expr);
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