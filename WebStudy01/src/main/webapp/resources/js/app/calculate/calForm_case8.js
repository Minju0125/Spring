/**
 * 
 */
$(function(){ // $(document).on("load|ready", function(){});
	let selectValue = $(calForm.operator).data("initValue");
	$(calForm.operator).val(selectValue);
	$(calForm).on("submit", function(event){
		event.preventDefault();
		let url = this.action;
		let method = this.method;
		let settings = {
			url : url,
			method : method,
			dataType : "json" // Accept request header : Content-Type response header
			,
			success : function(resp) {
				$(resultArea).html(resp.calVO.expression);
			},
			error : function(jqXhr, status, error) {
				console.log("jqXhr : ", jqXhr);
				console.log("status : ", status);
				console.log("error : ", error);
			}
		};
		
		let contentType = $("[name=contentType]:checked").data("contentType");
		if(contentType && contentType.indexOf("json")>=0){
			let data = $(this).serializeJSON(); // js object
			let json = JSON.stringify(data);
			settings.contentType=contentType;
			settings.data=json;
		}else{
			let data = $(this).serialize(); // query string
			settings.data = data;
		}
		
		$.ajax(settings);
		return false;
	});
});
















