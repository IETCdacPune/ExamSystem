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
$(document).ready(function(){
	
	fillCanvas("canvasResult1","bar",["Correct","Wrong","Unattempted"],[10,20,15],["green","red","orange"],"Result");
	fillCanvas("canvasResult2","radar",["Correct","Wrong","Unattempted"],[10,20,15],["green","red","orange"],"Result");
	fillCanvas("canvasResult3","pie",["Correct","Wrong","Unattempted"],[10,20,15],["green","red","orange"],"Result");
	fillCanvas("canvasResult4","line",["Correct","Wrong","Unattempted"],[10,20,15],["green","red","orange"],"Result");
	
});
</script>
</head>
<body><div class="m-1">
		<jsp:include page="../menuBar.jsp" />
		<div class="row mx-1">
			<div class="col">
				<div class="card text-white bg-primary m-1"> <!-- style="max-width: 18rem;" -->
				  <div class="card-header">Number Of Students</div>
				  <div class="card-body">
				    <h6 class="card-title">Under DAC:- 120</h6>
				    <h6 class="card-title">Under PreDAC:- 10</h6>
				  </div>
				</div>
			</div>
			<div class="col">
				<div class="card text-white bg-info m-1"> <!-- style="max-width: 18rem;" -->
				  <div class="card-header">Number Of Paper</div>
				  <div class="card-body">
				    <h6 class="card-title">Under DAC:- 20</h6>
				    <h6 class="card-title">Under PreDAC:- 10</h6>
				  </div>
				</div>
			</div>
			<div class="col">
				<div class="card text-white bg-secondary m-1"> <!-- style="max-width: 18rem;" -->
				  <div class="card-header">Number Of Subjects</div>
				  <div class="card-body">
				    <h5 class="card-title">Under DAC:- 15</h5>
				    <h6 class="card-title">Under PreDAC:- 5</h6>
				  </div>
				</div>
			</div>
			<div class="col">
				<div class="card text-white m-1" style="background-color: rgb(195, 155, 211);"> <!-- style="max-width: 18rem;" -->
				  <div class="card-header">Number Of Subjects</div>
				  <div class="card-body">
				    <h6 class="card-title">Under DAC:- 13</h6>
				    <h6 class="card-title">Under PreDAC:- 5</h6>
				  </div>
				</div>
			</div>
		</div>
		<div class="row m-1">
			<div class="col-6">
				<canvas class="w-100 h-100" id="canvasResult1"></canvas>
			</div>
			<div class="col-6">
				<canvas class="w-100 h-100" id="canvasResult2"></canvas>
			</div>
			<div class="col-6">
				<canvas class="w-100 h-100" id="canvasResult3"></canvas>
			</div>
			<div class="col-6">
				<canvas class="w-100 h-100" id="canvasResult4"></canvas>
			</div>
		</div>
	<jsp:include page="../footerLink.jsp" />
</div></body>
</html>