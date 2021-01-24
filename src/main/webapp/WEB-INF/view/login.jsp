<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>

<head>

    <title>Login Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300&display=swap" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/error.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/nav.css" />

    <script src="https://kit.fontawesome.com/7b83e77052.js" crossorigin="anonymous"></script>
</head>

<body>
<div>
    <%@ include file="nav.jsp"%>
    <div id="loginbox"
         class="container col-md-4 col-md-offset-3 pt-5">

        <div class="card">
            <div class="card-header d-flex align-items-center justify-content-center h-25" style="background-color: black">
                <div class="card-title font-weight-bold" style="color: white">Вход</div>
            </div>

            <div  class="card-body">
                <form:form action="${pageContext.request.contextPath}/authenticateTheUser"
                           method="POST" class="form-horizontal">

                        <div>
                            <div>
                                <c:if test="${param.error != null}">
                                    <div class="error" style="margin-bottom: 10px" >
                                        Неверное имя пользователя или пароль
                                    </div>
                                </c:if>
                            </div>
                    </div>

                    <div class="input-group mb-4">
                        <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                        <input type="text" name="username" placeholder="Имя пользователя" class="form-control">

                        </div>

                    <div class="input-group mb-4">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-key"></i></span>
                        </div>
                        <input type="password" name="password" placeholder="Пароль" class="form-control" >
                    </div>

                    <div class="form-group">
                        <div>
                            <button type="submit" class="btn btn-info">Войти</button>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-12 control">
                            <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
                                Нет учестной записи?
                                <a href="${pageContext.request.contextPath}/registration/showRegistrationForm">
                                    Регистрация
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