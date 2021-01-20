<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show station timetable</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>const myContextPath = "${pageContext.request.contextPath}"</script>
    <script type="text/javascript" src="resources/js/autocomplete.js"></script>
    <sec:csrfMetaTags />
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-8 offset-md-2 p-2 mt-4 rounded">
            <h4 class="text-center font-weight-light"> Расписание по станциям </h4>
                <form:form action="${pageContext.request.contextPath}/showTimetable" method="POST" modelAttribute="searchStation" class="form-inline p-3">
                    <form:input path="stationName" type="text" name="stationName" placeholder="Station" id = "search" class="form-control form-control-lg rounded-0 border-info col-4"/>
                    <form:input path="date" type="date" name="stationName"  class="form-control form-control-lg rounded-0 border-info col-4"/>
                    <input type="submit" name="submit" value="Search" class="btn btn-info btn-lg rounded-0" style="width: 20%;"
                </form:form>
        </div>

        <div class="col-md-4" style=" width: 24%; position: relative; top: -40px; left: 184px;">
            <div class="list-group">
                <a id="show-list" href="#" class="list-group-item list-group-item-action border-1" style="display: none"> List 1</a>
            </div>
        </div>
    </div>
</div>

<c:if test="${timetable != null}">
    <c:choose>
        <c:when test="${not empty timetable}">
            <div id="timetable" class="container p-4">

                    <div class="col-md-6 offset-md-3">
                        <div class="row offset-5">
                        <h6 class="font-weight-light">${searchStation.stationName}</h6>
                        <h6 class="font-weight text-right col-10">${searchStation.date}</h6>
                        </div>
                        <div class="row">
                        <ul class="list-group col-11">
                        <c:forEach items="${timetable}" var="timetable">
                            <div class="list-group-item list-group-item-action">
                                <div class="">
                                   Поезд №${timetable.routeNumber}
                                    <p></p>
                                    <div><span class="font-weight-bold">${timetable.departureTime.toLocalTime()}</span>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
        </c:when>
        <c:otherwise>
                            <p class="text-center">Записей нет</p>

        </c:otherwise>
    </c:choose>
</c:if>
                        </div>
</body>
</html>