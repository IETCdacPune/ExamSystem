<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-lg navbar-light bg-info">
			<a class="navbar-brand" href="#">IET</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
<sec:authorize access="!isAuthenticated()">
  <ul class="navbar-nav mr-auto">
  	<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/">Home</a></li>
  	<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/signup">Registration</a></li>
  </ul>
  <ul class="navbar-nav ml-auto">
  	<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/signin">Login</a></li>
  </ul>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
  <sec:authorize access="hasRole('ADMIN')">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/Admin">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/Admin/listOfStudent">Students</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Exam </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="${pageContext.request.contextPath}/Admin/addPaper">Add Paper</a> <a
								class="dropdown-item" href="${pageContext.request.contextPath}/">Show All Paper</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="#">Something else here</a>
						</div></li>
						<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Subject </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="${pageContext.request.contextPath}/Admin/addSubject">Add Subject</a> <a
								class="dropdown-item" href="${pageContext.request.contextPath}/Admin/listOfSubject">Show All Subject</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="${pageContext.request.contextPath}/Admin" onclick="return confirm('Are you sure?')">Remove Subject</a>
						</div></li>
				</ul>
				</sec:authorize>
				<sec:authorize access="hasRole('STUDENT')">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/STUDENT">Home</a></li>
				</ul>
				</sec:authorize>
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/signout">Logout</a></li>
				</ul>

</sec:authorize>
</div>
		</nav>