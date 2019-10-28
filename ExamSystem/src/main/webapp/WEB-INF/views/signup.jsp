<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Exam System</title>
<jsp:include page="./headerLink.jsp" />
</head>
<body>
	<div class="container">
		<jsp:include page="./menuBar.jsp" />

		<div class="container">
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
			<div class="row">
				<div class="col-sm-9 col-md-9 col-lg-9 mx-auto">
					<div class="card card-signin my-5">
						<div class="card-body">
							<h1 class="card-title text-primary text-center display-1">
								<em class="fas fa-user-plus"></em>
							</h1>
							<hr class="my-4">
							<form:form method="post">
								<div class="form-group row">
									<form:label path="firstName" class="col-sm-3 col-form-label">Name</form:label>
									<div class="col-sm-9 row">
										<div class="col-sm-6">
											<form:input type="text" path="firstName" class="form-control"
												placeholder="First Name" />
											<form:errors path="firstName" cssClass="text-danger"></form:errors>
										</div>
										<div class="col-sm-6">
											<form:input type="text" path="lastName" class="form-control"
												placeholder="Last Name" />
											<form:errors path="lastName" cssClass="text-danger"></form:errors>
										</div>
									</div>
								</div>
								<div class="form-group row">
									<form:label path="courseCode" class="col-sm-3 col-form-label">Course Code</form:label>
									<div class="col-sm-9">
										<form:input type="text" path="courseCode" class="form-control"
											placeholder="Course Code" />
										<form:errors path="courseCode" cssClass="text-danger"></form:errors>
									</div>
								</div>
								<div class="form-group row">
									<form:label path="emailId" class="col-sm-3 col-form-label">Email</form:label>
									<div class="col-sm-9">
										<form:input type="text" path="emailId" class="form-control"
											placeholder="Email" />
										<form:errors path="emailId" cssClass="text-danger"></form:errors>
									</div>
								</div>
								<div class="form-group row">
									<form:label path="prn" class="col-sm-3 col-form-label">PRN</form:label>
									<div class="col-sm-9">
										<form:input type="text" path="prn" class="form-control"
											placeholder="P. R. N." />
										<form:errors path="prn" cssClass="text-danger"></form:errors>
									</div>
								</div>
								<div class="form-group row">
									<form:label path="password" class="col-sm-3 col-form-label">Password</form:label>
									<div class="col-sm-9">
										<form:input type="password" path="password"
											class="form-control" placeholder="Password" />
										<form:errors path="password" cssClass="text-danger"></form:errors>
									</div>
								</div>
								<div class="form-group row">
									<label for="conformPass" class="col-sm-3 col-form-label">Confirm
										Password</label>
									<div class="col-sm-9">
										<form:input type="password" id="conformPass"
											path="conformPass" class="form-control"
											placeholder="Conform Password" />
									</div>
								</div>
								<div class="form-group row">
									<form:label path="securityQeustion"
										class="col-sm-3 col-form-label">Security question</form:label>
									<div class="col-sm-9">
										<form:select path="securityQeustion" class="form-control">
											<form:option value="" label="Select security question" />
											<form:options items="${qList}" itemLabel="question" />
										</form:select>
										<form:errors path="securityQeustion" cssClass="text-danger"></form:errors>
									</div>
								</div>
								<div class="form-group row">
									<form:label path="securityAnswer"
										class="col-sm-3 col-form-label">PRN</form:label>
									<div class="col-sm-9">
										<form:input type="text" path="securityAnswer"
											class="form-control" placeholder="Security Answer" />
										<form:errors path="securityAnswer" cssClass="text-danger"></form:errors>
									</div>
								</div>
								<div class="col-md-8 offset-md-2">
									<button class="btn btn-lg btn-primary btn-block text-uppercase"
										type="submit">Sign up</button>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="./footerLink.jsp" />
	</div>
</body>
</html>