/**
 * 
 */
// $(document).ready();
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
			console.log("error : ", error);
		}
	};
	
	setTimeout(() => {
		console.log("5초 뒤에 한번 실행하고 종료.");
	}, 5000);
	
	setInterval(() => {
		settings.dataType = $("[name=dataType]:checked").val();
		settings.success = fnSuccesses[settings.dataType];
		$.ajax(settings);
	}, 1000);
});