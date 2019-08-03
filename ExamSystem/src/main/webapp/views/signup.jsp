<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
							<h1 class="card-title text-primary text-center display-1" >
								<i class="fas fa-user-plus"></i>
							</h1>
							<hr class="my-4">
							<form>
								<div class="form-group row">
									<label for="inputName" class="col-sm-2 col-form-label">Name</label>
									<div class="col-sm-5">
										<input type="text" id="inputName" class="form-control"
											placeholder="First Name" required autofocus>
									</div>
									<div class="col-sm-5">
										<input type="text" id="inputName" class="form-control"
											placeholder="Last Name" required>
									</div>
								</div>
								<div class="form-group row">
									<label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
									<div class="col-sm-10">
										<input type="email" id="inputEmail" class="form-control"
											placeholder="Email" required>
									</div>
								</div>
								<div class="form-group row">
									<label for="inputPRN" class="col-sm-2 col-form-label">PRN</label>
									<div class="col-sm-10">
										<input type="text" id="inputPRN" class="form-control"
											placeholder="PRN" required>
									</div>
								</div>

								<div class="form-group row">
									<label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
									<div class="col-sm-10">
										<input type="password" id="inputPassword" class="form-control"
											placeholder="Password" required>
									</div>
								</div>

								<div class="col-md-8 offset-md-2">
								<button class="btn btn-lg btn-primary btn-block text-uppercase"
									type="submit">Sign up</button>
								</div>
							</form>
							<hr class="my-4">
							<div class="col-md-8 offset-md-2">
								<a class="btn btn-lg btn-primary btn-block text-uppercase" href="signin"
									type="submit">Sign in</a>
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