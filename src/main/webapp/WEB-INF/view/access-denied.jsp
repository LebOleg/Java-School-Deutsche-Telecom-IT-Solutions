<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Доступ запрещен</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
<%@ include file="nav.jsp"%>


<div class="container d-flex h-75 justify-content-center">
<div class="card text-center mt-4  align-self-center">
    <div class="card-header" style="background-color: black;">
    </div>
    <div class="card-body">
        У вас недостаточно прав для доступа к данному ресурсу
        <p></p>
        <a href="${pageContext.request.contextPath}/">Главная страница</a>
    </div>
    <div class="card-footer" style="background-color: black">
    </div>
</div>
</div>
</body>
</html>
