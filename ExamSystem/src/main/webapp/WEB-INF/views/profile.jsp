<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Exam System</title>
<jsp:include page="./headerLink.jsp" />
</head>
<body>
	<div class="m-1">
		<jsp:include page="./menuBar.jsp" />
		<c:if test="${not empty errmsg}">
			<div class="alert alert-danger alert-dismissible">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Error!</strong> ${errmsg}
			</div>
		</c:if>
		<div class="row">
		<div class="col-3">
			<button class="close text-rigth editImg" data-toggle="modal" data-target="#uploadImg">
			<img alt="${user.prn}" src="/Common/avatar/${user.imgUrl}" class="rounded mx-auto d-block w-100 h-100">
			</button>
			
			</div>
		<div class="col-9">
			<h3>PRN:-${user.prn}</h3>
			<h3>Name:-${user.firstName} ${user.lastName}</h3>
			<h4>Email:-${user.emailId}</h4>
		</div>
		</div>
		<jsp:include page="./footerLink.jsp" />
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