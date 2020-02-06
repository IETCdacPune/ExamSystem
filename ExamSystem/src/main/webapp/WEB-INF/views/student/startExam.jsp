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
	<div class="container">
		<%-- <jsp:include page="../menuBar.jsp" /> --%>
		<br>
		<div class="row justify-content-md-center">
			<c:if test="${empty msg}"><div class="col-sm-6">
				<div class="card text-center">
					<div class="card-header bg-primary">
						<h3>Paper Code</h3>
					</div>
					<div class="card-body">
					<div class="jumbotron py-3">
					<h5 class="my-3">1. Check your PRN and Module Name.</h5>
					<h5>2. If you want  to submit your paper then click submit button.</h5>
					<h5>3.If you answered any question button color is green. </h5>
					<h5>4.If you mark for review any question button color is orange. </h5>
					<h5>5.If you click on save and next button color is grey. </h5>
					</div>
						<form method="post" autocomplete="off">
							<input type="hidden" name="paperId" value="${paperId}"> <input
								type="hidden" name="paperCode" value="${paperCode}"> <input
								type="text" class="form-control" name="code"
								placeholder="Enter paper code" /> <br>
							<button type="submit" class="btn btn-primary">Start Exam</button>
							<button type="reset" class="btn btn-danger">Reset form</button>
						</form>
					</div>
				</div>
			</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="jumbotron justify-content-center">
					<h1>${msg}</h1>
					<a class="btn btn-outline-secondary" href="/">Get Out</a>
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