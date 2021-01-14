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
        <div class="col-md-6 offset-md-3 p-2 mt-4 rounded">
            <h4 class="text-center font-weight-light"> search station </h4>
                <form:form action="${pageContext.request.contextPath}/showTimetable" method="POST" modelAttribute="stationName" class="form-inline p-3">
                    <input type="text" name="stationName" placeholder="Station" id = "search" class="form-control form-control-lg rounded-0 border-info" style="width: 80%;">
                    <input type="submit" name="submit" value="Search" class="btn btn-info btn-lg rounded-0" style="width: 20%;"
                </form:form>
        </div>

        <div class="col-md-4" style=" width: 40.5%; position: relative; top: -37px; left: 275px;">
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
                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <h6 class="text-center font-weight-light">${stationName}</h6>
                        <ul class="list-group">
                        <c:forEach items="${timetable}" var="timetable">
                            <div class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                                <div class="flex-column">
                                    №${timetable.train.number}
                                    <p class="font-weight-bold">${timetable.arrivalTime.hour}:${timetable.arrivalTime.minute}</p>
                                    <span class="badge badge-info badge-pill">available ${timetable.train.availableSeats} seats </span>
                                </div>
                            </div>
                        </c:forEach>
        </c:when>
        <c:otherwise>
                            <p class="text-center">Station "${stationName}" doesn't exist</p>

        </c:otherwise>
    </c:choose>
</c:if>
</body>
</html>