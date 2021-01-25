<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Расписание</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <h6 class="text-center font-weight-light">${stationName}</h6>
            <c:if test="${not empty timetable}">
            <ul class="list-group">
                <c:forEach items="${timetable}" var="timetable">

                <div class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                    <div class="flex-column">
                        №${timetable.train.number}
                        <p><small>${timetable.departureTime}</small></p>
                        <span class="badge badge-info badge-pill">available ${timetable.train.availableSeats} seats </span>
                    </div>
                </div>
                </c:forEach>
                </c:if>
</body>
</html>