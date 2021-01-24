<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>

<head>
	
	<title>Регистрация</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="https://kit.fontawesome.com/7b83e77052.js" crossorigin="anonymous"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/error.css"/>

</head>

<body>
<%@include file="nav.jsp"%>
	<div>

		<div id="signupbox"
			 class="container col-md-4 col-md-offset-3 pt-5">

			<div class="card">

				<div class="card-header d-flex align-items-center justify-content-center" style="background: black">
					<div class="card-title font-weight-bold" style="color: white">Регистрация</div>
				</div>

				<div  class="card-body">

					<form:form action="${pageContext.request.contextPath}/registration/processRegistrationForm"
							   method="post" modelAttribute="user" class="form-horizontal">

						<div class="col-12">
							<div>

								<c:if test="${registrationError != null}">
									<div class="error">
											${registrationError}
									</div>
								</c:if>

							</div>
						</div>

						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<form:input path="username" placeholder="Имя пользователя*" class="form-control" />
						</div>
						<div class="mb-1">
							<form:errors path="username" cssClass="error" />
						</div>


						<div class="input-group mt-4">
							<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
								<form:password path="password" placeholder="Пароль*" class="form-control" />
						</div>

						<div class="mb-1">
							<form:errors path="password" cssClass="error" />
						</div>

						<div class="input-group mt-4">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
								<form:password path="matchingPassword" placeholder="Подтвердите пароль*" class="form-control" />
						</div>

						<div class="mb-1">
							<form:errors path="password" cssClass="error" />
						</div>

						<div class="form-group mt-4">
							<div>
								<button type="submit" class="btn btn-info">Регистрация</button>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-12 control">
								<div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
									Уже есть аккаунт?
									<a href="${pageContext.request.contextPath}/showLoginPage">
										Войти
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