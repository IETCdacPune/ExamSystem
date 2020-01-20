<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="ISO-8859-1">
<title>Exam System</title>





<script type="text/javascript">
$(document).ready(function() {
	 alert("kkk");
   /* $('#en').click(function() {
       alert(",,,,,,,");
        this.value = 'Changed';
    }); */
});
</script>
<jsp:include page="../headerLink.jsp" />
</head>
<body>
	<div class="container">
	
		<jsp:include page="../menuBar.jsp" />
		<br> <br>
		<c:if test="${not empty errmsg}">
			<div class="alert alert-danger alert-dismissible">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Error!</strong> ${errmsg }
			</div>
		</c:if>
		
		<c:if test="${not empty list}">
			<c:forEach items="${list}" var="question" varStatus="loop">
				<div class="col-12 mt-1">
					<div class="card border-info pl-3">
						<div class="card-head">
							<a class="close text-rigth" href="/Admin/questionEdit/${question.queId}"><em class="far fa-edit"></em></a>
							<pre>${loop.index +1 }) ${question.fullQuestion}</pre>
						</div>
						<div class="card-body">
							<c:forEach items="${question.optionList}" var="option"
								varStatus="loop">
								<c:if test="${option.opt == question.correctOption}">
									<div class="alert alert-success">${option.opt}
										${option.answer}</div>
								</c:if>
								<c:if test="${option.opt != question.correctOption}">
									<div class="alert alert-secondary">${option.opt}
										${option.answer}</div>
								</c:if>
							</c:forEach>
							<button class="btn btn-light" data-toggle="collapse"
								data-target="#demo${loop.index +1}">Description</button>

							<div id="demo${loop.index +1}" class="collapse">
								${question.description}</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:if>

		<div class="row">
			<div class="col-3"></div>
			<div class="col-6">
				<c:if test="${!enableStatus}">
				<a href="/Admin/Paper/enable/${paperId}" class="btn btn-block  btn-success">Enable</a>
				</c:if>
				<c:if test="${enableStatus}">
				<a href="/Admin/Paper/disable/${paperId}" class="btn btn-block btn-danger">Disable</a>
				</c:if>
			</div>
		</div>

		</div>
	
	<jsp:include page="../footerLink.jsp" />
</body>
</html>