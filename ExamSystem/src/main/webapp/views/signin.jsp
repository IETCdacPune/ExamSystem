<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Exam System</title>
<jsp:include page="./headerLink.jsp" />
</head>
<body>
	<div class="container">
	<jsp:include page="./menuBar.jsp" />
		<div class="row">
			<div class="col-sm-9 col-md-8 col-lg-7 mx-auto">
				<div class="card card-signin my-5">
					<div class="card-body">
						<h1 class="card-title text-primary text-center display-1">
							<i class="fas fa-users"></i>
						</h1>
						<hr class="my-4">
						<c:if test="${not empty errorMessge}">
							<div class="alert alert-danger alert-dismissible fade show"
								role="alert">
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								${errorMessge}
							</div>
						</c:if>
						<form name='login' method='POST'>
							<div class="form-group row">
								<input type='text' name='username' class="form-control" value=''
									placeholder="P. R. N.">
							</div>

							<div class="form-group row">
								<input type='password' name='password' class="form-control" placeholder="Password" />
							</div>

							<button class="btn btn-lg btn-primary btn-block text-uppercase"
								type="submit">Sign in</button>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />

						</form>
						<!-- <hr class="my-4">
						<a class="btn btn-lg btn-primary btn-block text-uppercase"
							href="signup" type="submit">Sign up</a>-->
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="./footerLink.jsp" />
</body>
</html>