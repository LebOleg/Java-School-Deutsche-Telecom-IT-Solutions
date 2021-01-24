<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>search  ticket</title>
    <script>const myContextPath = "${pageContext.request.contextPath}"</script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300&display=swap" rel="stylesheet">

    <script src="http://malsup.github.com/jquery.form.js"></script>
    <script src="https://kit.fontawesome.com/7b83e77052.js" crossorigin="anonymous"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/check-available-seats.js"></script>

    <sec:csrfMetaTags />


</head>
<body>
<%@include file="nav.jsp"%>
<div class="container pt-5">
    <div class="col-md-8 offset-md-2">
        <form:form action="${pageContext.request.contextPath}/processSearchTicket" method="post" class = "form-horizontal" modelAttribute="searchTicketAttr">
        <div class="row">
            <div class="col form-group">
                <form:input type = "text" class = "form-control" placeholder="Cтанция отправления" path="fromStation"/>
            </div>
            <span class="material-icons pt-2" style="color: white">
                east
            </span>
            <div class="col form-group">
                <form:input type="text" class="form-control" placeholder="Станция назначения" path="toStation"/>
            </div>

        </div>

        <div class="row">
            <div class="col form-group">
                <label for="time"><small>&nbsp;</small></label>
                <form:input id="time" type="time" class="form-control" placeholder="00:00" path="fromTime"/>
            </div>

            <div class="col form-group">
                <label for="timeTo"><small>&nbsp;</small></label>
                <form:input id="timeTo" type="time" class="form-control" placeholder="00:00" path="toTime"/>
            </div>

            <div class="col form-group">
                <label for="date"><small>&nbsp;</small></label>
                <form:input id="date" type="date" name="date" class="form-control " placeholder="dd.mm.yyyy" path="date"/>


            </div>
            <div class="col pt-4">
                <input id="button" type="submit" name="submit" value="Поиск" class="btn btn-info rounded-0" style="width: 100%; height: 71%";>
            </div>
        </div>
    </div>

    </form:form>
</div>

<c:if test="${tickets != null}">
<c:choose>
<c:when test="${not empty tickets}">

<div id="timetable" class="container">
    <div class="col-md-8 offset-md-2">
        <h6 class="text-center font-weight-light">Бил</h6>
        <ul class="list-group">
            <c:forEach items="${tickets}" var="ticket" varStatus="status">
            <form:form action="${pageContext.request.contextPath}/fillPassenger" method="post" modelAttribute="ticketDTO" class="MyForm">
            <li class="list-group-item list-group-item-action">
                <div>
                    <div class="text-center">
                        Маршрут ${ticket.routeNumber}
                        <form:input class = "trains" path="train" type="hidden" value="${ticket.train}"/>
                        <form:input path="routeNumber" type="hidden" value="${ticket.routeNumber}"/>


                    </div>

                    <p></p>

                    <div class="row d-flex justify-content-center">
                        <div>

                            <div>${ticket.departureDateTime.toLocalTime()}
                                <form:input path="departureTime" type="hidden" value="${ticket.departureDateTime.toLocalTime()}"/>
                            </div>
                            <div  class="row d-flex justify-content-center font-weight-bold">${ticket.sourceStation}
                                <form:input path="sourceStation" type="hidden" value="${ticket.sourceStation}"/>
                            </div>

                        </div>

                        <div class="col-10">
                            <hr>
                        </div>

                        <div>
                            <div>${ticket.arrivalDateTime.toLocalTime()}
                                <form:input path="arrivalTime" type="hidden" value="${ticket.arrivalDateTime.toLocalTime()}"/>
                                <form:input path="dateTicket" type="hidden" value="${ticket.arrivalDateTime.toLocalDate()}"/>
                            </div>
                            <div  class="row d-flex justify-content-center font-weight-bold"> ${ticket.destinationStation}
                                <form:input path="destinationStation" type="hidden" value="${ticket.destinationStation}"/>
                            </div>
                        </div>
                    </div>
                    <p></p>
                    <span class="badge badge-info badge-pill mt-3 seats">Свободно ${ticket.availableSeats} мест </span>
                    <form:input class="myInput" path="availableSeats" type="hidden" value="${ticket.availableSeats}"/>

                    <input class="btn btn-info btn-sm rounded-0 myButton"  type="button" name="buy" value="Купить"   style="width: 20%; float: right">
                </div>
            </li>
            </form:form>
            </c:forEach>

            </c:when>
            <c:otherwise>

            <div  class="row d-flex justify-content-center" style="color: white">
                Билетов нет
            </div>

            </c:otherwise>
            </c:choose>

            </c:if>

</body>
</html>