<%--
  Created by IntelliJ IDEA.
  User: lebed
  Date: 26.01.2021
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ошибка</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
<%@ include file="nav.jsp" %>

<div class="card w-100 h-100">
    <div class="card-body">
        <h5 class="card-title"></h5>
        <p class="card-text text-center"><span style="font-size: 135pt">5</span><i class="far fa-frown fa-10x"></i><span style="font-size: 135pt">O</span></p>
        <p class="text-center">На сервере произошла непредвиденная ошибка. Пожалуйста, попробуйте позже</p>
    </div>
</div>
</body>
</html>
