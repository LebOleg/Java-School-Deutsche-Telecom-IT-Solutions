<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>
<body>
<%@ include file="nav.jsp"%>
<div class="container mt-3 col-6">
<table class="table">
    <thead style="background-color: black">
    <tr style="color: white">
        <th scope="col">№</th>
        <th scope="col">Свободные места</th>
        <th scope="col">Маршрут</th>
    </tr>
    </thead>
    <tbody style="background-color: white">
    <c:forEach items="${trains.pageList}" var="train">

        <tr>
            <th scope="row">${train.id}</th>
            <td>${train.availableSeats}</td>

            <c:choose>
                <c:when test="${empty train.routeNumber}">
                    <td>Маршрут не назначен</td>
                </c:when>

                <c:otherwise>
                    <td>${train.routeNumber}</td>
                </c:otherwise>

            </c:choose>

        </tr>
    </c:forEach>


<br/>
<tr>
    <td>
<c:choose>

    <c:when test="${trains.firstPage}">
        <span>Пред</span>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/employee/train/prev">Пред</a>
    </c:otherwise>

</c:choose>

<c:forEach begin="1" end="${trains.pageCount}" step="1"  varStatus="tagStatus">
    <c:choose>
        <c:when test="${(trains.page + 1) == tagStatus.index}">
            <span>${tagStatus.index}</span>
        </c:when>

        <c:otherwise>
            <a href="${pageContext.request.contextPath}/employee/train/${tagStatus.index}">${tagStatus.index}</a>
        </c:otherwise>

    </c:choose>
</c:forEach>

<c:choose>

    <c:when test="${trains.lastPage}">
        <span>След</span>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/employee/train/next">След</a>
    </c:otherwise>
</c:choose>
    </td>
    <td></td>
    <td></td>
</tr>


    </tbody>
</table>
</div>
</body>
</html>
