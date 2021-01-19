<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создать марщрут</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>const myContextPath = "${pageContext.request.contextPath}"</script>
    <script type="text/javascript" src="resources/js/create-route.js"></script>
    <sec:csrfMetaTags />
</head>
<body>
<div class="container">
    <div class="col-12">
        <div class="row mt-4">
            <h5 class="font-weight-light pt-4 col-4 offset-4">Создать ${route.routeNumber.number} маршрут</h5>
        </div>
        <div class="row mt-2">
            <input id="routeNumber" type="hidden" value="${route.routeNumber.number}"/>
            <input id ="routeFromStation" class="form-control col-2 mr-2" placeholder="От" type="text">
            <input id ="routeToStation" class="form-control col-2 mr-2" placeholder="До" type="text">
            <input id ="travelTime" class="form-control col-2 mr-2" placeholder="Время в пути 00:00" type="text">
            <input id="myButtonRoute" class="btn btn-info rounded-0" type="button" value="Добавить">
        </div>
        <div class="row">
            <div  id="createSuccessRoute">

                <span class="font-weight-light text-info"><small id="successTextRoute"></small></span>
            </div>
        </div>
    </div>

</div>
</body>
</html>
