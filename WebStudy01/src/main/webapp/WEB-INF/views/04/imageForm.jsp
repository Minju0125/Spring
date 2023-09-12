 <%@ page contentType="text/html; charset=UTF-8" %>
 <html>
 <body>
<form onsubmit='submitHandler(event);' action='<%=request.getContextPath() %>/image.do'>
 <select name='image' id='image' onchange='this.form.requestSubmit();'>                                 ");
		<%=request.getAttribute("options") %>
 </select>
 <input type='submit' value='전송'>
 </form>
<div id="imageArea">
	
</div>
 <script>
 	function submitHandler(event) {
 		event.preventDefault();
 		let imageName = image.value;
 		imageArea.innerHTML = `<img src="<%=request.getContextPath() %>/image.do?image=\${imageName}"/>`;
 	}
 </script>
 </body>
 </html>