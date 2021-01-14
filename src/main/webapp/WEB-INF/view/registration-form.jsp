<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!doctype html>
<html lang="en">

<head>
	
	<title>Register New User Form</title>
	
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="resources/css/error.css" />

</head>

<body>

	<div>

		<div id="signupbox" style = "margin-top: 50px"
			 class="mainbox col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3 col-lg-4 col-lg-offset-4">

			<div class="panel panel-info">

				<div class="panel-heading">
					<div class="panel-title">Register New User</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<!-- Registration Form -->
					<form:form action="${pageContext.request.contextPath}/processRegistrationForm"
							   method="post" modelAttribute="user" class="form-horizontal">

						<div class="col-xs-15">
							<div>

								<c:if test="${registrationError != null}">
									<div class="error" style="margin-bottom: 10px">
											${registrationError}
									</div>
								</c:if>

							</div>
						</div>

						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
							<form:input path="username" placeholder="username (*)" class="form-control" />
						</div>
						<div style="margin-bottom: 25px">
							<form:errors path="username" cssClass="error" />
						</div>

						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
							<form:password path="password" placeholder="password (*)" class="form-control" />
						</div>
						<div style="margin-bottom: 25px">
							<form:errors path="password" cssClass="error" />
						</div>

						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
							<form:password path="matchingPassword" placeholder="confirm password (*)" class="form-control" />
						</div>

						<div style="margin-bottom: 25px">
							<form:errors path="password" cssClass="error" />
						</div>

						<div style="margin-top: 10px" class="form-group">
							<div class="col-sm-6 controls">
								<button type="submit" class="btn btn-success">Register</button>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-12 control">
								<div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
									Already have an account?
									<a href="${pageContext.request.contextPath}/showLoginPage">
										Sign In
									</a>
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>