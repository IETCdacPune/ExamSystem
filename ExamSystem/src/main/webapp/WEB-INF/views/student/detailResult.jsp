<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Exam System</title>
<jsp:include page="../headerLink.jsp" />
<script type="text/javascript" src="/Chart.min.js"></script>
<script type="text/javascript">
fillCanvas=function(canvasId,label,graphData,colors,titleText){
	var ctx = $('#'+canvasId);
	var myChart = new Chart(ctx, {
	    type: 'doughnut',
	    data: {
	        labels: label,
	        datasets: [{
	            data: graphData,
	            backgroundColor: colors,
	            borderWidth: 1
	        }]
	    },
	    options: {
	    	title: {
	            display: true,
	            text: titleText
	        },
	        legend: {
	            display: false
	        }
	    }
	});
}
var totalMarks=${studPaper.paper.numOfQuestion};
var obtainedMarks=${studPaper.marks};
var totalAttempt=${attempt};
$(document).ready(function(){
	
	fillCanvas("canvasResult",["Correct","Wrong","Unattempted"],[obtainedMarks,totalAttempt-obtainedMarks,totalMarks-totalAttempt],["green","red","orange"],"Result");
	fillCanvas("canvasAccuracy",["Accuracy","Unaccurate"],[Math.round(obtainedMarks*100/totalAttempt),Math.round((totalAttempt-obtainedMarks)*100/totalAttempt)],["green","red"],"Accuracy");
});
</script>
</head>
<body>
	<div class="container">
		<jsp:include page="../menuBar.jsp" />
		<div class="row">
			<div class="col">
				<canvas class="w-100 h-100" id="canvasResult"></canvas>
			</div>
			<div class="col">
				<canvas class="w-100 h-100" id="canvasAccuracy"></canvas>
			</div>
		</div>
		<hr>
		<h3>Top Five Students</h3>
		<c:forEach var="studPaper" items="${topFive}" varStatus="loop">
			<c:choose>
				<c:when test="${loop.index == 0}">
					<c:set value="1st" var="rank"></c:set>
				</c:when>
				<c:when test="${loop.index == 1}">
					<c:set value="2nd" var="rank"></c:set>
				</c:when>
				<c:when test="${loop.index == 2}">
					<c:set value="3rd" var="rank"></c:set>
				</c:when>
				<c:when test="${loop.index == 3}">
					<c:set value="4th" var="rank"></c:set>
				</c:when>
				<c:when test="${loop.index == 4}">
					<c:set value="5th" var="rank"></c:set>
				</c:when>
				<c:when test="${loop.index == 5}">
					<c:set value="6th" var="rank"></c:set>
				</c:when>
			</c:choose>
			<div class="media my-1">
			  <img class="mr-3" style="width: 50px;height: 50px" src="/Common/avatar/${studPaper.student.imgUrl}" alt="${studPaper.student.firstName} ${studPaper.student.lastName}">
			  <div class="media-body">
			    <h5 class="mt-0">${rank} PRN:-${studPaper.student.prn}  Name:-${studPaper.student.firstName} ${studPaper.student.lastName}</h5>
			    	<div class="progress">
					  <div class="progress-bar progress-bar-striped" style="width:<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${ studPaper.marks*100/studPaper.paper.numOfQuestion}" />%">
					  	${studPaper.marks}
					  </div>
					</div>
			  </div>
			</div>
		</c:forEach>
		<hr>
		<c:if test="${studPaper.paper.correctAnsVisibility}"><a class="btn btn-light" href="/Student/AnswerSheet/${studPaper.id}">View Answer Sheet</a></c:if>
		<jsp:include page="../footerLink.jsp" />
	</div>
</body>
</html>