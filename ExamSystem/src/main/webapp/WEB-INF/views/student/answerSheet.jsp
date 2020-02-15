<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Exam System</title>
<jsp:include page="../headerLink.jsp" />
<script type="text/javascript">
$(document).ready(function () {
	$('body').bind('cut copy paste', function (e) {
        e.preventDefault();
    });
    $("body").on("contextmenu",function(e){
        return false;
    });
});
</script>
</head>
<body id="body">
	<div class="m-1">
		<jsp:include page="../menuBar.jsp" />
		<c:if test="${not empty studPaper.paper.questionList}">
			<div class="row" style="height: 80vh;">
				<div class="col-3 h-100">
					<div id="list-tab" role="tablist">
						<c:forEach items="${studPaper.paper.questionList}" var="question"
							varStatus="loop">
							<c:choose>
								<c:when test="${empty studPaper.studentAnsMap[question.queId]}">
									<c:set value="bg-light" var="cssClass"></c:set>
								</c:when>
								<c:when
									test="${question.correctOption != studPaper.studentAnsMap[question.queId]}">
									<c:set value="bg-danger" var="cssClass"></c:set>
								</c:when>
								<c:when
									test="${question.correctOption == studPaper.studentAnsMap[question.queId]}">
									<c:set value="bg-success" var="cssClass"></c:set>
								</c:when>
							</c:choose>
							<a class="btn ${cssClass} my-1" id="linkQuestion${loop.index+1}"
								data-toggle="list" href="#question${loop.index+1}" role="tab"
								aria-controls="question${loop.index+1}"><c:if
									test="${loop.index<9 }">0${loop.index+1}</c:if> <c:if
									test="${loop.index>=9 }">${loop.index+1}</c:if></a>
						</c:forEach>
					</div>
					<div>
					<p class="m-1"><a class="btn bg-success my-1">01</a> correct answer.</p>
					<p class="m-1"><a class="btn bg-danger my-1">01</a> wrong answer.</p>
					<p class="m-1"><a class="btn bg-light my-1">01</a> leaved question.</p>
					</div>
				</div>
				<div class="col-9 h-100">
					<div class="tab-content h-100" id="nav-tabContent">
						<c:forEach items="${studPaper.paper.questionList}" var="question"
							varStatus="loop">
							<div
								class="tab-pane h-100 fade <c:if test="${loop.first}">show active</c:if>"
								id="question${loop.index+1}" role="tabpanel"
								aria-labelledby="linkQuestion${loop.index+1}">
								<div class="card h-100 pl-3" style="overflow: auto;">
									<div class="card-head">
									<c:set var="newline" value="<%= \"\n\" %>" />
										Q${index}. ${fn:replace(question.fullQuestion,newline, "<br />")}
									</div>
									<div class="card-body">
										<c:forEach items="${question.optionList}" var="option"
											varStatus="loop">
											<c:choose>
												<c:when test="${option.opt == question.correctOption}">
													<c:set value="bg-success" var="cssClass"></c:set>
												</c:when>
												<c:when
													test="${option.opt != question.correctOption && option.opt == studPaper.studentAnsMap[question.queId]}">
													<c:set value="bg-danger" var="cssClass"></c:set>
												</c:when>
												<c:when test="${option.opt != question.correctOption}">
													<c:set value="bg-light" var="cssClass"></c:set>
												</c:when>
											</c:choose>
											<div class="alert ${cssClass} m-1 p-1" role="alert">
											${option.opt}. ${fn:replace(option.answer,newline, "<br />")}
												
											</div>
										</c:forEach>
										<button class="btn btn-light" data-toggle="collapse"
											data-target="#demo${loop.index +1}">Description</button>

										<div id="demo${loop.index +1}" class="collapse">
											${question.description}</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</c:if>
	</div>
	<jsp:include page="../footerLink.jsp" />
</body>
</html>