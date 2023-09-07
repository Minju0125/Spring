/**
 * 
 */

//$(document).readay();

$(function(){
	console.log(this.body.dataset.contextPath); //여기서 this는 document
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
	      //sucess function 도 동적으로
	      error : function(jqXhr, status, error) {
	         console.log("jqXhr : ", jqXhr);
	         console.log("status : ", status);
	         console.log("errer : ", error);
	      }
	   };
	   
	   setTimeout(() => {
	      console.log("5초 뒤에 한번 실행하고 종료.")
	   }, 5000);
	   
	   setInterval(() => { 
		  settings.dataType = $("[name=dataType]:checked").val()
		  //settings.success = fnSuccesses.json//json 이라는 함수를 success 
		  //settings.success = fnSuccesses.html//json 이라는 함수를 success => * html을 식별자로
		  settings.success = fnSuccesses[settings.dataType]
	      $.ajax(settings)
	   }, 1000);
})