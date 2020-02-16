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
		<c:if test="${not empty msg}">
			<div class="alert alert-success alert-dismissible">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Success!</strong> ${msg}
			</div>
		</c:if>
		<c:if test="${not empty errmsg}">
			<div class="alert alert-danger alert-dismissible">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Error!</strong> ${errmsg}
			</div>
		</c:if>
		<c:if test="${not empty courseList }">
			<div class="card text-center col-8 mx-auto">
				<div class="card-header bg-primary">
					<h3>Add Paper</h3>
				</div>
				<div class="card-body">
					<form:form method="post" enctype="multipart/form-data" autocomplete="off">
						<div class="form-group row">
							<form:label path="paperCode" class="col-sm-2 col-form-label">Paper Code</form:label>
							<div class="col-sm-10">
								<form:input type="text" class="form-control" path="paperCode"
									placeholder="Enter paper name" />
								<form:errors path="paperCode" cssClass="text-danger"></form:errors>
							</div>
						</div>
						<div class="form-group row">
							<form:label path="course" class="col-sm-2 col-form-label">Course Name</form:label>
							<div class="col-sm-10">
								<form:select id="course" path="course" class="form-control">
									<form:option value="" label="Select Module" />
									<form:options items="${courseList}" itemLabel="name" />
								</form:select>
								<form:errors path="course" cssClass="text-danger"></form:errors>
							</div>
						</div>
						<div class="form-group row">
							<form:label  path="subject" class="col-sm-2 col-form-label">Subject Name</form:label>
							<div class="col-sm-10">
								<form:select id="subject" path="subject" class="form-control">
									<form:option value="" label="Select Module" />
									<%--<form:options items="${subList}" itemLabel="name" />${course.name} : ${name} </form:options>--%>
								</form:select>
								<form:errors path="subject" cssClass="text-danger"></form:errors>
							</div>
						</div>
						<div class="form-group row">
							<form:label path="paperTiming" class="col-sm-2 col-form-label">Paper Timing</form:label>
							<div class="col-sm-10">
								<form:input type="text" class="form-control" path="paperTiming"
									placeholder="Enter paper timing in min only" />
								<form:errors path="paperTiming" cssClass="text-danger"></form:errors>
							</div>
						</div>
						<div class="form-group row">
							<label id="paperFile" class="col-sm-2 col-form-label">Question
								File</label>
							<div class="col-sm-10">
								<input type="File" class="form-control-file" name="file"
									id="paperFile"
									accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" />
								<%-- <form:errors path="paperTiming" cssClass="text-danger"></form:errors> --%>
							</div>
						</div>
						<%-- <div class="form-group row">
					<form:label path="enabled" class="col-sm-2 col-form-label">Paper status</form:label>
					<form:radiobutton path="enabled" class="form-control col-sm-2 col-form-label "></form:radiobutton>
					</div> --%>
						<form:button type="submit" class="btn btn-primary">Add Paper</form:button>
						<form:button type="reset" class="btn btn-danger">Reset form</form:button>
					</form:form>
				</div>
			</div>
		</c:if>
		<c:if test="${empty courseList }">
			<div class="jumbotron text-center">
				<h2>
					There is not single course entry in system.<br> <a
						class="btn btn-primary"
						href="${pageContext.request.contextPath}/Admin/addCourse">Add
						Course</a>
				</h2>
				<br>
			</div>
		</c:if>
	</div>
	<jsp:include page="../footerLink.jsp" />
</body>
</html>