<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Создать маршрут</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
<%@ include file="nav.jsp" %>

<div class="col-4 mr-5 offset-4 mt-5">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title text-center">Создать маршрут</h5>
            <p class="card-text">
                <form:form action="${pageContext.request.contextPath}/employee/route/showCreateRoute"
                           modelAttribute="route" method="post">
                <form:input id="routeInput" type="text" placeholder="Название маршрута" class="form-control"
                            path="routeNumber.number"/>
            </p>
            <input type="submit" value="Создать" class="btn btn-info rounded-0 ml-2">
            <span class="font-weight-light text-info"><small id="successTextRoute"></small></span>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>
