<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Создать маршрут</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/create-route.js"></script>
    <sec:csrfMetaTags/>
</head>

<body>
<%@ include file="nav.jsp" %>

<div class="col-4 mr-5 offset-4 mt-5">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title text-center">Создать ${route.routeNumber.number} маршрут</h5>
            <p class="card-text">
                <input id="routeNumber" type="hidden" value="${route.routeNumber.number}"/>
                <input id="routeFromStation" class="form-control mb-2" placeholder="От" type="text">
                <input id="routeToStation" class="form-control mb-2" placeholder="До" type="text">
                <input id="travelTime" class="form-control mb-2" placeholder="Время в пути 00:00" type="text">
            </p>
            <input id="myButtonRoute" class="btn btn-info rounded-0" type="button" value="Добавить">
            <span class="font-weight-light text-info"><small id="successTextRoute"></small></span>
        </div>
    </div>
</div>
</body>
</html>
