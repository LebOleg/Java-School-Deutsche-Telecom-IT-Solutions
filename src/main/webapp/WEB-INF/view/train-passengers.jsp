<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Список пассажиров</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
<%@ include file="nav.jsp" %>

<div class="container mt-3 col-6">
    <table class="table">
        <thead style="background-color: black">
        <tr style="color: white">
            <th scope="col">Имя</th>
            <th scope="col">Фамилия</th>
            <th scope="col">Отчество</th>
            <th scope="col">Дата рождения</th>
            <th scope="col">Номер пасспорта</th>
        </tr>
        </thead>
        <tbody style="background-color: white">
        <c:forEach items="${passengers.pageList}" var="passenger">

            <tr>
                <td>${passenger.name}</td>
                <td>${passenger.lastName}</td>
                <td>${passenger.middleName}</td>
                <td>${passenger.birthday}</td>
                <td>${passenger.passportNumber}</td>
            </tr>

        </c:forEach>

        <br/>

        <tr>
            <td>
                <c:choose>

                    <c:when test="${passengers.firstPage}">
                        <span>Пред</span>
                    </c:when>
                    <c:otherwise>
                        <a href="../prev">Пред</a>
                    </c:otherwise>

                </c:choose>

                <c:forEach begin="1" end="${passengers.pageCount}" step="1" varStatus="tagStatus">
                    <c:choose>
                        <c:when test="${(passengers.page + 1) == tagStatus.index}">
                            <span>${tagStatus.index}</span>
                        </c:when>

                        <c:otherwise>
                            <a href="../${tagStatus.index}">${tagStatus.index}</a>
                        </c:otherwise>

                    </c:choose>
                </c:forEach>

                <c:choose>

                    <c:when test="${passengers.lastPage}">
                        <span>След</span>
                    </c:when>
                    <c:otherwise>
                        <a href="../next">След</a>
                    </c:otherwise>
                </c:choose>
            </td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>


        </tbody>
    </table>
</div>
</body>
</html>
