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
		let data = $(this).serialize(); // quer String
		let settings = {
			url : url,
			method : method,
			data : data,
			dataType : "html" // Accept request header : Content-Type response header
			,
			success : function(resp) {
				$(resultArea).html(resp);
			},
			error : function(jqXhr, status, error) {
				console.log("jqXhr : ", jqXhr);
				console.log("status : ", status);
				console.log("error : ", error);
			}
		};
		$.ajax(settings);
		return false;
	});
});