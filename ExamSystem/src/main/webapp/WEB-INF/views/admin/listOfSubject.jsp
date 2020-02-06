<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		
	<c:if test="${not empty courseList}">
			<c:forEach items="${courseList}" var="course" varStatus="loop">
				<div class="card mt-2">
					<div class="card-header">
						<a class="card-link" data-toggle="collapse"
							href="#collapse${loop.index +1 }">Course:-${course.name} have
							${fn:length(course.subjectList)} subjects. </a>
					</div>

	<div id="collapse${loop.index +1}" class="collapse">
						<div class="card-body">
							<c:if test="${not empty course.subjectList}">
								<c:forEach items="${course.subjectList}" var="subject"
									varStatus="loop1">
									<div class="card mt-2">
										<div class="card-header">
											<a class="card-link" data-toggle="collapse"
												href="#collapse">Subject:-${subject.name}</a>
										</div>
										</div>
										</c:forEach>
										</c:if>
										</div>
										</div>
										</div>
				</c:forEach>
			</c:if>

		
		<c:if test="${empty courseList }">
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
