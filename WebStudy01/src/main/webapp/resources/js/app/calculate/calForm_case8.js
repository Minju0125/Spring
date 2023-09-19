/**
 * 
 */
//calForm.operator.value = "${param.operator}";
//el 이나 서버사이드 코드를 넣을 수 없음
$(function(){ //document load 작업이 끝났을 때 // $(document).on("load|ready", function(){})
	let selectValue =$(calForm.operator).data("initValue");
	$(calForm.operator).val(selectValue);
	let successes = {//두개 이상의 함수를 객체로 모아놓기 (자바스크립트에서는 함수도 하나의 객체가 될 수 있음)
		//name:value (아래에서 json, html 은 함수의 이름)
		json:function(resp){
			$(resultArea).html(resp.calVO.expression); //controller에서 scope에 담을때 calVO 라는 이름 결정
		},
		HTML:function(resp){
			$(resultArea).html(resp);
		}
	};
	
	$(calForm).on("submit", function(event){
		event.preventDefault();
		
		let url = this.action;
		let method = this.method;
		let settings = {
				url : url,
				method : method,
				
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
		
		let dataType = $("[name=accept]:checked").val() ?? "json"; // $("[name=accept]:checked").val() 이 있으면 쓰고, 없으면 json
		settings["dataType"] = dataType;
		settings.success = successes[dataType];
		
		$.ajax(settings); //상황에 따라서 다른 data와 다른 content-type을 가지게됨
		return false;
	});
});