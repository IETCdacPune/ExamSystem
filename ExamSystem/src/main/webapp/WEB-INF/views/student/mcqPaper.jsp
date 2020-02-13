<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
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
<body id="body" style="margin: 0">
	<div class="card" style="height: 100vh;">
		<div class="card-header" style="height:15%;">
			<div class="row">
				<div class="col-10">
					<sec:authentication var="user" property="principal" />
					<h4>PRN:- ${user.prn}  &nbsp; &nbsp; &nbsp; &nbsp; Subject:- ${sessionScope.subject}</h4>
					<p>Readed:-${readed}; &nbsp;&nbsp;Marked for
						Review:-${mReview}; &nbsp;&nbsp;Not visited:-${unReaded};</p>
				</div>
				<div class="col-2">
					<h4 id="timmer"></h4>
				</div>
			</div>
		</div>
		<div class="card-body" style="height:85%;">
			<div class="row h-100">
				<div class="col-3 h-100">
					<c:if test="${not empty list}">
						<div class="bg-light" style="overflow: auto;">
							<c:forEach items="${list}" var="question" varStatus="loop">
								<c:choose>
									<c:when
										test="${question.read && question.markedReview && question.ans!=''}">
										<c:set value="btn-warning text-success" var="cssClass"></c:set>
									</c:when>
									<c:when test="${question.read && question.markedReview}">
										<c:set value="btn-warning text-danger" var="cssClass"></c:set>
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
										<a href="/Student/mcqExam/${loop.index+1}"
											class="btn ${cssClass} my-1" style="border-radius: 30%;"><c:if
												test="${loop.index<9 }">0${loop.index+1}</c:if><c:if
												test="${loop.index>=9 }">${loop.index+1}</c:if></a>
							</c:forEach>
						</div>
						<div class="row">
							<div class="col-sm-6 align-self-center">
								<a href="/Student/exam/submit" onclick="return confirm('Are you sure to sbumit the exam?');" class="btn btn-info" id="submit">Submit</a>
							</div>
						</div>
					</c:if>
				</div>
				<div class="col-9 h-100">
						<div class="card border-info h-100">
						<form:form class="h-100">
							<div class="card-head" style="height:30%;overflow: auto; width: 100%;">
								<h5 class="m-1">
									<c:set var="newline" value="<%= \"\n\" %>" />
									Q${index}. ${fn:replace(question.fullQuestion,newline, "<br />")}
									
								</h5>
								<input type="hidden" name="queId" value="${question.queId}">
								<input type="hidden" name="index" value="${index}">
							</div>
							<div class="card-body" style="height:55%;overflow: auto;"> 
							<div data-spy="scroll" data-offset="0" class="btn-group btn-group-vertical w-100" data-toggle="buttons">
								<c:forEach items="${question.optionList}" var="option"
									varStatus="loop">
									<label class="btn btn-light text-left m-1 p-1 w-100 ${question.ans==option.opt?'active':''}">
										<input type="radio" name="ans"
											id="exampleRadios${loop.index+1}" value="${option.opt}"
											${question.ans==option.opt?'checked':''}>${fn:replace(option.answer,newline, "<br />")}
									</label>
								</c:forEach>
								</div>
							</div>
							<div class="card-footer"  style="height:15%;">
								<button formaction="/Student/mcqExamPost/previous" formmethod="post"
									class="btn btn-light" type="submit">Previous Question</button> <button formaction="/Student/mcqExamPost/markedReview"
									formmethod="post" class="btn btn-warning" type="submit">Marked
									for Review And Next</button> <button formaction="/Student/mcqExamPost/save" formmethod="post"
									class="btn btn-primary" type="submit">Save & Next</button>
							</div>
					</form:form>
						</div>
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