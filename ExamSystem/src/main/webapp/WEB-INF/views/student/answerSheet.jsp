<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Exam System</title>
<jsp:include page="../headerLink.jsp" />
</head>
<body id="body">
<div class="container">
		<jsp:include page="../menuBar.jsp" />
			<c:if test="${not empty studPaper.paper.questionList}">
			<div class="row"><div class="col-3">
			<c:forEach items="${studPaper.paper.questionList}" var="question" varStatus="loop">
			
			</c:forEach>
			</div><div class="col-9">
			<c:forEach items="${studPaper.paper.questionList}" var="question" varStatus="loop">
				<div class="col-12 mt-1">
					<div class="card border-info pl-3">
						<div class="card-head">
							<pre>${loop.index +1 }) ${question.fullQuestion}</pre>
						</div>
						<div class="card-body">
							<c:forEach items="${question.optionList}" var="option"
								varStatus="loop">
								<c:choose>
									<c:when
										test="${option.opt == question.correctOption}">
										<c:set value="bg-success" var="cssClass"></c:set>
									</c:when>
									<c:when
										test="${option.opt != question.correctOption && option.opt == studPaper.studentAnsMap[question.queId]}">
										<c:set value="bg-danger" var="cssClass"></c:set>
									</c:when>
									<c:when
										test="${option.opt != question.correctOption}">
										<c:set value="bg-light" var="cssClass"></c:set>
									</c:when>
								</c:choose>
								<div class="alert ${cssClass}" role="alert"><pre>${option.opt}. ${option.answer}</pre></div>
							</c:forEach>
							<button class="btn btn-light" data-toggle="collapse"
								data-target="#demo${loop.index +1}">Description</button>

							<div id="demo${loop.index +1}" class="collapse">
								${question.description}</div>
						</div>
					</div>
				</div>
			</c:forEach>
			</div></div>
		</c:if>
		</div>
	<jsp:include page="../footerLink.jsp" />
</body>
</html>