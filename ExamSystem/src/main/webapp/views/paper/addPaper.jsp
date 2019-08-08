<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Subject</title>
<jsp:include page="../headerLink.jsp" />
</head>
<body>
	<div class="container">
		<jsp:include page="../menuBar.jsp" />
		<br> <br>
		
		<div class="card text-center">
			<div class="card-header bg-primary">
				<h3>Add Paper</h3>
			</div>
			<div class="card-body">
				<form:form method="post">
					<div class="form-group row">
						<form:label path="paperCode" class="col-sm-2 col-form-label">Paper Code</form:label>
						<div class="col-sm-10">
							<form:input type="text" class="form-control" path="paperCode"
								placeholder="Enter paper name" />
							<form:errors path="paperCode" cssClass="text-danger"></form:errors>
						</div>
					</div>
					<div class="form-group row">
						<form:label path="moduleName" class="col-sm-2 col-form-label">Subject Name</form:label>
						<div class="col-sm-10">
						<form:select path="moduleName" class="form-control" >
                      <form:option value="" label="Select Module" />
                      <form:options items="${sublist}" itemLabel="name"/>
                       </form:select>
							
							<form:errors path="moduleName" cssClass="text-danger"></form:errors>
						
						</div>
						
					</div>
					<div class="form-group row">
					<form:label path="isEnabled" class="col-sm-2 col-form-label">Paper status</form:label>
					<form:radiobutton path="isEnabled" class="form-control col-sm-2 col-form-label "></form:radiobutton>
					<form:radiobutton path="isEnabled" class="form-control col-sm-3 col-form-label "></form:radiobutton>
					</div>
					
					<form:button type="submit" class="btn btn-primary">Add Subject</form:button>
				</form:form>
			</div>
		</div>
	</div>
	<jsp:include page="../footerLink.jsp" />
</body>
</html>