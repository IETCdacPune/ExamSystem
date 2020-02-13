<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<nav class="navbar navbar-expand-md navbar-light bg-info">
	<a class="navbar-brand" href="/">IET</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<sec:authorize access="!isAuthenticated()">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/"><i class="fas fa-home"></i>Home</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/signup">Registration</a></li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/signin">Login</a></li>
			</ul>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<sec:authorize access="hasRole('ADMIN')">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/Admin/"><i class="fas fa-home"></i> Home</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Student </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item"

							href="${pageContext.request.contextPath}/Admin/listOfStudent">show all DAC student</a>
							<a class="dropdown-item"
							href="${pageContext.request.contextPath}/Admin/listOfPredacStudent">show all PERDAC student</a>

								href="${pageContext.request.contextPath}/Admin/listOfStudent">show
								all student</a>

						</div></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Exam </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item"
								href="${pageContext.request.contextPath}/Admin/addPaper">Add
								Paper</a> <a class="dropdown-item"
								href="${pageContext.request.contextPath}/Admin/allPapers">Show
								All Paper</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item"
								href="${pageContext.request.contextPath}/Admin/genratedResult">Result</a>
						</div></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Subject </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item"
								href="${pageContext.request.contextPath}/Admin/addSubject">Add
								Subject</a> <a class="dropdown-item"
								href="${pageContext.request.contextPath}/Admin/listOfSubject">Show
								All Subject</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item"
								href="${pageContext.request.contextPath}/Admin/removeSubject"
								onclick="return confirm('Are you sure?')">Remove Subject</a>
						</div></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Course </a>
						<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
							<a class="dropdown-item"
								href="${pageContext.request.contextPath}/Admin/addCourse">Add
								Course</a> <a class="dropdown-item"
								href="${pageContext.request.contextPath}/Admin/listOfCourses">Show
								All Course</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item"
								href="${pageContext.request.contextPath}/Admin"
								onclick="return confirm('Are you sure?')">Remove Course</a>
						</div></li>
				</ul>
			</sec:authorize>


			<sec:authorize access="hasRole('STUDENT')">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/Student/"><i class="fas fa-home"></i> Home</a></li>

					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/Student/result">Result</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/Student/newPapers">Exam</a></li>
				</ul>
			</sec:authorize>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"><i class="fas fa-users-cog"></i></a>
					<div class="dropdown-menu dropdown-menu-right"
						aria-labelledby="navbarDropdown">
						<a class="dropdown-item"
							href="${pageContext.request.contextPath}/#">Change Password</a>
						<sec:authorize access="hasRole('STUDENT')">
							<a class="dropdown-item"
								href="${pageContext.request.contextPath}/Common/profile"><i
								class="far fa-id-card"></i> Profile</a>
						</sec:authorize>
						<div class="dropdown-divider"></div>
						<a class="nav-link"
							href="${pageContext.request.contextPath}/signout"><i
							class="fas fa-sign-out-alt"></i> Logout</a>
					</div></li>
			</ul>

		</sec:authorize>
	</div>
</nav>
<br>
val1:-${myMap[isRegistrationAvailable]}
<br>
val2:-${applicationScope.demo}
<br>