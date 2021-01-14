<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>search  ticket</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">


</head>
<body>
<div class="container">
    <div class="p-4">
        <h4 class="text-center font-weight-light">find ticket</h4>
    </div>

    <div class="col-md-8 offset-md-2">
    <form:form action="${pageContext.request.contextPath}/processSearchTicket" method="post" class = "form-horizontal" modelAttribute="searchTicketAttr">
        <div class="row">
            <div class="col form-group">
                <form:input type = "text" class = "form-control" placeholder="From" path="fromStation"/>
            </div>
            <span class="material-icons pt-2">
                east
            </span>
            <div class="col form-group">
                <form:input type="text" class="form-control" placeholder="To" path="toStation"/>
            </div>

        </div>

        <div class="row">
            <div class="col form-group">
                <label for="time"><small>From</small></label>
                <form:input id="time" type="time" class="form-control" placeholder="00:00" path="fromTime"/>
            </div>

            <div class="col form-group">
                <label for="timeTo"><small>To</small></label>
                <form:input id="timeTo" type="time" class="form-control" placeholder="00:00" path="toTime"/>
            </div>

            <div class="col form-group">
                <label for="date"><small>Date</small></label>
                <form:input id="date" type="date" name="date" class="form-control " placeholder="dd.mm.yyyy" path="date"/>

            </div>
            <div class="col-2 pt-4">
                <input id="button" type="submit" name="submit" value="Search" class="btn btn-info rounded-0" style="width: 100%; height: 71%";>
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
            <h6 class="text-center font-weight-light">available trains</h6>
            <ul class="list-group">
                <c:forEach items="${tickets}" var="ticket" varStatus="status">
                        <form:form action="${pageContext.request.contextPath}/fillPassenger" method="get" modelAttribute="ticket">
                <div class="list-group-item list-group-item-action">
                        <div class="text-center">
                        №${ticket.key.train.number}
                        </div>

                        <p></p>

                        <div class="row d-flex justify-content-center" >
                            <div>

                        <div>${ticket.key.departureTime.toLocalTime()}</div>
                                <div  class="row d-flex justify-content-center font-weight-bold">${searchTicketAttr.fromStation}</div>

                            </div>

                            <div class="col-10">
                                <hr>
                            </div>

                            <div>
                        <div>${ticket.value.toLocalTime()}</div>
                                <div  class="row d-flex justify-content-center font-weight-bold"> ${searchTicketAttr.toStation}</div>
                            </div>
                        </div>
                        <p></p>
                        <span class="badge badge-info badge-pill mt-3">available ${ticket.key.train.availableSeats} seats </span>
<%--                    <div class="row d-flex justify-content-end">--%>

                    <input  class="btn btn-info btn-sm rounded-0" type="submit" name="buy" value="Buy"   style="width: 20%; float: right">
                    </form:form>
                </c:forEach>

        </c:when>

    </c:choose>

</c:if>


</body>
</html>
