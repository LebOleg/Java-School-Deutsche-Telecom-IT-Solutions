<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Расписание по станции</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/autocomplete.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/error.css"/>
    <sec:csrfMetaTags/>
</head>
<body>
<%@ include file="nav.jsp" %>

<div class="card col-6 offset-3 mt-5 mb-5 rounded">

            <div class="card-body">
                <div class="card-title text-center mb-4"></div>
            <form:form action="${pageContext.request.contextPath}/showTimetable" method="POST"
                       modelAttribute="searchStation" class="form-inline">
                <div class="row">
                    <div class="col">
                        <form:input path="stationName" type="text" name="stationName" placeholder="Станция" id="search"
                                    class="form-control rounded-0 "/>
                        <div class="row">
                            <div class="col-12">
                                <div class="list-group">
                                    <a id="show-list" href="#" class="list-group-item list-group-item-action border-1"
                                       style="display: none"> List 1</a>
                                </div>
                            </div>
                        </div>

                    </div>

                    <div class="col">
                        <form:input path="date" type="date" name="stationName" class="form-control rounded-0"/>
                    </div>
                    <div class="col">
                        <input type="submit" name="submit" value="Поиск" class="btn btn-info rounded-0">
                    </div>
                </div>
                    <div class="card-body text-center">

                    <form:errors path="stationName"  cssClass="error"/>
                        <br/>
                    <form:errors path="date" cssClass="error"/>
                    </div>
            </div>
                    </div>


                    </form:form>
        </div>
    </div>


</div>

<c:if test="${timetable != null}">
<c:choose>
<c:when test="${not empty timetable}">
<div id="timetable" class="container p-4">

    <div class="col-7 offset-3">
        <ul class="list-group col-11">

            <div class="row">
                <div class="list-group-item list-group-item-action " style="background-color: black">
                    <div class="d-flex justify-content-center"><h6 class="font-weight-light"
                                                                   style="color: white">${searchStation.stationName}</h6>
                    </div>
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
        </ul>
    </div>
</div>
</body>
</html>