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
	<div class="m-1">
		<jsp:include page="../menuBar.jsp" />
		<c:if test="${not empty list}">
			<c:forEach items="${list}" var="course" varStatus="loop">
				<div class="card w-100 mt-2">
					<div class="card-header">
						<a class="card-link" data-toggle="collapse"
							href="#collapse${loop.index +1 }">
							<h3>Course:-${course.name}</h3>
							<h4>Course Code:-${course.courseCode}</h4> have
							${fn:length(course.subjectList)} subjects.
						</a>
					</div>
					<div id="collapse${loop.index +1}" class="collapse">
						<div class="card-body">
							<c:if test="${empty course.subjectList}">
								<div class="jumbotron text-center">
									<h2>
										There is not single subject entry in ${course.name} course.<br>
										<a class="btn btn-primary"
											href="${pageContext.request.contextPath}/Admin/addSubject">Add
											Subject</a>
									</h2>
								</div>
							</c:if>
							<c:if test="${not empty course.subjectList}">
								<ul class="list-group">
									<c:forEach items="${course.subjectList}" var="subject"
										varStatus="loop">
										<li class="list-group-item"><h4>${loop.index +1})
												${subject.name}</h4></li>
									</c:forEach>
								</ul>
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:if>
		<c:if test="${empty list }">
			<div class="jumbotron text-center">
				<h2>
					There is not single course entry in system.<br> <a
						class="btn btn-primary"
						href="${pageContext.request.contextPath}/Admin/addCourse">Add
						Course</a>
				</h2>
				<br>
			</div>
		</c:if>
	</div>
	<jsp:include page="../footerLink.jsp" />
</body>
</html>
