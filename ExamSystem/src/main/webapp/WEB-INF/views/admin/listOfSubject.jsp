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
	<div class="container">
		<jsp:include page="../menuBar.jsp" />
		<div class="row">
			<c:if test="${not empty list}">
				<c:forEach items="${list}" var="subject" varStatus="loop">
					<div class="col-md-4 mt-1">
						<div class="card bg-info">
							<div class="card-body">
								<h5 class="card-title">${loop.index +1 }
									${subject.name}<a class="close text-rigth"
										href="${pageContext.request.contextPath}/Admin/subjectEdit/${subject.id}"><em
										class="far fa-edit"></em></a>
									<br>under ${subject.course.name} course...
								</h5>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>

		</div>
		<c:if test="${empty list }">
			<div class="jumbotron text-center">
				<h2>
					There is not single subject entry in system.<br> <a
						class="btn btn-primary"
						href="${pageContext.request.contextPath}/Admin/addSubject">Add
						Subject</a>
				</h2>
				<br>
			</div>
		</c:if>
	</div>
	<jsp:include page="../footerLink.jsp" />
</body>
</html>
