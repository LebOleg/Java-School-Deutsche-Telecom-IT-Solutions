<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить поезд</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/create-train.js"></script>
    <sec:csrfMetaTags/>
</head>

<body>
<%@ include file="nav.jsp" %>

<div class="col-4 mr-5 offset-4 mt-5">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title text-center">Добавить поезд</h5>
            <p class="card-text">
                <input id="availableSeats" class="form-control mb-2" placeholder="Количество мест" type="text">

                <select class="form-control form-control-sm mb-2" id="routeNumber">
                    <option value="" selected>Выберите маршрут</option>
                    <c:forEach items="${routes}" var="route">
                        <option value="${route.number}">${route.number}</option>
                    </c:forEach>
                </select>

                <input type="date" class="form-control mb-2" id="timetableDate">

                <input type="time" class="form-control mb-2" id="timetableTime">

            </p>

            <input id="myButtonTrain" class="btn btn-info rounded-0" type="button" value="Добавить" style="width: 24mm">
            <span id="createSuccessConnection" class="font-weight-light text-info"><small
                    id="successTextConnection"></small></span>
        </div>
    </div>
</div>
</body>
</html>
