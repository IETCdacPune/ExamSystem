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
				<div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
					<div class="card card-signin my-5">
						<div class="card-body">
							<h1 class="card-title text-primary text-center display-1">
								<i class="fas fa-users"></i>
							</h1>
							<hr class="my-4">
							<form>
								<div class="form-group">
									<input type="text" id="inputPRN" class="form-control"
										placeholder="PRN" required autofocus>

								</div>

								<div class="form-group">
									<input type="password" id="inputPassword" class="form-control"
										placeholder="Password" required>

								</div>


								<button class="btn btn-lg btn-primary btn-block text-uppercase"
									type="submit">Sign in</button>

							</form>
							<hr class="my-4">
								<a class="btn btn-lg btn-primary btn-block text-uppercase" href="signup"
									type="submit">Sign up</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="./footerLink.jsp" />
	</div>
</body>
</html>