<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<html>
<head>
    <title>Создать станции</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>const myContextPath = "${pageContext.request.contextPath}"</script>
    <script type="text/javascript" src="resources/js/create-station.js"></script>
    <script type="text/javascript" src="resources/js/create-connection.js"></script>
    <sec:csrfMetaTags />

</head>
<body>
<div class="container">
    <div class="col-md-12">
        <div class="row">
        <h5 class="text-center font-weight-light pt-4 col-4">Создать станцию</h5>
        </div>

            <div class="row">
                <input id="stationName" class="form-control col-4 mt-2" placeholder="Станция" type="text"/>
                <input id="myButton" class="btn btn-info rounded-0 m-2" type="button" value="Добавить">
            </div>
        <div class="row">
        <div  id="createSuccess">

            <span class="font-weight-light text-info"><small id="successText"></small></span>
        </div>
        </div>

        <div class="row mt-4">
            <h5 class="text-center font-weight-light pt-4 col-4">Создать связь между станциями</h5>
        </div>
        <div class="row mt-2">
            <input id ="fromStation" class="form-control col-2 mr-2" placeholder="От" type="text">
            <input id ="toStation" class="form-control col-2 mr-2" placeholder="До" type="text">
            <input id="myButtonPath" class="btn btn-info rounded-0" type="button" value="Добавить" style="width: 24mm">
        </div>
        <div class="row">
        <div  id="createSuccessConnection">

            <span class="font-weight-light text-info"><small id="successTextConnection"></small></span>
        </div>
        </div>

        <div class="row">
            <h5 class="text-center font-weight-light pt-5 col-4">Создать маршрут</h5>
        </div>

        <div class="row mt-2">
            <div>
            <form:form action="${pageContext.request.contextPath}/showCreateRoute" modelAttribute="route" method="post" >
                <form:input  id="routeInput" type="text" placeholder="Название маршрута" class="form-control" path="routeNumber.number"/>
            </div>
            <div>
                <input type="submit" value="Создать" class="btn btn-info rounded-0 ml-2">
            </div>
            </form:form>

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
