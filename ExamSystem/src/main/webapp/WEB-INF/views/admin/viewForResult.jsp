<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" lang="en">
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
		
	
var pass=${pass};
var failed=${failed};
fillCanvas("passfail",["pass","fail"],[pass,failed],["green","red"],"Result");
fillCanvas("avg",["pass","fail"],[40,60],["green","red"],"Avrage");
	
	});
</script>
</head>
<body>
	<div class="m-1">
	<div class="table-responsive">      
		<jsp:include page="../menuBar.jsp" />
		
		<div class="row">
		<div class="col">
		  <canvas class="w-100 h-90" id="passfail"></canvas>
		  </div>
		  <%-- <div class="col-4">
		  <canvas class="w-100 h-90" id="avg"></canvas>
		  </div> --%>
		 <div class="col">
	
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
			<ul class="list-group list-group-flush">
			 <li class="list-group-item">
			    ${rank} PRN:-${studPaper.student.prn}  Name:-${studPaper.student.firstName} ${studPaper.student.lastName}
			    	</li>
			    	</ul>
			
		</c:forEach>
	


  </div>
		 </div>
		 
		
		 </div>
		 
		   <div class="row my-3">
		   <div class="col">
		    <input class="form-control  w-50" id="myInput" type="text" placeholder="Search..">
		   </div>
		    <div class="col">
		    <a href="${pageContext.request.contextPath}/Admin/downloadExcelResult/${paperId}" style="float:right;"><i class="far fa-file-excel fa-2x mr-3"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		 
	 <%--  <a href="${pageContext.request.contextPath}/Admin/downloadPdfResult/${paperId}"  style="float:right"><i class="far fa-file-pdf fa-2x ml-3"></i></a> --%>
		   </div>
		</div>
  <br>
  
  
  
    <table class="table table-bordered">
  <thead>
  <tr>
  <th>PRN</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Marks</th>
    <th>Result</th>
    <th>Attendence</th>
  </tr>
  </thead>
  <tbody id="myTable">
  
  <c:forEach items="${studentResultList}" var="student" >
 
  
  <tr>
  <td>${student.student.prn} </td>
    <td>${student.student.firstName}</td>
    <td>${student.student.lastName}</td>
    <td>${student.marks}</td>
     <td>${student.result}</td>
       <td><c:if test="${student.present}" >Present</c:if><c:if test="${!student.present}" >Absent</c:if></td>
  </tr>
    </c:forEach>
</tbody>
</table>
  
  

  
  
</div>



 
 
 
						
						
						
										
						
						
						
						
<jsp:include page="../footerLink.jsp" />
</body>
</html>