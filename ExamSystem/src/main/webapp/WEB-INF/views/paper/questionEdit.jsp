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
</head>
<body>
	<div class="m-1">
		<jsp:include page="../menuBar.jsp" />
		<c:if test="${not empty msg}">
		<div class="alert alert-success alert-dismissible">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong>Success!</strong> ${msg }
		</div>
		</c:if>
		<c:if test="${not empty errmsg}">
		<div class="alert alert-danger alert-dismissible">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong>Error!</strong> ${errmsg }
		</div>
		</c:if>
		<div class="card text-center">
			<div class="card-header bg-primary">
				<h3>Edit Question</h3>
			</div>
			<div class="card-body">
				<form:form method="post">
					<div class="form-group row">
						<form:hidden path="queId"/>
						<form:label path="fullQuestion" class="col-sm-2 col-form-label">Question</form:label>
						<div class="col-sm-10">
							<form:textarea cols="4" class="form-control" path="fullQuestion" />
							<form:errors path="fullQuestion" cssClass="text-danger"></form:errors>
						</div>
					</div>
					<div class="form-group row">
						<form:label path="correctOption" class="col-sm-2 col-form-label">Correct Option</form:label>
						<div class="col-sm-10">
							<form:input type="text" class="form-control" path="correctOption" />
							<form:errors path="correctOption" cssClass="text-danger"></form:errors>
						</div>

					</div>
					<c:if test="${empty optionList}">
						${optionList}
						option list is empty
					</c:if>
					<c:forEach items="${optionList}" var="option" varStatus="loop">
						<div class="form-group row">
							
						</div>
					</c:forEach>
					<form:hidden path="paper"/>
					<div class="form-group row">
						<form:label path="description" class="col-sm-2 col-form-label">Description</form:label>
						<div class="col-sm-10">
							<form:textarea cols="4" class="form-control" path="description" />
							<form:errors path="description" cssClass="text-danger"></form:errors>
						</div>
					</div>

					<form:button type="submit" class="btn btn-primary">Edit Question</form:button>
					<form:button type="reset" class="btn btn-danger">Reset form</form:button>
				</form:form>
			</div>
		</div>
	</div>
	<jsp:include page="../footerLink.jsp" />
</body>
</html>