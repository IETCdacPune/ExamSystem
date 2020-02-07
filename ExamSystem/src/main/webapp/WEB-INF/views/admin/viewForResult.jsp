<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Exam System</title>
<jsp:include page="../headerLink.jsp" />
<script type="text/javascript" src="/Chart.min.js"></script>
<script>
$(document).ready(function(){
	  $("#myInput").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#myTable tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });
	});
</script>
<script type="text/javascript">

fillCanvas=function(canvas,label,graphData,colors,titleText){
var ctx=$('#'+canvas);
var myChart=new Chart(ctx,{
type:'doughnut',
data:{

labels:label,
datasets:[{

	data:graphData,
	backgroundColor:colors,
	borderWidth:1
}]
	
},


options:{
title:{
display:true,
text:titleText
	},
legend:{
	display:false
	}
}

	
});
}



$(document).ready(function(){
		
	

fillCanvas("passfail",["pass","fail"],[50,60],["green","red"],"Result");
fillCanvas("avg",["pass","fail"],[40,60],["green","red"],"Avrage");
	
	});
</script>
</head>
<body>
	<div class="container">
	<div class="table-responsive">      
		<jsp:include page="../menuBar.jsp" />
		
		<div class="row">
		<div class="col-4">
		  <canvas class="w-100 h-90" id="passfail"></canvas>
		  </div>
		  <div class="col-4">
		  <canvas class="w-100 h-90" id="avg"></canvas>
		  </div>
		 <div class="col-4">
	
		 Top Three Student
	<ul class="list-group list-group-flush">
	<li class="list-group-item">2sd</li>
	<li class="list-group-item">3rd</li>
	<li class="list-group-item">4th</li>
	<li class="list-group-item">5th</li>

	</ul>
  </div>
		 </div>
		  </div>
		  
		 <input class="form-control" id="myInput" type="text" placeholder="Search..">
  <br>
  
  
  
    <table class="table table-bordered">
  <thead>
  <tr>
  <th>PRN</th>
    <th>Firstname</th>
    <th>Lastname</th>
    <th>Marks</th>
    <th>Result</th>
    <th>Attendence</th>
  </tr>
  </thead>
  <tbody id="myTable">
  
  <c:forEach items="${studentResultList}" var="student" >
 
  
  <tr>
  <td>${student.student.prn} </td>
    <td>${student.student.lastName}</td>
    <td>${student.student.firstName}</td>
    <td>${student.marks}</td>
     <td>${student.result}</td>
  </tr>
    </c:forEach>
</tbody>
</table>
  
  
 <%--  <canvas class="w-100 h-100" id="result"></canvas> --%>
  
  
</div>



 
 
 
						
						
						
										
						
						<button type="button" class="btn btn-primary">download Generate Result</button>
						
						
<jsp:include page="../footerLink.jsp" />
</body>
</html>