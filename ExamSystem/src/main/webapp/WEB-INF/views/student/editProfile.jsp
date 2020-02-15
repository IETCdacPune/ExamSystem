<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Exam System</title>
<jsp:include page="../headerLink.jsp" />
</head>
<body>
	<div class="m-1">
		<jsp:include page="../menuBar.jsp" />
		<c:if test="${not empty errmsg}">
			<div class="alert alert-danger alert-dismissible">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Error!</strong> ${errmsg}
			</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="alert alert-success alert-dismissible">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Success!</strong> ${msg}
			</div>
		</c:if>
		<div class="row">
		<div class="col-3">
			<button class="close text-rigth editImg" data-toggle="modal" data-target="#uploadImg">
			<img alt="${user.prn}" src="/Common/avatar/${command.imgUrl}" class="rounded mx-auto d-block w-100 h-100">
			</button>
			
			</div>
		<div class="col-9">
			<form:form method="post">
			<div class="form-row m-1">
				<div class="col-3">PRN</div>
				<div class="col-9">
					<form:input path="prn" class="form-control" placeholder="P.R.N." />
				</div>
			</div>
			<div class="form-row m-1">
				<div class="col-3">Name</div>
				<div class="col-9 form-row">
					<div class="col">
				      <form:input path="firstName" class="form-control" placeholder="First name" />
				    </div>
				    <div class="col">
				      <form:input path="lastName" class="form-control" placeholder="Last name" />
				    </div>
				</div>
			</div>
			<div class="form-row m-1">
				<div class="col-3">Email</div>
				<div class="col-9">
					<input name="emailId" value="${command.emailId}" readonly="readonly" class="form-control-plaintext" />
				</div>
			</div>
			<div class="row m-1">
				<div class="offset-3 col-9">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</div>
			</form:form>
		</div>
		</div>
		<jsp:include page="../footerLink.jsp" />
	</div>
	<!-- Modal -->
<form:form action="/Student/uploadImg" enctype="multipart/form-data">
<div class="modal fade" id="uploadImg" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Upload your profile photo.</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <input type="file" name="photo" />
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">upload</button>
      </div>
    </div>
  </div>
</div>
</form:form>
</body>
</html>