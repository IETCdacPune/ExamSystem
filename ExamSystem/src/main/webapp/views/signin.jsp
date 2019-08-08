<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>signin</title>
<jsp:include page="./headerLink.jsp" />
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
				<div class="card card-signin my-5">
					<div class="card-body">
						<h1 class="card-title text-primary text-center display-1">
							<i class="fas fa-users"></i>
						</h1>
						<hr class="my-4">
						<form:form method="post">
							<div class="form-group row">
								<%-- <form:label path="prNo" class="col-sm-2 col-form-label">PRN</form:label> 
									<div class="col-sm-10">--%>
								<form:input type="text" path="prNo" class="form-control"
									placeholder="P. R. N." />
								<form:errors path="prNo" cssClass="text-danger"></form:errors>
								<!-- </div> -->
							</div>

							<div class="form-group row">
								<%-- <form:label path="password" class="col-sm-2 col-form-label">Password</form:label>
									<div class="col-sm-10"> --%>
								<form:input type="password" path="password" class="form-control"
									placeholder="Password" />
								<form:errors path="password" cssClass="text-danger"></form:errors>
								<!-- </div> -->
							</div>


							<button class="btn btn-lg btn-primary btn-block text-uppercase"
								type="submit">Sign in</button>

						</form:form>
						<hr class="my-4">
						<a class="btn btn-lg btn-primary btn-block text-uppercase"
							href="signup" type="submit">Sign up</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="./footerLink.jsp" />
</body>
</html>