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
    <script src="https://kit.fontawesome.com/7b83e77052.js" crossorigin="anonymous"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/autocomplete.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/nav.css" />
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300&display=swap" rel="stylesheet">

    <sec:csrfMetaTags />
</head>

<body>

<%@ include file="nav.jsp"%>
<div class="container">
    <div class="row">
        <div class="col-md-6 offset-3 p-2 mt-4 rounded">
                <form:form action="${pageContext.request.contextPath}/showTimetable" method="POST" modelAttribute="searchStation" class="form-inline p-3">
                    <form:input path="stationName" type="text" name="stationName" placeholder="Станция" id = "search" class="form-control rounded-0 "/>
                    <form:input path="date" type="date" name="stationName"  class="form-control rounded-0"/>
                    <input type="submit" name="submit" value="Поиск" class="btn btn-info rounded-0" style="width: 20%;"
                </form:form>
        </div>

        <div class="col-md-3" style=" width: 22.5%; position: relative; top: -40px; left: 278px;">
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
                        <ul class="list-group col-11">

                            <div class="row">
                            <div class="list-group-item list-group-item-action " style="background-color: black" >
                            <div class="d-flex justify-content-center"> <h6 class="font-weight-light" style="color: white">${searchStation.stationName}</h6></div>
                                <div class="d-flex justify-content-center">
                        <h6 class="font-weight" style="color: white">${searchStation.date}</h6>
                                </div>
                        </div>
                            </div>
                        <div class="row">

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
                            <p class="text-center" style="color: white">Записей нет</p>

        </c:otherwise>
    </c:choose>
</c:if>
                        </div>
</body>
</html>