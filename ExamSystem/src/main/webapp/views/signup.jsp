<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="./headerLink.jsp" />
</head>
<body>
	<div class="container">
		<div class="container">
			<div class="row">
				<div class="col-sm-9 col-md-9 col-lg-9 mx-auto">
					<div class="card card-signin my-5">
						<div class="card-body">
							<h1 class="card-title text-primary text-center display-1">
								<i class="fas fa-user-plus"></i>
							</h1>
							<hr class="my-4">
							<form:form method="post">
								<div class="form-group row">
									<form:label path="firstName" class="col-sm-2 col-form-label">Name</form:label>
									<div class="col-sm-5">
										<form:input type="text" path="firstName" class="form-control"
											placeholder="First Name" />
										<form:errors path="firstName" cssClass="text-danger"></form:errors>
									</div>
									<div class="col-sm-5">
										<form:input type="text" path="lastName" class="form-control"
											placeholder="Last Name" />
										<form:errors path="lastName" cssClass="text-danger"></form:errors>
									</div>
								</div>
								<div class="form-group row">
									<form:label path="emailId" class="col-sm-2 col-form-label">Email</form:label>
									<div class="col-sm-10">
										<form:input type="text" path="emailId" class="form-control"
											placeholder="Email" />
										<form:errors path="emailId" cssClass="text-danger"></form:errors>
									</div>
								</div>
								<div class="form-group row">
									<form:label path="prNo" class="col-sm-2 col-form-label">PRN</form:label>
									<div class="col-sm-10">
										<form:input type="text" path="prNo" class="form-control"
											placeholder="P. R. N."  />
										<form:errors path="prNo" cssClass="text-danger"></form:errors>
									</div>
								</div>

								<div class="form-group row">
									<form:label path="password" class="col-sm-2 col-form-label">Password</form:label>
									<div class="col-sm-10">
										<form:input type="password" path="password"
											class="form-control" placeholder="Password"  />
										<form:errors path="password" cssClass="text-danger"></form:errors>
									</div>
								</div>

								<div class="col-md-8 offset-md-2">
									<button class="btn btn-lg btn-primary btn-block text-uppercase"
										type="submit">Sign up</button>
								</div>
							</form:form>
							<hr class="my-4">
							<div class="col-md-8 offset-md-2">
								<a class="btn btn-lg btn-primary btn-block text-uppercase"
									href="signin" type="submit">Sign in</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="./footerLink.jsp" />
	</div>
</body>
</html>