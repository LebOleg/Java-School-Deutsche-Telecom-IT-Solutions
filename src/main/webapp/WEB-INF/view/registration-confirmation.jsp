<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Успешная регистрация</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
<%@ include file="nav.jsp" %>

<div class="container col-6 mt-5">
    <div class="card text-center mt-4 ">
        <div class="card-header" style="background-color: black;">
        </div>
        <div class="card-body">
            Вы успешно зарегистрировались
            <p></p>
            <a href="${pageContext.request.contextPath}/showLoginPage">Войти</a>
        </div>
        <div class="card-footer" style="background-color: black">
        </div>
    </div>
</div>
</body>
</html>