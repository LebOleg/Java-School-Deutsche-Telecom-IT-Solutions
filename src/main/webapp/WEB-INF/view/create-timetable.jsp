<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создать расписание</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script>const myContextPath = "${pageContext.request.contextPath}"</script>
    <script type="text/javascript" src="resources/js/timetable.js"></script>
    <sec:csrfMetaTags />
</head>
<body>

<c:if test="${trains != null}">
<c:choose>
<c:when test="${not empty trains}">
<div id="trains" class="container p-4">

        <div class="col-md-3">
            <h5 class="text-center font-weight-light pb-3">Добавить в расписание</h5>
            <ul class="list-group">
                <c:forEach items="${trains}" var="train">
              <div class="row">
                <div class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                    <div class="flex-column">
                        №<span class="trainNumber">${train.id}</span>
                        <p class="">Свободных мест: ${train.availableSeats}</p>
                            <div class="hiddenFields">
                            <select class="form-control form-control-sm selectNumber">
                                <option value="" selected>Выберите маршрут</option>
                                <c:forEach items="${routes}" var="route">
                                <option value="${route.number}">${route.number}</option>
                                </c:forEach>
                            </select>
                            <p></p>
                        <input type="date" placeholder="Test" class="form-control timetableDate">
                        <p></p>
                        <input type="time" placeholder="Test" class="form-control timetableTime">
                                <p></p>
                                <input type="button" value="Добавить" class="btn btn-info rounded-0 timetable">
                                    <span class="font-weight-light text-info successText"><small class=""></small></span>
                            </div>
                    </div>
                </div>
              </div>

                <p></p>
                </c:forEach>
                </c:when>
                <c:otherwise>
                <p class="text-center">Доступных поездов нет</p>

                </c:otherwise>
                </c:choose>
                </c:if>



</body>
</html>
