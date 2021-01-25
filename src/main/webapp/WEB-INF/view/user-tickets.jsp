<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Мои билеты</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
<%@ include file="nav.jsp" %>

<c:if test="${tickets != null}">
<c:choose>
<c:when test="${not empty tickets}">

<div id="timetable" class="container mt-5">
    <div class="col-md-8 offset-md-2">
        <ul class="list-group">
            <c:forEach items="${tickets}" var="ticket" varStatus="status">

            <li class="list-group-item list-group-item-action mt-2">
                <div>
                    <div class="text-center">
                        Маршрут ${ticket.train.routeNumber.number}
                    </div>
                    <p></p>

                    <div class="row d-flex justify-content-center">
                        <div>
                            <div class="text-center">
                                <strong>Отправление:</strong>
                            </div>
                            <div>  ${ticket.departureTime.toLocalDate()} &nbsp; ${ticket.departureTime.toLocalTime()}
                            </div>
                            <div class="row d-flex justify-content-center font-weight-bold">${ticket.sourceStation.name}
                            </div>

                        </div>

                        <div class="col-6">
                            <hr>
                        </div>

                        <div>

                            <div class="text-center">
                                <strong>Прибытие:</strong>
                            </div>
                            <div> ${ticket.arrivalTime.toLocalDate()} &nbsp;${ticket.arrivalTime.toLocalTime()}

                            </div>

                            <div class="row d-flex justify-content-center font-weight-bold"> ${ticket.destinationStation.name}

                            </div>
                        </div>
                    </div>
                    <p></p>
                </div>
            </li>

            </c:forEach>

            </c:when>
            <c:otherwise>

            <div class="row d-flex justify-content-center" style="color: white">
                Билетов нет
            </div>

            </c:otherwise>
            </c:choose>
            </c:if>
</body>
</html>
