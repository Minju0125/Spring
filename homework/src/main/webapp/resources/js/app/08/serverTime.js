/**
 * 
 */
/* 	$(document).on("ready", function(){
	$(serverTimeArea)
}) */
$(function(){
	console.log(this.body.dataset.contextPath);
	const CPATH = this.body.dataset.contextPath;
	let fnSuccesses = {
		json : function(resp){
			serverTimeArea.innerHTML = resp.now;
		},
		html : function(resp){
			serverTimeArea.innerHTML = resp;
		}
	}
	let settings = {
		url : `${CPATH}/08/serverTime`,
		error : function(jqXhr, status, error) {
			console.log("jqXhr : ", jqXhr);
			console.log("status : ", status);
			console.log("errer : ", error);
		}
	};
	$.ajax(settings)
	
	setTimeout(() => {
		console.log("5초 뒤에 한번 실행하고 종료.")
	}, 5000);
	
	setInterval(() => {
		settings.dataType = $("[name=dataType]:checked").val();
		settings.success = fnSuccesses[settings.dataType];
		//닷노테이션과 연산배열구조의 차이점!
		$.ajax(settings)
	}, 1000);
});