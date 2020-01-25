<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en" style="height: 100%;">
<head>
<meta charset="ISO-8859-1">
<title>Exam System</title>
<jsp:include page="../headerLink.jsp" />
</head>
<body id="body" style="min-height: 100%;">
	<div class="card d-flex align-items-stretch">
		<div class="card-header h-25">
			<div class="row">
				<div class="col-sm-10">
					<sec:authentication var="user" property="principal" />
					<h4>PRN:- ${user.prn}</h4>
					<h6>Subject:- ${sessionScope.subject}</h6>
					<p>Readed:-${readed}; &nbsp;&nbsp;Marked for
						Review:-${mReview}; &nbsp;&nbsp;Not visited:-${unReaded};</p>
				</div>
				<div class="col-sm-2 align-items-right"><h1 id="timmer"></h1></div>
			</div>
		</div>
		<div class="card-body h-75">
		
			<div class="row">
				<div class="col-sm-2">
					<c:if test="${not empty list}">
						<div class="row">
							<c:forEach items="${list}" var="question" varStatus="loop">
								<c:choose>
									<c:when
										test="${question.read && question.markedReview && question.ans!=''}">
										<c:set value="btn-warning text-success" var="cssClass"></c:set>
									</c:when>
									<c:when test="${question.read && question.markedReview}">
										<c:set value="btn-warning" var="cssClass"></c:set>
									</c:when>
									<c:when
										test="${question.read && !question.markedReview && question.ans!=''}">
										<c:set value="btn-success" var="cssClass"></c:set>
									</c:when>
									<c:when test="${question.read && !question.markedReview}">
										<c:set value="btn-danger" var="cssClass"></c:set>
									</c:when>
									<c:when test="${!question.read}">
										<c:set value="btn-secondary" var="cssClass"></c:set>
									</c:when>
								</c:choose>
								<a href="/student/mcqExam/${loop.index+1}"
									class="col-sm-4 btn ${cssClass}" style="border-radius: 50%;"><h3>${loop.index+1}</h3></a>
							</c:forEach>
						</div>
						<div class="row h-25">
							<div class="col-sm-6 align-self-center">
								<a href="/student/exam/submit" class="btn btn-info" id="submit">Submit</a>
							</div>
						</div>
					</c:if>
				</div>
				<div class="col-sm-10">
					<form>
						<div class="card border-info">
							<div class="card-head">
								<h4>
									<pre>Q${index}. ${question.fullQuestion}</pre>
								</h4>
								<input type="hidden" name="queId" value="${question.queId}">
								<input type="hidden" name="index" value="${index}">
							</div>
							<div class="card-body">
								<c:forEach items="${question.optionList}" var="option"
									varStatus="loop">
									<div class="form-check">
										<input class="form-check-input" type="radio" name="ans"
											id="exampleRadios${loop.index+1}" value="${option.opt}" ${question.ans==option.opt?'checked':''}>
											<label class="form-check-label"
												for="exampleRadios${loop.index+1}"><pre>${option.answer}</pre></label>
									</div>
								</c:forEach>
							</div>
							<div class="card-footer ">
								<button formaction="/student/mcqExamPost/markedReview"
									formmethod="post" class="btn btn-warning" type="submit">Marked
									for Review And Next</button>
								<button formaction="/student/mcqExamPost/next" formmethod="post"
									class="btn btn-info" type="submit">Next</button>
								<button formaction="/student/mcqExamPost/save" formmethod="post"
									class="btn btn-primary" type="submit">Save & Next</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var inTwoDigit=function(valueTOconvert){
		return valueTOconvert<10?"0"+valueTOconvert : valueTOconvert;
	}
	var displayTime=function(time){
		var minutes = Math.floor((time % (1000 * 60 * 60)) / (1000 * 60));
		var seconds = Math.floor((time % (1000 * 60)) / 1000);
		$("#timmer").text( inTwoDigit(minutes) + "m : " + inTwoDigit(seconds) + "s ");
	}
	$(document).ready(function(){
			var time=${remainingTime};
			displayTime(time);
			var x = setInterval(()=>{
					time-=1000;
					if (time < 0) {
					    clearInterval(x);
					    $("#submit")[0].click();
					}
					displayTime(time);
				},1000);
			$("#timmer").html=time;
		});
	</script>
</body>
</html>