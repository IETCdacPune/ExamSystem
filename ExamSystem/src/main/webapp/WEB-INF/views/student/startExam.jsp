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
			<div class="col-sm-6">
				<div class="card text-center">
					<div class="card-header bg-primary">
						<h3>Paper Code</h3>
					</div>
					<div class="card-body">
						<form method="post">
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