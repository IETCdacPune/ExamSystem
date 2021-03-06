<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Exam System</title>
<jsp:include page="../headerLink.jsp" />
</head>
<body >
	<div class="m-1">
		<%-- <jsp:include page="../menuBar.jsp" /> --%>
		<br>
		<div class="row justify-content-center">
			<c:if test="${empty msg}"><div class="col">
				<div class="card">
					<div class="card-header bg-primary">
						<h3>Paper Instruction</h3>
					</div>
					<div class="card-body">
						<ol class="list-group list-group-flush">
						  <li class="list-group-item">Your name and module name is visible at top left during exam.</li>
						  <li class="list-group-item">Timer is visible at right top after module name.</li>
						  <li class="list-group-item">Number of questions attempted, marked for review, not attempted shown just below the PRN.</li>
						  <li class="list-group-item">In the right side you will get question and just below that you will get the options you can choose any one option from that.</li>
						  <li class="list-group-item">To submit the answer you have two buttons <button class="btn btn-warning" type="submit">Marked
									for Review And Next</button> and <button class="btn btn-primary" type="submit">Save & Next</button> you can click on either of the button.</li>
						<li class="list-group-item">If you want to go to previous question then click on <button class="btn btn-light" type="submit">Previous Question</button> button.</li>
						<li class="list-group-item">You can jump to any question by the clicking on any question number at left.</li>
						  <li class="list-group-item">On the left hand side you will get question numbers with different color scheme. They are as follow
						  	<ul class="list-group list-group-flush">
						  		<li class="list-group-item"><a href="#" class="btn btn-secondary" style="border-radius: 30%;">01</a> Unvisited question.</li>
						  		<li class="list-group-item"><a href="#" class="btn btn-danger" style="border-radius: 30%;">01</a> Visited question but not answered.</li>
						  		<li class="list-group-item"><a href="#" class="btn btn-success" style="border-radius: 30%;">01</a> visited and answered .</li>
						  		<li class="list-group-item"><a href="#" class="btn btn-warning text-danger" style="border-radius: 30%;">01</a> visited and marked for review.</li>
						  		<li class="list-group-item"><a href="#" class="btn btn-warning text-success" style="border-radius: 30%;">01</a> visited and answered the question as well as marked it for review.</li>
						  	</ul>
						  </li>
						</ol>
						<div class="row">
							<div class="col-3"></div>
							<div class="col-6 m-1 justify-content-center">
						<form:form method="post" autocomplete="off">
							<input type="hidden" name="paperId" value="${paperId}"> <input
								type="hidden" name="paperCode" value="${paperCode}">
								<input type="text" class="form-control" name="code"
								placeholder="Enter paper code" />
								<br />
							<button type="submit" class="btn btn-primary ">Start Exam</button>
							<button type="reset" class="btn btn-danger ">Reset form</button>
						</form:form>
						</div>
						</div>
					</div>
				</div>
			</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="jumbotron justify-content-center">
					<h1>${msg}</h1>
					<a class="btn btn-outline-secondary" href="/">Your best yet to come.</a>
				</div>
			</c:if>
		</div>
	</div>
	<script type="text/javascript">   
    function requestFullScreen(el) {
        // Supports most browsers and their versions.
        var requestMethod = el.requestFullScreen || el.webkitRequestFullScreen || el.mozRequestFullScreen || el.msRequestFullScreen;

        if (requestMethod) { // Native full screen.
            requestMethod.call(el);
        } else if (typeof window.ActiveXObject !== "undefined") { // Older IE.
            var wscript = new ActiveXObject("WScript.Shell");
            if (wscript !== null) {
                wscript.SendKeys("{F11}");
            }
        }
        return false;
    } 
   // $(document).ready(requestFullScreen(document.body));
	</script>
</body>
</html>