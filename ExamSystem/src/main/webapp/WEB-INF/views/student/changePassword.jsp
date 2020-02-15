<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
		<div class="row">
			<div class="col-sm-8 col-md-6 col-lg-7 mx-auto">
				<div class="card card-signin my-5">
					<div class="card-body">
						<h1 class="card-title text-primary text-center display-1">
							<em class="fas fa-users"></em>
						</h1>
						<hr class="my-4">
						<c:if test="${not empty errmsg}">
			<div class="alert alert-danger alert-dismissible">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Error!</strong> ${errmsg}
			</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="alert alert-success alert-dismissible">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Success!</strong> ${msg}
			</div>
		</c:if>
						<form:form action="/Student/changePassword" method='POST' autocomplete="off">
							<h3>${question}</h3>
							<div class="form-group row">
								<input type='text' name='answer' class="form-control" value=''
									placeholder="Enter your security answer">
							</div>
							<div class="form-group row">
								<input type="password" name='pass' class="form-control" value=''
									pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&])[a-zA-Z0-9!@#$%^&]{8,}$"
									title="Password size is 8 char. with 1 Cap,1 small, 1 number, 1 special symbole" placeholder="Enter your password">
							</div>
							<div class="form-group row">
								<input type="password" name='confPass' class="form-control" value=''
									pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&])[a-zA-Z0-9!@#$%^&]{8,}$"
									title="Password size is 8 char. with 1 Cap,1 small, 1 number, 1 special symbole" placeholder="Conform your password">
							</div>
							<button class="btn btn-lg btn-primary btn-block text-uppercase"
								type="submit">Submit</button>
						</form:form>
						<hr class="my-4">
						<a href="/signin" type="submit">Sign In</a>
					</div>
				</div>
			</div>
		</div>
		</div>
		<jsp:include page="../footerLink.jsp" />
	</div>
</body>
</html>