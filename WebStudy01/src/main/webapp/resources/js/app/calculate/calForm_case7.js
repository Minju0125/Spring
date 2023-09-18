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
		let settings = {
				url : url,
				method : method,
				
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
			
		let contentType = $("[name=contentType]:checked").data("contentType"); //submit 되기 직전에 받아야함
		if(contentType && contentType.indexOf("json")>=0){ //conentType 이 존재하면서 ~ 제이슨 컨텐츠 타입이 맞으면
			let data = $(this).serializeJSON(); //js object
			let json = JSON.stringify(data);
			settings.contentType = contentType;
			settings.data = json;
		}else{
			let data = $(this).serialize(); //query String 이 만들어짐.
			//파라미터는 content Type 세팅하지 않아도 기본으로 세팅되어있음
			settings.data = data;
		}
		
		$.ajax(settings); //상황에 따라서 다른 data와 다른 content-type을 가지게됨
		return false;
	});
});