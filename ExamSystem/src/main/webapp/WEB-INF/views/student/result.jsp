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
<script type="text/javascript" src="/Chart.min.js"></script>
<script type="text/javascript">
var subjectArr=[<c:forEach items="${list}" var="map" varStatus="loop">
	{'name':'${map.key.name}','arrays':{
		'marks':[<c:forEach items="${map.value}" var="studPaper" varStatus="loop1">${studPaper.marks}<c:if test="${!loop1.last}">,</c:if></c:forEach>],
		'status':[<c:forEach items="${map.value}" var="studPaper" varStatus="loop1">'${studPaper.result}'<c:if test="${!loop1.last}">,</c:if></c:forEach>],
		'paperCode':[<c:forEach items="${map.value}" var="studPaper" varStatus="loop1">'${studPaper.paper.paperCode} ${studPaper.paperDate}'<c:if test="${!loop1.last}">,</c:if></c:forEach>]	
		}
	}<c:if test="${!loop.last}">,</c:if>
	</c:forEach>];
loadGraph=function(canvas,subject){
	marks=[];
	label=[];
	status=[];
	statusSize=0;
	subjectArr.forEach((sub)=>{
		if(sub.name===subject){
			marks=sub.arrays.marks;
			label=sub.arrays.paperCode;
			status=sub.arrays.status;
		}
	});
	if(marks.length<=4){
		s=8-marks.length;
		for(i=0;i<s;i++){
			marks.push(0);
			label.push('');
		}
	}
	var colors = []
	for(var i = 0; i < status.length; i++){
	   var color;
	   if(status[i]=='Pass'){
	           color = "green";
	}else if(status[i]=='Failed'){
	           color = "red";
	   }
	   colors[i] = color;
	}
	var ctx = $('#'+canvas);
	var myChart = new Chart(ctx, {
	    type: 'bar',
	    data: {
	        labels: label,
	        datasets: [{
	            data: marks,
	            backgroundColor: colors,
	            borderWidth: 1
	        }]
	    },
	    options: {
	    	title: {
	            display: true,
	            text: 'Marks in percentage'
	        },
	        legend: {
	            display: false
	        },
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero: true,
	                    steps: 10,
                        stepValue: 5,
	                    max: 40
	                }
	            }]
	        }
	    }
	});
}
</script>
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
				<h2>Not A Single Exam Given.</h2>
			</div>
		</c:if>
		<c:if test="${not empty list}">
			<c:forEach items="${list}" var="map" varStatus="loop">
				<div class="card mt-2">
					<div class="card-header">
						<a class="card-link" data-toggle="collapse"
							href="#collapse${loop.index +1 }" onclick="loadGraph('canvas${loop.index +1}','${map.key.name}');">Subject:-${map.key.name}
							have ${fn:length(map.value)} given paper. </a>
					</div>
					<div id="collapse${loop.index +1}" class="collapse">
						<div class="card-body">
							<c:if test="${not empty map.value}">
									<div class="card">
									 <canvas class="w-100 h-100" id="canvas${loop.index +1}"></canvas>
									 <div class="row">
									 <c:forEach items="${map.value}" var="studPaper" varStatus="loop1">
									 	<div class="col-3"><a class="btn btn-info" href="${pageContext.request.contextPath}/Student/paperDetails/${studPaper.id}">${studPaper.paper.paperCode}</a></div>
									</c:forEach>
									</div>
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