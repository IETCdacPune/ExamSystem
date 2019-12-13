<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
		<c:if test="${not empty errmsg}">
			<div class="alert alert-danger alert-dismissible">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Error!</strong> ${errmsg }
			</div>
		</c:if>
		<c:if test="${empty list}">
			<div class="jumbotron text-center">
				<h2>
					There is not single subject entry in system.<br>
					
				</h2>
			</div>
		</c:if>
		<c:if test="${not empty list}">
				<c:forEach items="${list}" var="subject" varStatus="loop">
					<div class="card mt-2">
						<div class="card-header">
							<a class="card-link" data-toggle="collapse" href="#collapse${loop.index +1 }">Subject:-${subject.name} have ${fn:length(subject.paperList)} papers.
							</a>
						</div>
						<div id="collapse${loop.index +1}" class="collapse">
							<div class="card-body">
								<c:if test="${not empty subject.paperList}">
								<div class="row">
								<c:forEach items="${subject.paperList}" var="paper" varStatus="loop">						
								<div class="col-md-4 mt-1">
									<div class="card bg-info">
										<div class="card-body">
											<h5 class="card-title">${loop.index +1 })
											Subject:-${subject.name}
											</h5>
											<h6 class="card-subtitle mb-2">Paper
												Timing:-${paper.paperTiming}</h6>
											
											<a
												href="${pageContext.request.contextPath}/student/startExam/"
												class="text-white">Start Exam</a>
										</div>
									</div>
								</div>
								</c:forEach>
								</div>
								</c:if>
								<c:if test="${empty subject.paperList}">
									<div class="jumbotron text-center">
										<h2>
											There is not single paper entry in ${subject.name} subject.<br>
										</h2>
									</div>
								</c:if>
							</div>
						</div>
					</div>

				</c:forEach>
		</c:if>
	</div>
	<jsp:include page="../footerLink.jsp" />
</body>
</html>