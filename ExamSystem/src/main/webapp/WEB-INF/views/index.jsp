<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Exam System</title>
<jsp:include page="./headerLink.jsp" />
</head>
<body>
	<div class="m-1">
		<jsp:include page="./menuBar.jsp" />
		<div id="mainCarosel" class="carousel slide h-100 w-100" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#mainCarosel" data-slide-to="0" class="active"></li>
				<li data-target="#mainCarosel" data-slide-to="1"></li>
				<li data-target="#mainCarosel" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img class="d-block w-100" src="/img1.jpg" alt="First slide">
					<div class="carousel-caption d-none d-md-block">
						<h5>Taking the lead for education quality</h5>
						<p>Educating the mind without educating the heart is no education at all.</p>
					</div>
				</div>
				<div class="carousel-item">
					<img class="d-block w-100" src="/img2.jpg" alt="Second slide">
					<div class="carousel-caption d-none d-md-block">
						<h5>Education is your door to future</h5>
						<p>Education is a PATH, not a DESTINATION.</p>
					</div>
				</div>
				<div class="carousel-item">
					<img class="d-block w-100" src="/img3.jpg" alt="Third slide">
					<div class="carousel-caption d-none d-md-block">
						<h5>The number one source for education</h5>
						<p>Education is the most powerful weapon which you can use to change the world.</p>
					</div>
				</div>
			</div>
			<a class="carousel-control-prev" href="#mainCarosel" role="button"
				data-slide="prev"> <span class="carousel-control-prev-icon"
				aria-hidden="true"></span> <span class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#mainCarosel" role="button"
				data-slide="next"> <span class="carousel-control-next-icon"
				aria-hidden="true"></span> <span class="sr-only">Next</span>
			</a>
		</div>
		<jsp:include page="./footerLink.jsp" />
	</div>
</body>
</html>