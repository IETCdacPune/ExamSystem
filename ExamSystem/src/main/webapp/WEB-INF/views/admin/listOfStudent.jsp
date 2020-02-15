<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<script>
		$(document)
				.ready(
						function() {
							$("#myInput")
									.on(
											"keyup",
											function() {
												var value = $(this).val()
														.toLowerCase();
												$("#myTable tr")
														.filter(
																function() {
																	$(this)
																			.toggle(
																					$(
																							this)
																							.text()
																							.toLowerCase()
																							.indexOf(
																									value) > -1)
																});
											});
						});
	</script>

<meta charset="ISO-8859-1">
<title>Exam System</title>
<jsp:include page="../headerLink.jsp" />
<style>
</style>
</head>
<body>
	<div class="m-1">
		<div class="table-responsive">
			<jsp:include page="../menuBar.jsp" />
			
			<c:forEach var="c" items="2">
			<div class="row mx-1">
			
			<div class="col">
				<div class="card text-white bg-primary m-1"> <!-- style="max-width: 18rem;" -->
				  <div class="card-header">Number Of Students</div>
				  <div class="card-body">
				    <h6 class="card-title">Under DAC:- ${studentDAC} </h6>
				    <h6 class="card-title">Under PreDAC:- ${studentPredac} </h6>
				  </div>
				</div>
			</div>
			</div>
		
			</c:forEach>
			
			
			
			<input class="form-control" id="myInput" type="text"
				placeholder="Search.."> <br>

			<table class="table table-bordered">
				<thead>
					<tr>
						<th>PRN</th>
						<th>Firstname</th>
						<th>Lastname</th>
						<th>Email</th>
						<th>Course</th>
					</tr>
				</thead>
				<tbody id="myTable">

					<c:forEach items="${studentAllList}" var="student">


						<tr>
							<td>${student.prn}</td>
							<td>${student.lastName}</td>
							<td>${student.firstName}</td>
							<td>${student.emailId}</td>
							<td>${student.course.name}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>


	<jsp:include page="../footerLink.jsp" />
</body>
</html>