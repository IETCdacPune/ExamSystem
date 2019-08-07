<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Subject</title>
<jsp:include page="../headerLink.jsp" />
</head>
<body>
	<div class="container">
		<jsp:include page="./menuBar.jsp" />
		<br>
		<div class="row">
			<c:forEach items="${list}" var="subject" varStatus="loop">
				<div class="col-md-4 mt-1">
					<div class="card bg-info">
						<div class="card-body">
							<h5 class="card-title">${loop.index +1 } ${subject.name}<a class="text-rigth" href="/Admin/subjectEdit/${subject.id}"><i class="far fa-edit"></i></a></h5>
							<!-- <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
							<p class="card-text">Some quick example text to build on the
								card title and make up the bulk of the card's content.</p> -->
							<a href="/Admin/allPapers/${subject.name}" class="text-white">All
								Papers of this subject</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<jsp:include page="../footerLink.jsp" />
</body>
</html>
