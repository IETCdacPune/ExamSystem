<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Exam System</title>
<jsp:include page="../headerLink.jsp" />
<script type="text/javascript">
$(document).ready(function() { 
	$('#course').change(
		function() {
			$.getJSON('/Admin/subjectByCourse', {
				course : $(this).val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option value="">Select Subject</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].id + '">'
							+ data[i].name + '</option>';
				}
				html += '</option>';
 
				$('#subject').html(html);
			});
		});
});
</script>
</head>
<body>
	<div class="m-1">
		<jsp:include page="../menuBar.jsp" />
		
		
			<div class="card text-center">
				
				<div class="card-body">
					<form:form method="post" enctype="multipart/form-data">
						
						
						
						<div class="form-group row">
							<label id="paperFile" class="col-sm-2 col-form-label">Question
								File</label>
							<div class="col-sm-10">
								<input type="File" class="form-control-file" name="file"
									id="paperFile"
									accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" />
								
							</div>
						</div>
						
						
						
						<button type="submit" class="btn btn-primary">Add Question</button>
						
					</form:form>
				</div>
			</div>
		
	</div>
	
	<jsp:include page="../footerLink.jsp" />
</body>
</html>