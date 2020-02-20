<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Exam System</title>
<jsp:include page="../headerLink.jsp" />
<script type="text/javascript" src="/Chart.min.js"></script>
<script type="text/javascript">
fillCanvas=function(canvasId,graphType,label,graphData,colors,titleText){
	var ctx = $('#'+canvasId);
	var myChart = new Chart(ctx, {
	    type: graphType,
	    data: {
	        labels: label,
	        datasets: [{
	            data: graphData,
	            backgroundColor: colors,
	            borderColor:"green",
	            fill:false,
	            borderWidth: 1
	        }]
	    },
	    options: {
	    	title: {
	            display: true,
	            text: titleText
	        }
	    }
	});
}
$(document).ready(function(){
	fillCanvas("canvasPapers","line",["Accuracy","Unaccurate","Accuracy","Unaccurate"],[15,23,10,7],["green","red","green","red"],"Accuracy");
});
</script>
</head>
<body>
	<jsp:include page="../menuBar.jsp" />
	<div class="card-deck">
			<div class="card text-white m-1">
				<div class="card-header"><h5 class="card-title">Total</h5></div>
				<div class="card-body">
					<h5 class="card-title">26</h5>
					<p class="card-text">C Programming</p>
				</div>
			</div>
			<div class="card text-white m-1">
				<div class="card-header">Top Score</div>
				<div class="card-body">
					<h5 class="card-title">26</h5>
					<p class="card-text">C Programming</p>
				</div>
			</div>
			<div class="card text-white m-1">
				<div class="card-header">Top Score</div>
				<div class="card-body">
					<h5 class="card-title">26</h5>
					<p class="card-text">C Programming</p>
				</div>
			</div>
			<div class="card text-white m-1">
				<div class="card-header">Top Score</div>
				<div class="card-body">
					<h5 class="card-title">26</h5>
					<p class="card-text">C Programming</p>
				</div>
			</div>
		
	</div>
	<div class="row m-1">
		<div class="col-6">
			<div class="card">
				<canvas class="w-100 h-100" id="canvasPapers"></canvas>
			</div>
		</div>
		<div class="col-6"></div>
		<div class="col-6"></div>
		<div class="col-6"></div>
	</div>
	<jsp:include page="../footerLink.jsp" />
</body>
</html>