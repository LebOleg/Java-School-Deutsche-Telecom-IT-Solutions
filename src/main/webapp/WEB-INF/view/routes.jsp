<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список маршрутов</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
<%@ include file="nav.jsp" %>

<div class="col-4 mr-5 offset-4 mt-5 mb-5">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title text-center">Посмотреть маршрут</h5>
            <p class="card-text">

                <select class="form-control form-control-sm mb-2" id="routeNumber">
                    <option value="" selected>Выберите маршрут</option>
                    <c:forEach items="${routes}" var="route">
                        <option value="${route.number}">${route.number}</option>
                    </c:forEach>
                </select>
            </p>

            <a onclick="redirectTo()" class="btn btn-info rounded-0" type="button">Поиск</a>
        </div>

    </div>
</div>

<c:if test="${not empty routesStation}">
<div class="container mt-3 col-6">
    <table class="table">
        <thead style="background-color: black">
        <tr style="color: white">
            <th scope="col">Маршрут ${routesStation[0].routeNumber.number}</th>
        </tr>
        </thead>
        <tbody style="background-color: white">
        <c:forEach items="${routesStation}" var="route">

            <tr>
                <th scope="row">${route.station.name}</th>
            </tr>
        </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>

<script>
    $(document).ready(function () {
        <%
        session.removeAttribute("routesStation");
        %>
    });
</script>

<script>
    function redirectTo() {
        let routeNumber = $('#routeNumber').val();
        window.location.href = myContextPath + "/employee/route/getRoutes/" + routeNumber;
    }
</script>

</body>
</html>
